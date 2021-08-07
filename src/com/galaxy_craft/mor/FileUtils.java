/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galaxy_craft.mor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author Bryce Browner
 */
public class FileUtils {
    private static MessageDigest shaDigest = null;

    protected static ArrayList<String> getMinecraftVersions() {
        ArrayList<String> versions = new ArrayList<>();
        File indexesDir = getIndexesDir().toFile();
        
        if(indexesDir.isDirectory()) {
            File[] indexFiles = indexesDir.listFiles();
            for(File indexFile : indexFiles) {
                if(getExtension(indexFile).equals("json")) {
                    versions.add(getFileNameWithoutExtension(indexFile.getName()));
                }
            }
        }
        return versions;
    }
    
    public static String getExtension(File file) {
        return getExtension(file.toPath());
    }
    
    public static String getExtension(Path path) {
        String fileName = path.getFileName().toString();
        int index = fileName.lastIndexOf('.');

        if(index > 0) {
          return fileName.substring(index + 1);
        }
        else {
            return "";
        }
    }
    
    public static String getExtension(String path) {
        return getExtension(Paths.get(path));
    }
    
    public static String getFileNameWithoutExtension(String fileName) {
        return fileName.replaceFirst("[.][^.]+$", "");
    }
    
    // TODO: DOES NOT WORK IN LINUX
    public static boolean isValidPath(String pathStr) {
        try {
            Path path = Paths.get(pathStr);
            if(path.getRoot() == null || !Files.exists(path.getRoot()) || (Files.exists(path) && !Files.isWritable(path))) {
                return false;
            }

        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        
        return true;
    }
    
    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        //String os = SystemUtils.OS_NAME;
        return os;
    }
    
    public static Path getMinecraftDir() {
        String os = getOperatingSystem().toLowerCase();
        
        if(os.contains("windows")) {
            return Paths.get(System.getenv("APPDATA"), ".minecraft");
        }
        else if(os.contains("mac")) {
            return Paths.get(System.getProperty("user.home"), "Library", "Application Support", "minecraft");
        }
        else {
            return Paths.get(System.getProperty("user.home"), ".minecraft");
        }
    }
    
    public static Path getIndexesDir() {
        return Paths.get(getMinecraftDir().toString(), "assets", "indexes");
    }
    
    public static Path getObjectsDir() {
        return Paths.get(getMinecraftDir().toString(), "assets", "objects");
    }
    
    public static Path getIndexesDirForDisplay() {
        String os = getOperatingSystem().toLowerCase();
        
        if(os.contains("windows")) {
            return Paths.get("%appdata%", ".minecraft", "assets", "indexes");
        }
        else if(os.contains("mac")) {
            return Paths.get("~", "Library", "Application Support", "minecraft", "assets", "indexes");
        }
        else {
            return Paths.get("~", ".minecraft", "assets", "indexes");
        }
    }
    
    public static Path getIndexFilePath(String mcVersion) {
        return Paths.get(FileUtils.getIndexesDir().toString(), mcVersion + ".json");
    }
    
    public static Path getObjectFilePath(String hash) {
        return Paths.get(FileUtils.getObjectsDir().toString(), hash.substring(0, 2), hash);
    }
    
    public static Path getOutputFilePath(String outputPath, String partialFilePath) {
        if(partialFilePath.equals("pack.mcmeta")) {
            return Paths.get(outputPath, partialFilePath);
        }
        else {
            return Paths.get(outputPath, "assets", partialFilePath);
        }
    }
    
    public static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount; 

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
       return sb.toString();
    }
    
    public static String getSHA256(File file) {
        if(shaDigest == null) {
            try {
                shaDigest = MessageDigest.getInstance("SHA-256");
            }
            catch(NoSuchAlgorithmException ex) {
                System.err.println("SHA-256 hashing not supported");
                return "";
            }
        }
        
        try {
                return getFileChecksum(shaDigest, file);
            }
            catch (IOException ex) {
                //Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace(System.err);
                return "";
            }
    }
    
    public static boolean processObjectFile(String partialPath, String hash, long size, String outputPath) {
        File objectFile = FileUtils.getObjectFilePath(hash).toFile();
        File outputFile = FileUtils.getOutputFilePath(outputPath, partialPath).toFile();

        try {
            File outputFileParentDir = outputFile.getParentFile();
            if(!outputFileParentDir.exists()) {
                outputFileParentDir.mkdirs();
            }

            if(!objectFile.isFile() || !objectFile.canRead()) {
                System.err.println("Missing read permissions to object file " + objectFile.getAbsolutePath());
                return false;
            }

            outputFile.createNewFile();
        }
        catch(SecurityException | IOException ex) {
            ex.printStackTrace(System.err);
            return false;
        }

        if(objectFile.length() != size) {
            System.err.println("Object file " + objectFile.getAbsolutePath() + " size " + objectFile.length() + " does not match expected size " + size);
            return false;
        }

        String sha256 = FileUtils.getSHA256(objectFile).toLowerCase();
        if(!sha256.equalsIgnoreCase(hash)) {
            System.err.println("Object file " + objectFile.getAbsolutePath() + " has a mismatched hash " + sha256);
            //return false;
        }

        try {
            Files.copy(objectFile.toPath(), outputFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException ex) {
            ex.printStackTrace(System.err);
            return false;
        }
        
        return true;
    }
    
    public static String getMyLocation() {
        String myLocation;
        
        try {
            myLocation = MainUI.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            String os = getOperatingSystem().toLowerCase();
            char badSeparator;
            
            if(os.contains("windows")) {
                badSeparator = '/';
                
                myLocation = myLocation.replace(badSeparator, File.separatorChar);
                
                if(myLocation.charAt(0) == File.separatorChar) {
                    return myLocation.substring(1);
                }
            }
            else {
                badSeparator = '\\';
                
                return myLocation.replace(badSeparator, File.separatorChar);
            }
        }
        catch(URISyntaxException e) {
            myLocation = "";
        }
        
        return myLocation;
    }
}
