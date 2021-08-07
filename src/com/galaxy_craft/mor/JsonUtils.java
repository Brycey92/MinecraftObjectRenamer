/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galaxy_craft.mor;

import com.galaxy_craft.mor.Index.Asset;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Map.Entry;

/**
 *
 * @author Bryce Browner
 */
public class JsonUtils {
    public static boolean processIndex(Path indexPath, String outputPath) {
        File indexFile = indexPath.toFile();
        File outputDir = new File(outputPath);
        
        try {
            if(!outputDir.exists()) {
                outputDir.mkdirs();
            }

            if(!indexFile.isFile() || !indexFile.canRead()) {
                System.err.println("Missing read permissions to index file " + indexFile.getAbsolutePath());
                return false;
            }
            
            if(!outputDir.canWrite()) {
                System.err.println("Missing write permissions to output directory " + outputDir.getAbsolutePath());
                return false;
            }
        }
        catch(SecurityException ex) {
            ex.printStackTrace(System.err);
            return false;
        }
        
        Gson gson = new Gson();
        Index index;
        try {
            index = gson.fromJson(new FileReader(indexFile), Index.class);
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace(System.err);
            return false;
        }
        
        for(Entry<String, Asset> entry : index.objects.entrySet()) {
            if(!FileUtils.processObjectFile(entry.getKey(), entry.getValue().hash, entry.getValue().size, outputPath)) {
                return false;
            }
        }
        
        return true;
    }
}
