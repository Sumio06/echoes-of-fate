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

public class RegisterScreen extends javax.swing.JPanel {
    private UserData userData;
    private MainFrame frame;
    
    public RegisterScreen(MainFrame frame, UserData userData) {
        this.frame = frame;
        this.userData = userData;
        initComponents();
        
        
        btnSubmit.addActionListener(e -> registerUser());
       
    }
    
    private void registerUser() {
       
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty() || username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }   
        if (username.equals("Username") || password.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        // ma update ang userData instance sa MainFrame
        frame.setUserData(username, password);
        
        
        // testing
        System.out.println("Registered: " + username + " / " + password);
        
        // balhin to login screen og ma update ang userData sa og mainmenu
        frame.showScreen("Login");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGameTitle = new javax.swing.JLabel();
        lblQuote2 = new javax.swing.JLabel();
        lblQuote1 = new javax.swing.JLabel();
        lblQuote = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        lblUsername1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameTitle.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 70)); // NOI18N
        lblGameTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblGameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameTitle.setText("Echoes of Fate");
        add(lblGameTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 770, 90));

        lblQuote2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblQuote2.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote2.setText("Step into the past, before it erases you.");
        add(lblQuote2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 540, 40));

        lblQuote1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        lblQuote1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote1.setText("Register");
        add(lblQuote1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 760, 50));

        lblQuote.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        lblQuote.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote.setText("@2025 OOP 2 Project. All Rights Reserved");
        add(lblQuote, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 800, 430, 30));

        btnSubmit.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnSubmit.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 550, 220, 50));

        lblUsername1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblUsername1.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername1.setText("Username");
        add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, -1, -1));

        txtUsername.setBackground(new java.awt.Color(83, 77, 169, 100));
        txtUsername.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 390, 360, 40));

        lblPassword.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password");
        add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 470, -1, -1));

        txtPassword.setBackground(new java.awt.Color(83, 77, 169, 100));
        txtPassword.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 460, 360, 40));

        lblLoginFormBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblLoginFormBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblLoginFormBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblLoginFormBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoginFormBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblLoginFormBackground.setOpaque(true);
        add(lblLoginFormBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 800, 380));

        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/loginandregisterbackground.jpg"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
       
        
        
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblGameTitle;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblQuote;
    private javax.swing.JLabel lblQuote1;
    private javax.swing.JLabel lblQuote2;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
