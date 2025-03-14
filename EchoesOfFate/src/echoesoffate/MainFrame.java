/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package echoesoffate;

/**
 *
 * @author Kean Saligue
 */


//This is the main game window mga pre

import javax.swing.*;
import java.awt.*;

public class MainFrame extends javax.swing.JFrame {
    
    //Mao nig maka ingon na maka balhin2 og screen sa isa ka JFrame
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    //Mao ni para sa validation username og password
    public UserData userData;
    
    public MainFrame() {
        
        // mao nig mga designs pre
        initComponents(); 
        
        //mga initializations 
        userData = new UserData();     
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // gi instantiate mga screen nasa uban files para magamit 
        LoginScreen loginScreen = new LoginScreen(this, userData);
        MainMenu mainMenu = new MainMenu(this, userData);
        RegisterScreen registerScreen = new RegisterScreen(this, userData);

        // mag add og screens sa CardLayout (which is like ang sudlanan sa mga screens)
        mainPanel.add(loginScreen, "Login");
        mainPanel.add(mainMenu, "Menu");
        mainPanel.add(registerScreen, "Register");

        // pra ag mogawas sa frame kay mga screens na naa sa card layout
        setContentPane(mainPanel);
        setVisible(true);
        
        //Mao nig maka automatic scale sa frame na mo fit sa Entire Screen JUICE!
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);  
        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Echoes of Fate");
        setResizable(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
     public void showScreen(String screenName) {
         
         //E update niya ag username og password text fields sa LoginScreen after mag register :D
         if (screenName.equals("Login")) {
        ((LoginScreen) mainPanel.getComponent(0)).updateTextFields();
    }       
         //E update niya ag lblMsg sa MainMenu after mag register :D
         if (screenName.equals("Menu")) {
        ((MainMenu) mainPanel.getComponent(1)).updateData();
    }
         
        //mao ni ag method para mag balhin2 og screen. Makita ni siya nimo sa mga constructors sa uban screen files.
        cardLayout.show(mainPanel, screenName);
    }
     
     //mao ni pra after mo register, ma update mga attributes sa userData.
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
