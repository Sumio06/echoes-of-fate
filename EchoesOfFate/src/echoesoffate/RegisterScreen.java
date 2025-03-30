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

public class RegisterScreen extends javax.swing.JPanel {
    private UserData userData;
    private MainFrame frame;
    
    public RegisterScreen(MainFrame frame, UserData userData) {
        this.frame = frame;
        this.userData = userData;
        initComponents();
        btnSubmit.addActionListener(e -> registerUser());
        btnBack.addActionListener(e -> frame.showScreen("Login"));
    }
    
    private void registerUser() {
       
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Check If Field Are Empty
        if (username.isEmpty() || password.isEmpty() || username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }   
        if (username.equals("Username") || password.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Update userData to MainFrame
        frame.setUserData(username, password);
        
        //Test
        System.out.println("Registered: " + username + " / " + password);
        
        //Go To Login Screen If userData is updated
        frame.showScreen("Login");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGameTitle = new javax.swing.JLabel();
        lblQuote2 = new javax.swing.JLabel();
        lblQuote = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        lblLogin = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameTitle.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 70)); // NOI18N
        lblGameTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblGameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameTitle.setText("Echoes of Fate");
        add(lblGameTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 470, 90));

        lblQuote2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblQuote2.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote2.setText("Step into the past, before it erases you.");
        add(lblQuote2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 540, 40));

        lblQuote.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        lblQuote.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote.setText("@2025 OOP 2 Project. All Rights Reserved");
        add(lblQuote, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 800, 430, 30));

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

        lblLogin.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("REGISTER");
        add(lblLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 340, 110, 40));

        lblUsername1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblUsername1.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername1.setText("Username");
        add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, -1, -1));

        lblPassword.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password");
        add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, -1, -1));

        btnBack.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnBack.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 590, 140, 50));

        btnSubmit.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnSubmit.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 590, 160, 50));

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
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblGameTitle;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblQuote;
    private javax.swing.JLabel lblQuote2;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}