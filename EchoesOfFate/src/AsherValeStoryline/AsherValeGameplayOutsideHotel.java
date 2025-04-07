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
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class AsherValeGameplayOutsideHotel extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayAfterHotel
     */
    
    private MainFrame frame;
    
    private int dialogueIndex = 0;
    private int charIndex = 0;
    private Timer timer;
    private Clip typewriterClip;
    
    public AsherValeGameplayOutsideHotel(MainFrame frame) {
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
    
    private String[] SCENE1 = {
        "(Asher exits the hotel, the morning sun hitting his face)",
        "(The street is quiet, bathed in soft golden light)",
        "(Cars pass occasionally. A breeze moves scattered papers across the sidewalk)",
        "(Asher walks alone, the key heavy in his pocket)",
        "(He turns the corner, leaving the hotel behind him)"
    };
    
    private String[] SCENE2 = {
        "(Asher stands before a massive, gnarled tree deep in the woods)",
        "(Its roots twist over a narrow opening, half-hidden by leaves and earth)",
        "(The entrance looks more like a forgotten cave than a bunker—natural, secretive)",
        "(Asher brushes away the foliage, revealing a faint metallic outline in the dirt)",
        "(He pulls out the brass key, its edges cool in his hand)",
        "(Asher breathes deeply, then crouches to enter the dark passage beneath the tree)"
    };

    private String[] SCENE3 = {
        "(The door creaks open, revealing a narrow staircase descending into darkness)",
        "(Asher steps inside, each footfall echoing down the corridor)",
        "(Dust floats in the air, lit by a flickering ceiling light)",
        "(Shelves line the walls—papers, maps, photos pinned with red thread)",
        "(In the center: a table with scattered documents... and another locked drawer)"
    };
    
    public void startDialogue() {
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
        if(dialogueIndex == SCENE1.length){
            frame.afterHotel();
        }
        
        if (dialogueIndex < SCENE1.length) {
            displayText(SCENE1[dialogueIndex]);
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length) {
            displayText(SCENE2[dialogueIndex - SCENE1.length]);

            if (dialogueIndex == SCENE1.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/outside_bunker.png")));
            }

            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length) {
            displayText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_bunker.png")));
            }

            dialogueIndex++;
        }
        else {
            lblDialogue.setText("End of dialogue.");
        }
    }
    
    private void advanceDialogue() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
            stopTypewriterSound();
            if (dialogueIndex - 1 < SCENE1.length) {
                lblDialogue.setText(SCENE1[dialogueIndex - 1]);
            } else if (dialogueIndex - 1 < SCENE1.length + SCENE2.length) {
                lblDialogue.setText(SCENE2[dialogueIndex - SCENE1.length - 1]);
            } else if (dialogueIndex - 1 < SCENE1.length + SCENE2.length + SCENE3.length) {
                lblDialogue.setText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length - 1]);
            }
        } else {
            if (dialogueIndex >= SCENE1.length + SCENE2.length + SCENE3.length) {
                frame.showScreen("AsherValeGameplayBunker");
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
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/jeju_street.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -70, 1600, -1));
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
