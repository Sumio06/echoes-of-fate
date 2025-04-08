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
public class AsherValeGameplayOutsideBar extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayOutsideBar
     */
    
    private MainFrame frame; 
    
    private String[] SCENE1 = {
        "(Asher watches a shadowy figure slip out of the bar, moving with purpose)",
        "(There’s something off about them, Asher’s instincts flare)",
        "Asher (to himself): Who’s that?"
    };
    
    private String[] SCENE2 = {
        "(He takes a step toward the door, but the figure glances back, noticing him)",
        "(In an instant, the figure bolts, disappearing into the dark alley)",
        "Asher (gritted teeth): Hey!",
        "(He quickly follows, but the figure is fast, slipping through the maze of streets)",
        "(Asher chases, but the figure’s lead is too great)",
        "(He slows down, looking around the alley. Nothing)",
        "(Then, he notices something: a crumpled piece of paper on the ground)",
        "(Asher picks it up and reads the words ‘Namdaemun Warehouse’ written on it)",
        "Asher (muttering): Namdaemun... what’s going on here?",
        "(He pockets the paper, a new lead forming in his mind)",
        "(Time to head to the warehouse)"
    };
    
    private String[] SCENE3 = {
        "(Asher stands in front of the warehouse, the cold air biting at his skin)",
        "Asher (muttering): This is it... the Namdaemun Warehouse",
        "(He looks around cautiously, making sure no one is watching)",
        "Asher (thinking to himself): I need to be careful. I don’t know who or what’s inside",
        "(He takes a deep breath and pushes the door open, stepping into the dark, musty interior)"
    };
    
    private String[] SCENE4 = {
        "(The door creaks as Asher steps inside, the air heavy with the scent of dust and mildew)",
        "(He hears faint sounds echoing through the vast, empty space of the warehouse)",
        "Asher (whispering): Hello? Is anyone here?",
        "(There’s no response. The warehouse is eerily quiet, save for the distant sound of something shifting)",
        "(He moves forward cautiously, every step echoing through the empty space)",
        "Asher (gritting his teeth): Someone’s been here recently... I need to find more clues",
        "(Suddenly, he spots something on the ground, a strange marking, maybe a symbol?)",
        "(Asher kneels down to inspect it closely)",
        "Asher (to himself): What the hell is this? It doesn’t look like anything I’ve seen before..."
    };

    private int dialogueIndex = 0;
    private int charIndex = 0;
    private Timer timer;
    private Clip typewriterClip;

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
    
    public AsherValeGameplayOutsideBar(MainFrame frame) {
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
    
    private boolean isDoorSoundPlayed = false;

    private void showNextDialogue() {
        if (dialogueIndex < SCENE1.length) {
            displayText(SCENE1[dialogueIndex]);
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length) {
            displayText(SCENE2[dialogueIndex - SCENE1.length]);

            if (dialogueIndex == SCENE1.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/alleyway_paper.png")));
            }

            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length) {
            displayText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/outside_warehouse.png")));
            }

            dialogueIndex++;
        }
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
                if (!isDoorSoundPlayed) {
                playDoorSound();
                isDoorSoundPlayed = true;
            }
            displayText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_warehouse.png")));
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
            } else if (dialogueIndex - 1 < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
                lblDialogue.setText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - 1]);
            }
        } else {
            if (dialogueIndex >= SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
                frame.showScreen("AsherValeGameplayWarehouse");
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
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/bar_shadowfigure.png"))); // NOI18N
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
