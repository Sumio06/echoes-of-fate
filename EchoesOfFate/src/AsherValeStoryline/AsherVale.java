/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AsherValeStoryline;

import echoesoffate.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

/**
 *
 * @author User
 */

public class AsherVale extends javax.swing.JPanel {
     
    private MainFrame frame;
    private String[] SCENE1 = {
        "One night in Itaewon, Seoul, South Korea",
        "March 16, 2025... 2:37 AM...",
        "(A dim alley in Itaewon. Neon lights flicker. Rain-slick pavement reflects the glow)",
        "(Kieran Vale walks alone, hands in pockets, breath visible in the cold air)",
        "Kieran (muttering): Should’ve gone home...",
        "(A gust of wind. Lights flicker—then darkness)",
        "Kieran: Hello?...",
        "(Silence. A shadow shifts. Movement—behind him)",
        "(A hand grips his shoulder. Cold steel sinks into his stomach)",
        "Kieran (gasping): Ngh...!",
        "(He staggers, fingers brushing warm, sticky blood)",
        "Kieran (weakly): No... wait...",
        "(He collapses. The killer watches—then vanishes into the dark)",
        "(Minutes later, Asher Vale enters the alley, heart pounding)",
        "Asher (under his breath): Where the hell are you, Kieran?",
        "(Then, he sees the body. His stomach drops)",
        "Asher: No...",
        "(He rushes forward, shaking Kieran. Skin—too cold)",
        "(Two fingers to the neck. Nothing)",
        "Asher (whispering): No... no, no...",
        "(His hands tremble. The world blurs)"
    };

    private String[] SCENE2 = {
        "(Footsteps. Fast. Someone running)",
        "(Asher's head snaps up. A shadow turns the corner)",
        "Asher: HEY!",
        "(He bolts, chasing the figure through twisting alleys)",
        "Asher (gritted teeth): You’re not getting away!",
        "(He gains on them—almost there—)",
        "(Then, the figure vanishes)",
        "(Asher skids to a stop, panting. No sign of them)"
    };

    private String[] SCENE3 = {
        "(Then, he sees it. A door. Rusted. Old)",
        "Asher: That wasn’t there before...",
        "(He grips the icy handle. Hesitates. Pushes it open)"
    };

    private String[] SCENE4 = {
        "(Inside: A dim corridor. Silent. Dusty. Empty)",
        "Asher (scanning): Where did he go?",
        "(He turns to leave. The alley outside—wrong)",
        "(Fog creeps unnaturally. Neon signs flicker, distorted)",
        "Asher (frowning): What the...?",
        "(The ground bends beneath him. A ringing noise—shrill—warping)",
        "(His breath catches. His vision twists)",
        "(Then, everything snaps back)"
    };

    private String[] SCENE5 = {
        "(The alley is... normal. But eerily silent)",
        "(His phone buzzes. He checks it.)",
        "(Screen reads: February 15, 2025. One month earlier)",
        "Asher (whispering): No...",
        "(The door behind him is gone)",
        "(Time hasn’t just shifted. It’s unraveled)"
    };

    private String[] SCENE6 = {
        "(A ringing phone. Not his)",
        "(He turns—an old payphone stands under flickering light)",
        "(His gut tightens. That wasn’t there before)",
        "(He steps forward. Picks up)",
        "Mysterious Voice: You have one month",
        "Asher: Who is this?!",
        "Mysterious Voice: Stop the murder. Or time will erase you",
        "(Static distorts the line)",
        "Asher: What’s happening?!",
        "Mysterious Voice: The clock is already ticking",
        "(Call ends. No beep. Just... silence)"
    };

    private String[] SCENE7 = {
        "(He blinks—the phone booth is gone)",
        "(His chest tightens. Then, his phone buzzes)",
        "(A new message. From Kieran)",
        "Kieran: 'Yo, you free? Kinda bored'",
        "(Asher stares. Kieran is alive. For now)",
        "(He scrolls past messages—a location pin)",
        "(Hours before Kieran’s death. But not this alley)",
        "Asher (muttering): Why was he here?",
        "(He exhales sharply. Focus. Retrace Kieran’s steps)",
        "(He tucks his phone away. Heads into the night)",
        "(The investigation begins)"
    };

    private String[] SCENE8 = {
        "(Asher walks through Itaewon, the city unfamiliar)",
        "(Across the street—familiar neon lights. A bar)",
        "Asher: You always did love this place...",
        "(He steps inside. If he's back in time, he needs to start somewhere)",
        "(Maybe the answers are waiting inside)"
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
        btnContinue.addActionListener(e -> advanceDialogue());
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

        timer = new Timer(50, new ActionListener() { //Typewriter effect
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
        } else if (dialogueIndex - SCENE1.length < SCENE2.length) {
            if (dialogueIndex == SCENE1.length) lblDialogue.setText("");
            lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/scene2_alleyway.png")));
            displayText(SCENE2[dialogueIndex - SCENE1.length]);
        } else if (dialogueIndex - SCENE1.length - SCENE2.length < SCENE3.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length) lblDialogue.setText("");
            lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/scene3_door.png")));
            displayText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);
        } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length < SCENE4.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length) lblDialogue.setText("");
            lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/scene4_corridor.png")));
            displayText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length]);
        } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length < SCENE5.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) lblDialogue.setText("");
            lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/scene5_time_distortion.png")));
            displayText(SCENE5[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length]);
        } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length < SCENE6.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) lblDialogue.setText("");
            lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/scene6_phone_booth.png")));
            displayText(SCENE6[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length]);
        } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length < SCENE7.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length) lblDialogue.setText("");
            lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/scene7_reality_shift.png")));
            displayText(SCENE7[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length]);
        } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length < SCENE8.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length) lblDialogue.setText("");
            lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/scene8_bar.png")));
            displayText(SCENE8[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length]);
        } else {
             frame.showScreen("AsherValeItaewonGameplay1");
        }
    }

    private void advanceDialogue() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
            stopTypewriterSound();

            if (dialogueIndex < SCENE1.length) {
                lblDialogue.setText(SCENE1[dialogueIndex]);
            } else if (dialogueIndex - SCENE1.length < SCENE2.length) {
                lblDialogue.setText(SCENE2[dialogueIndex - SCENE1.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length < SCENE3.length) {
                lblDialogue.setText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length < SCENE4.length) {
                lblDialogue.setText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length < SCENE5.length) {
                lblDialogue.setText(SCENE5[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length < SCENE6.length) {
                lblDialogue.setText(SCENE6[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length < SCENE7.length) {
                lblDialogue.setText(SCENE7[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length < SCENE8.length) {
                lblDialogue.setText(SCENE8[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length]);
            }
        } else {
            dialogueIndex++;

            int totalDialogues = SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + 
                                 SCENE5.length + SCENE6.length + SCENE7.length + SCENE8.length;
            if (dialogueIndex == totalDialogues - SCENE8.length) {
                lblDialogue.setText("");
                lblDialogue.revalidate();
                lblDialogue.repaint();
            }
            if (dialogueIndex >= totalDialogues) {
                frame.showScreen("AsherValeItaewonGameplay1");
                return;
            }
            showNextDialogue();
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
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/scene1_alleyway.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, -1, -1));
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