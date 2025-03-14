/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package echoesoffate;

/**
 *
 * @author Kean Saligue
 */

import javax.swing.*;
import java.awt.*;

public class MainMenu extends javax.swing.JPanel {

    /**
     * Creates new form LoginScreen
     */
    
    private MainFrame frame; // Reference to MainFrame
    UserData userData;
    
    public MainMenu(MainFrame frame, UserData userData) {
        this.frame = frame;
        this.userData = userData;
        initComponents();
        updateData(); 
       btnExit.addActionListener(e -> exitGame());
        
    }

     public void updateData() {
         this.userData = frame.userData;
        lblMsg.setText("Time is running out, " + userData.getUsername() + ". What will you do?");
    }

    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Leaving already, " + userData.getUsername() + "? But the past still calls to you…", 
            "Exit Game", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); // Close the application
        }
    }
     
     public void setUserData(String username, String password) {
        userData.setUsername(username);
        userData.setPassword(password);
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGameTitle = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();
        lblQuote2 = new javax.swing.JLabel();
        btnNewGame = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnLoadGame = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        lblQuote = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameTitle.setFont(new java.awt.Font("Pristina", 1, 130)); // NOI18N
        lblGameTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblGameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameTitle.setText("Echoes of Fate");
        add(lblGameTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 770, 190));

        lblMsg.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMsg.setText("Time is running out, [Username]. What will you do?");
        add(lblMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 760, 110));

        lblQuote2.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblQuote2.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote2.setText("Step into the past… before it erases you.");
        add(lblQuote2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 760, 110));

        btnNewGame.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnNewGame.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        btnNewGame.setForeground(new java.awt.Color(255, 255, 255));
        btnNewGame.setText("New Game");
        add(btnNewGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 180, 50));

        btnExit.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnExit.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Exit Game");
        add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 610, 170, 50));

        btnLoadGame.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnLoadGame.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        btnLoadGame.setForeground(new java.awt.Color(255, 255, 255));
        btnLoadGame.setText("Load Game");
        add(btnLoadGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 420, 170, 50));

        btnSetting.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnSetting.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        btnSetting.setForeground(new java.awt.Color(255, 255, 255));
        btnSetting.setText("Settings");
        add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 520, 170, 50));

        lblQuote.setFont(new java.awt.Font("Pristina", 1, 18)); // NOI18N
        lblQuote.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote.setText("@2025 OOP 2 Project. All Rights Reserved  | Design by  Group Nuevas");
        add(lblQuote, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 750, 760, 70));

        lblLoginFormBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblLoginFormBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblLoginFormBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblLoginFormBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoginFormBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblLoginFormBackground.setOpaque(true);
        add(lblLoginFormBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 800, 450));

        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon("C:\\Users\\Kean Saligue\\Documents\\GitHub\\echoes-of-fate\\EchoesOfFate\\src\\echoesoffate\\Assets\\background.jpg")); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLoadGame;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnSetting;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblGameTitle;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblQuote;
    private javax.swing.JLabel lblQuote2;
    // End of variables declaration//GEN-END:variables
}
