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
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class LoginScreen extends javax.swing.JPanel {

    /**
     * Creates new form LoginScreen
     */
    
    private MainFrame frame;
    UserData userData; 
    
    public LoginScreen(MainFrame frame, UserData userData) {
       this.userData = userData;
       this.frame = frame;
       initComponents(); 
       
       btnLogin.addActionListener(e -> {
           playButtonClickSound();
           String enteredUsername = userData.getUsername();
           if (!userData.getUsername().equals(txtUsername.getText()) || !userData.getPassword().equals(txtPassword.getText())) {
           JOptionPane.showMessageDialog(this, "Username Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
           return;
       }   
           if (userData.getUsername().equals("") || userData.getPassword().equals("")) {
           JOptionPane.showMessageDialog(this, "Username Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
           return;
       }   
           System.out.println("Logging in as: " + enteredUsername);
           frame.showScreen("Menu");
       });
       btnRegister.addActionListener(e -> {
            playButtonClickSound();
            frame.showScreen("Register");
        });    
    }
    
    public void updateTextFields(){
       txtUsername.setText(userData.getUsername());
       txtPassword.setText(userData.getPassword());
    }
    
    private Clip clickClip;
    
    private void playButtonClickSound() {
        try {
            if (clickClip != null && clickClip.isRunning()) {
                clickClip.stop();
            }
            File soundFile = new File("src/echoesoffateassets/button_click.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clickClip = AudioSystem.getClip();
            clickClip.open(audioStream);
            clickClip.setFramePosition(0);
            clickClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGameTitle = new javax.swing.JLabel();
        lblQuote1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblLogin = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        lblQuote = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameTitle.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 70)); // NOI18N
        lblGameTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblGameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameTitle.setText("Echoes of Fate");
        add(lblGameTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 470, 90));

        lblQuote1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblQuote1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote1.setText("Step into the past, before it erases you.");
        add(lblQuote1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 540, 40));

        txtUsername.setBackground(new java.awt.Color(83, 77, 169, 100));
        txtUsername.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 400, 40));

        lblLogin.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("LOGIN");
        add(lblLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 340, 110, 40));

        txtPassword.setBackground(new java.awt.Color(83, 77, 169, 100));
        txtPassword.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 400, 40));

        btnRegister.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnRegister.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register");
        add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 590, 160, 50));

        btnLogin.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnLogin.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 590, 140, 50));

        lblQuote.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        lblQuote.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote.setText("@2025 OOP 2 Project. All Rights Reserved");
        add(lblQuote, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 800, 430, 30));

        lblPassword.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password");
        add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, -1, -1));

        lblUsername1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblUsername1.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername1.setText("Username");
        add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, -1, -1));

        lblLoginFormBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblLoginFormBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblLoginFormBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblLoginFormBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoginFormBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblLoginFormBackground.setOpaque(true);
        add(lblLoginFormBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 770, 390));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/echoesoffatebg.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblGameTitle;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblQuote;
    private javax.swing.JLabel lblQuote1;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
