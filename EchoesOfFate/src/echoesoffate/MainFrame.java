/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package echoesoffate;

/**
 *
 * @author Kean Saligue
 */

import AsherValeStoryline.AsherValeItaewonGameplay1;
import AsherValeStoryline.AsherVale;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MainFrame extends javax.swing.JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Clip clip;
    
    public UserData userData;
    
    public MainFrame() {
        
        setUndecorated(true);
        
        initComponents(); 
        
        userData = new UserData();     
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginScreen loginScreen = new LoginScreen(this, userData);
        MainMenu mainMenu = new MainMenu(this, userData);
        RegisterScreen registerScreen = new RegisterScreen(this, userData);
        ChooseCharacter chooseCharacter = new ChooseCharacter(this, userData);
        AsherVale asherVale = new AsherVale(this);
        AsherValeItaewonGameplay1 asherValeGameplay1 = new AsherValeItaewonGameplay1(this);
        
        mainPanel.add(loginScreen, "Login");
        mainPanel.add(mainMenu, "Menu");
        mainPanel.add(registerScreen, "Register");
        mainPanel.add(chooseCharacter, "ChooseCharacter");
        mainPanel.add(asherVale, "AsherVale");
        mainPanel.add(asherValeGameplay1, "AsherValeItaewonGameplay1");

        setContentPane(mainPanel);
        setVisible(true);
        
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        playBackgroundMusic("src/echoesoffateassets/background_music.wav");
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Echoes of Fate");
        setResizable(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    private void playBackgroundMusic(String filepath) {
        try {
            if (clip != null && clip.isRunning()) {
                String currentTrack = clip.getMicrosecondPosition() > 0 ? clip.toString() : "";
                if (currentTrack.equals(filepath)) {
                    return;
                }
                clip.stop();
                clip.close();
            }

            File soundFile = new File(filepath);
            if (!soundFile.exists()) {
                System.out.println("File not found: " + soundFile.getAbsolutePath());
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            System.out.println("Playing: " + soundFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showScreen(String screenName) {
        if (screenName.equals("Login")) {
            ((LoginScreen) mainPanel.getComponent(0)).updateTextFields();
        }       
        if (screenName.equals("Menu")) {
            ((MainMenu) mainPanel.getComponent(1)).updateData();
        }
        if (screenName.equals("AsherVale")) {
            playBackgroundMusic("src/echoesoffateassets/gameplay_background_music.wav");
            ((AsherVale) mainPanel.getComponent(4)).startDialogue();
        } else if (clip == null || !clip.isRunning()) {
            playBackgroundMusic("src/echoesoffateassets/background_music.wav");
        }
        cardLayout.show(mainPanel, screenName);
    }
     
    public void setUserData(String username, String password) {
        userData.setUsername(username);
        userData.setPassword(password);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
        
        System.out.println("Working..");
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}