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
import AsherValeStoryline.AsherValeGameplayAfterRooftop;
import AsherValeStoryline.AsherValeGameplayApartment;
import AsherValeStoryline.AsherValeGameplayBunker;
import AsherValeStoryline.AsherValeGameplayContainer;
import AsherValeStoryline.AsherValeGameplayFactory;
import AsherValeStoryline.AsherValeGameplayHiddenBar;
import AsherValeStoryline.AsherValeGameplayHotel;
import AsherValeStoryline.AsherValeGameplayOutsideApartment;
import AsherValeStoryline.AsherValeItaewonGameplayBar;
import AsherValeStoryline.AsherValeItaewonGameplayBar2;
import AsherValeStoryline.AsherValeGameplayOutsideBar;
import AsherValeStoryline.AsherValeGameplayOutsideBunker;
import AsherValeStoryline.AsherValeGameplayOutsideContainer;
import AsherValeStoryline.AsherValeGameplayOutsideHotel;
import AsherValeStoryline.AsherValeGameplayOutsideWarehouse;
import AsherValeStoryline.AsherValeGameplayRooftop;
import AsherValeStoryline.AsherValeGameplaySeoulToBusan;
import AsherValeStoryline.AsherValeGameplayWarehouse;

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
        AsherValeItaewonGameplayBar asherValeItaewonGameplayBar = new AsherValeItaewonGameplayBar(this);
        AsherValeItaewonGameplayBar2 asherValeItaewonGameplayBar2 = new AsherValeItaewonGameplayBar2(this);
        AsherValeGameplayOutsideBar asherValeGameplayOutsideBar = new AsherValeGameplayOutsideBar(this);
        AsherValeGameplayWarehouse asherValeGameplayWarehouse = new AsherValeGameplayWarehouse(this);
        AsherValeGameplayOutsideWarehouse asherValeGameplayOutsideWarehouse = new AsherValeGameplayOutsideWarehouse(this);
        AsherValeGameplayFactory asherValeGameplayFactory = new AsherValeGameplayFactory(this);
        AsherValeGameplaySeoulToBusan asherValeGameplaySeoulToBusan = new AsherValeGameplaySeoulToBusan(this);
        AsherValeGameplayApartment asherValeGameplayApartment = new AsherValeGameplayApartment(this);
        AsherValeGameplayOutsideApartment asherValeGameplayOutsideApartment = new AsherValeGameplayOutsideApartment(this);
        AsherValeGameplayContainer asherValeGameplayContainer = new AsherValeGameplayContainer(this);
        AsherValeGameplayOutsideContainer asherValeGameplayOutsideContainer = new AsherValeGameplayOutsideContainer(this);
        AsherValeGameplayRooftop asherValeGameplayRooftop = new AsherValeGameplayRooftop(this);
        AsherValeGameplayAfterRooftop asherValeGameplayAfterRooftop = new AsherValeGameplayAfterRooftop(this);
        AsherValeGameplayHotel asherValeGameplayHotel = new AsherValeGameplayHotel(this);
        AsherValeGameplayOutsideHotel asherValeGameplayOutsideHotel = new AsherValeGameplayOutsideHotel(this);
        AsherValeGameplayBunker asherValeGameplayBunker = new AsherValeGameplayBunker(this);
        AsherValeGameplayOutsideBunker asherValeGameplayOutsideBunker = new AsherValeGameplayOutsideBunker(this);
        AsherValeGameplayHiddenBar asherValeGameplayHiddenBar = new AsherValeGameplayHiddenBar(this);
        
        mainPanel.add(loginScreen, "Login");
        mainPanel.add(mainMenu, "Menu");
        mainPanel.add(registerScreen, "Register");
        mainPanel.add(chooseCharacter, "ChooseCharacter");
        mainPanel.add(asherVale, "AsherVale");
        mainPanel.add(asherValeGameplay1, "AsherValeItaewonGameplay1");
        mainPanel.add(asherValeItaewonGameplayBar, "AsherValeItaewonGameplayBar");
        mainPanel.add(asherValeItaewonGameplayBar2, "AsherValeItaewonGameplayBar2");
        mainPanel.add(asherValeGameplayOutsideBar, "AsherValeGameplayOutsideBar");
        mainPanel.add(asherValeGameplayWarehouse, "AsherValeGameplayWarehouse");
        mainPanel.add(asherValeGameplayOutsideWarehouse, "AsherValeGameplayOutsideWarehouse");
        mainPanel.add(asherValeGameplayFactory, "AsherValeGameplayFactory");
        mainPanel.add(asherValeGameplaySeoulToBusan, "AsherValeGameplaySeoulToBusan");
        
        mainPanel.add(asherValeGameplayApartment, "AsherValeGameplayApartment");
        mainPanel.add(asherValeGameplayOutsideApartment, "AsherValeGameplayOutsideApartment");
        mainPanel.add(asherValeGameplayContainer, "AsherValeGameplayContainer");
        mainPanel.add(asherValeGameplayOutsideContainer, "AsherValeGameplayOutsideContainer");
        
        
       
        mainPanel.add(asherValeGameplayRooftop, "AsherValeGameplayRooftop");
        mainPanel.add(asherValeGameplayAfterRooftop, "AsherValeGameplayAfterRooftop");
        
        
        mainPanel.add(asherValeGameplayHotel, "AsherValeGameplayHotel");
        mainPanel.add(asherValeGameplayOutsideHotel, "AsherValeGameplayOutsideHotel");
        mainPanel.add(asherValeGameplayBunker, "AsherValeGameplayBunker");
        mainPanel.add(asherValeGameplayOutsideBunker, "AsherValeGameplayOutsideBunker");
        mainPanel.add(asherValeGameplayHiddenBar, "AsherValeGameplayHiddenBar");

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
     
    private String currentMusicPath = "";
    
    private void playBackgroundMusic(String filepath) {
        try {
            if (filepath.equals(currentMusicPath)) {
                return; // already playing the same music
            }

            if (clip != null && clip.isRunning()) {
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

            currentMusicPath = filepath;

            System.out.println("Playing: " + soundFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showScreen(String screenName) {
        System.out.println("Switching to panel: " + screenName);
        
        if (screenName.equals("Login")) {
            ((LoginScreen) mainPanel.getComponent(0)).updateTextFields();
        }       
        if (screenName.equals("Menu")) {
            ((MainMenu) mainPanel.getComponent(1)).updateData();
        }
        if (screenName.equals("AsherVale")) {
            playBackgroundMusic("src/echoesoffateassets/gameplay_background_music.wav");
            ((AsherVale) mainPanel.getComponent(4)).startDialogue();
        } 
        if (screenName.equals("AsherValeItaewonGameplayBar")) {
            playBackgroundMusic("src/echoesoffateassets/jazz.wav");    
        }      
                               
        if (screenName.equals("AsherValeGameplayOutsideBar")) {
            playBackgroundMusic("src/echoesoffateassets/outsidebar_music.wav");
        }
         if (screenName.equals("AsherValeGameplayWarehouse")) {
            playBackgroundMusic("src/echoesoffateassets/outsideandinsidenamdamwarehouse_music.wav");    
        } 
         
      
         if (screenName.equals("AsherValeGameplaySeoulToBusan")) {
            playBackgroundMusic("src/echoesoffateassets/transitionseoultobusan.wav");    
        } 
         
         if (screenName.equals("AsherValeGameplayApartment")) {
            playBackgroundMusic("src/echoesoffateassets/busaninvestigation1_music.wav");    
        } 
         
         
   
        cardLayout.show(mainPanel, screenName);
    }
     
    public void setUserData(String username, String password) {
        userData.setUsername(username);
        userData.setPassword(password);
    }
    
    
    public void onWarehouseScene2Start() {
    playBackgroundMusic("src/echoesoffateassets/outisdefactory_music.wav");
}
    
    public void afterTrainRideBusan() {
    playBackgroundMusic("src/echoesoffateassets/busanarrival_music.wav");
}   
    
    public void inShippingContainer(){
         playBackgroundMusic("src/echoesoffateassets/busaninvestigation2_music.wav"); 
    }
    
    public void toRoofTop(){
         playBackgroundMusic("src/echoesoffateassets/busaninvestigation3_music.wav"); 
    }
    
    public void afterRoofTop(){
         playBackgroundMusic("src/echoesoffateassets/busantojejuislandtransition_music.wav"); 
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