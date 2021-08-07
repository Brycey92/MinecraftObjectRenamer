/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galaxy_craft.mor;

import java.awt.Color;
import java.awt.Toolkit;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Bryce
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form MinecraftObjectRenamerUI
     */
    public MainUI() {
        initComponents();
        
        refreshMinecraftVersions();
        
        outputLocTextField.getDocument().addDocumentListener(new OutputLocationDocumentListener(outputLocTextField, null));
        outputLocTextField.setText(FileUtils.getMyLocation());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        minecraftVersionComboBox = new javax.swing.JComboBox<>();
        minecraftVersionLabel = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        outputLocationLabel = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        goButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        statusTextField = new javax.swing.JTextField();
        outputLocTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minecraft Object Renamer");

        minecraftVersionComboBox.setToolTipText("Shows only versions present in %appdata%/.minecraft/assetes/indexes/");
        minecraftVersionComboBox.setMinimumSize(new java.awt.Dimension(64, 20));
        minecraftVersionComboBox.setPreferredSize(new java.awt.Dimension(64, 20));

        minecraftVersionLabel.setText("Minecraft Version:");

        refreshButton.setText("Refresh");
        refreshButton.setMargin(new java.awt.Insets(2, 8, 2, 8));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        outputLocationLabel.setText("Output Location:");

        browseButton.setText("Browse...");
        browseButton.setMargin(new java.awt.Insets(2, 8, 2, 8));
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        statusLabel.setText("Status:");

        statusTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputLocationLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(minecraftVersionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minecraftVersionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshButton)))
                        .addGap(0, 147, Short.MAX_VALUE))
                    .addComponent(outputLocTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browseButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(goButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minecraftVersionLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(minecraftVersionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(refreshButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outputLocationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseButton)
                    .addComponent(outputLocTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(goButton)
                    .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        minecraftVersionComboBox.getAccessibleContext().setAccessibleName("");
        minecraftVersionComboBox.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refreshMinecraftVersions();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        //Create a file chooser
        final JFileChooser fc = new JFileChooser(outputLocTextField.getText());
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //In response to a button click:
        int returnVal = fc.showSaveDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            outputLocTextField.setText(fc.getSelectedFile().getPath());
        }
        
        
        //Handle open button action.
        /*FileOpenService fos = null;
        FileContents fileContents = null;

        try {
            fos = (FileOpenService)ServiceManager.
                      lookup("javax.jnlp.FileOpenService"); 
        } catch (UnavailableServiceException exc) { }

        if (fos != null) {
            try {
                fileContents = fos.openFileDialog(null, null); 
            } catch (Exception exc) {
                log.append("Open command failed: "
                           + exc.getLocalizedMessage()
                           + newline);
                log.setCaretPosition(log.getDocument().getLength());
            }
        }

        if (fileContents != null) {
            try {
                //This is where a real application would do something
                //with the file.
                log.append("Opened file: " + fileContents.getName()
                           + "." + newline);
            } catch (IOException exc) {
                log.append("Problem opening file: "
                           + exc.getLocalizedMessage()
                           + newline);
            }
        } else {
            log.append("User canceled open request." + newline);
        }*/
    }//GEN-LAST:event_browseButtonActionPerformed

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        Path indexPath = FileUtils.getIndexFilePath((String) minecraftVersionComboBox.getModel().getSelectedItem());
        if(JsonUtils.processIndex(indexPath, outputLocTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Output successful!", "Results", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Output failed!", "Results", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_goButtonActionPerformed
    
    public void updateStatus() {
        String selectedMcVer = (String) minecraftVersionComboBox.getModel().getSelectedItem();
        
        if(selectedMcVer == null) {
            statusTextField.setText("Error: No jsons found in " + FileUtils.getIndexesDirForDisplay());
        }
        else if(selectedMcVer.equals("") || !FileUtils.isValidPath(outputLocTextField.getText())) {
            statusTextField.setText("Not ready! Check Minecraft Version and Output Location");
        }
        else {
            statusTextField.setText("Ready");
        }
    }
    
    private void refreshMinecraftVersions() {
        ArrayList<String> mcVers = FileUtils.getMinecraftVersions();
        minecraftVersionComboBox.setModel(new DefaultComboBoxModel<>(mcVers.toArray(new String[0])));
        
        updateStatus();
    }
    
    private class OutputLocationDocumentListener implements DocumentListener {
        private final JTextField textField;
        private final Border greenBorder = new LineBorder(Color.GREEN, 1, false);
        private final Border redBorder = new LineBorder(Color.RED, 1, false);

        public OutputLocationDocumentListener(JTextField textField, JTextField a) {
            this.textField = textField;
        }
        @Override
        public void insertUpdate(DocumentEvent e) {
            update(e);
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            update(e);
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            update(e);
        }
        
        private void update(DocumentEvent e) {
            Border prevBorder = textField.getBorder();
            
            if(FileUtils.isValidPath(textField.getText())) {
                textField.setBorder(greenBorder);
            }
            else {
                textField.setBorder(redBorder);
            }
            
            if(prevBorder != textField.getBorder()) {
                updateStatus();
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            // Set old-looking cross-platform Java L&F (also called "Metal")
            //javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
            
            // Set the System L&F
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            
            // Set ancient-looking L&F
            /*for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                /*if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }
    
    private static OutputLocationDocumentListener outputLocDocListener;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JButton goButton;
    private javax.swing.JComboBox<String> minecraftVersionComboBox;
    private javax.swing.JLabel minecraftVersionLabel;
    private javax.swing.JTextField outputLocTextField;
    private javax.swing.JLabel outputLocationLabel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField statusTextField;
    // End of variables declaration//GEN-END:variables
}
