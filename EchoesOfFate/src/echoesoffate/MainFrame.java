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
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    
    
    public MainFrame() {
        initComponents();
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Initialize CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

         // Create screens
        LoginScreen loginScreen = new LoginScreen(this);
        MainMenu mainMenu = new MainMenu(this);

        // Add screens to the CardLayout
        mainPanel.add(loginScreen, "Login");
        mainPanel.add(mainMenu, "Menu");

        // Set main panel as content pane
        setContentPane(mainPanel);
        setVisible(true);
  
        
        //Mao nig maka automatic scale sa frame na mo fit sa Entire Screen
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
        cardLayout.show(mainPanel, screenName);
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
