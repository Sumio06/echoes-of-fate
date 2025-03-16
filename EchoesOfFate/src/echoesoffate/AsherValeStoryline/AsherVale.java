/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package echoesoffate.ashervalestoryline;

import echoesoffate.MainFrame;
import echoesoffate.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author User
 */

public class AsherVale extends javax.swing.JPanel {
     
    private MainFrame frame;
    private String[] dialogueLines = {
        "March 16, 2025... 2:37 AM...",
        "(A dimly lit alley in Itaewon. Neon lights flicker. The city hums in the background.)",
        "<html>Kieran Vale stumbles forward, clutching his stomach, blood spilling between his fingers. A hooded figure stands before him,<br> blade dripping red.</html>",
        "Asher: Who's there?!",
        "Asher: I should probably check it out...",
        "[End of Scene]"
    };
    
    private int dialogueIndex = 0;
    private int charIndex = 0;
    private Timer timer;
    private Clip typewriterClip;

    public AsherVale(MainFrame frame) {
        this.frame = frame;
        initComponents();
        lblDialogue.setText("");
        lblDialogue.revalidate();
        lblDialogue.repaint();
        //Typewriter Effect
        new Timer(7500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                showNextDialogue();
            }
        }).start();
        btnContinue.addActionListener(e -> advanceDialogue());
    }

    private void playTypewriterSound() {
        try {
            if (typewriterClip != null && typewriterClip.isRunning()) {
                typewriterClip.stop();
            }
            File soundFile = new File("src/echoesoffate/echoesoffateassets/typings.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            typewriterClip = AudioSystem.getClip();
            typewriterClip.open(audioStream);
            typewriterClip.setFramePosition(0);
            typewriterClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void stopTypewriterSound() {
        if (typewriterClip != null && typewriterClip.isRunning()) {
            typewriterClip.stop();
        }
    }
    
    private void showNextDialogue() {
        if (dialogueIndex < dialogueLines.length) {
            charIndex = 0;
            lblDialogue.setText(""); //Clear Text
            
            timer = new Timer(50, new ActionListener() { //Typewriter Effect
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (charIndex < dialogueLines[dialogueIndex].length()) {
                        lblDialogue.setText(lblDialogue.getText() + dialogueLines[dialogueIndex].charAt(charIndex));
                        if (charIndex % 3 == 0) {
                                playTypewriterSound();
                        }
                        charIndex++;
                    } else {
                        timer.stop();
                        stopTypewriterSound();
                    }
                }
            });
            timer.start();
        }
    }

    private void advanceDialogue() {
        if (timer != null && timer.isRunning()) {
            timer.stop(); // Skip Animation
            stopTypewriterSound(); // Stop the sound when skipping
            lblDialogue.setText(dialogueLines[dialogueIndex]); 
        } else {
            dialogueIndex++;
            showNextDialogue(); // Move To Next Dialogue
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnContinue.setBackground(new java.awt.Color(36, 43, 53, 100));
        btnContinue.setForeground(new java.awt.Color(255, 255, 255));
        btnContinue.setContentAreaFilled(false);
        add(btnContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 1430, 230));

        lblContinue.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        lblContinue.setForeground(new java.awt.Color(255, 255, 255));
        lblContinue.setText("Click to continue...");
        add(lblContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 750, -1, -1));

        lblDialogue.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        lblDialogue.setForeground(new java.awt.Color(255, 255, 255));
        lblDialogue.setText("[Dialogue]");
        add(lblDialogue, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 610, -1, -1));

        lblLoginFormBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblLoginFormBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblLoginFormBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblLoginFormBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoginFormBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblLoginFormBackground.setOpaque(true);
        add(lblLoginFormBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 1430, 230));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/loginandregisterbackground.jpg"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblLoginFormBackground;
    // End of variables declaration//GEN-END:variables
}