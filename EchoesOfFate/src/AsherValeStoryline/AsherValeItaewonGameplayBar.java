/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AsherValeStoryline;

import echoesoffate.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 *
 * @author User
 */
public class AsherValeItaewonGameplayBar extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeItaewonGameplayBar
     */
    
    private MainFrame frame; 
    
    private String[] SCENE1 = {
        "(The door creaks open as Asher steps inside, neon light casting shadows)",
        "The air smells of whiskey and smoke, jazz playing softly in the background.",
        "(The bartender wipes a glass, his eyes avoiding Asher’s)",
        "Bartender (gruffly): Haven’t seen you ‘round here before.",
        "Asher (calm but intent): Looking for Kieran Vale. He used to come here.",
        "(The bartender freezes, his grip tightening on the glass)",
        "Bartender: Lotta folks pass through. Hard to remember names.",
        "(He exhales, eyes darting away)",
        "Bartender: Yeah, Kieran was here. Sat at that corner booth. Pressing for answers.",
        "Asher (leaning in): Answers? What kind of answers?",
        "(The bartender hesitates, wiping the same spot on the glass)",
        "Bartender (nervously): Questions about things... things he shouldn’t have asked.",
        "He was waiting for someone. No one showed up. He left alone.",
        "Asher (intensely): Did he talk to anyone else? Anything suspicious?",
        "(The bartender looks away, his movements stiff)",
        "Bartender (quietly): Some things are better left alone.",
        "(The silence between them thickens, Asher’s suspicion growing)",
    };

    private int dialogueIndex = 0;
    private int charIndex = 0;
    private Timer timer;
    private Clip typewriterClip;

    public AsherValeItaewonGameplayBar(MainFrame frame) {
        this.frame = frame;
        initComponents();
        lblDialogue.setText("");
        
        lblDialogue.revalidate();
        lblDialogue.repaint();
        btnContinue.addActionListener(e -> advanceDialogue());
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                startDialogue();
            }
        });
        btnContinue.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "advance");
        btnContinue.getActionMap().put("advance", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advanceDialogue();
            }
        });
    }
    
    private Clip doorClip;

    private void playDoorSound() {
        try {
            if (doorClip != null && doorClip.isRunning()) {
                doorClip.stop();
            }
            File soundFile = new File("src/echoesoffateassets/door.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            doorClip = AudioSystem.getClip();
            doorClip.open(audioStream);
            doorClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void startDialogue() {
        playDoorSound();
        dialogueIndex = 0;
        showNextDialogue();
    }

    private void playTypewriterSound() {
        try {
            if (typewriterClip != null && typewriterClip.isRunning()) {
                typewriterClip.stop();
            }
            File soundFile = new File("src/echoesoffateassets/typings.wav");
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
    
    private void displayText(String text) {
        charIndex = 0;
        lblDialogue.setText("");

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < text.length()) {
                    lblDialogue.setText(lblDialogue.getText() + text.charAt(charIndex));
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

    private void showNextDialogue() {
        if (dialogueIndex < SCENE1.length) {
            displayText(SCENE1[dialogueIndex]); 
            dialogueIndex++;
        } else {
            lblDialogue.setText("End of dialogue.");
        }
    }

    private void advanceDialogue() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
            stopTypewriterSound();
            lblDialogue.setText(SCENE1[dialogueIndex - 1]);
        } else {
            if (dialogueIndex >= SCENE1.length) {
                frame.showScreen("AsherValeItaewonGameplayBar2");
                return;
            }
            showNextDialogue();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });
        add(btnContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 1430, 230));

        lblContinue.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblContinue.setForeground(new java.awt.Color(255, 255, 255));
        lblContinue.setText("Click to continue...");
        add(lblContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 750, -1, -1));

        lblDialogue.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
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
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/bar.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, -80, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnContinueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblLoginFormBackground;
    // End of variables declaration//GEN-END:variables
}