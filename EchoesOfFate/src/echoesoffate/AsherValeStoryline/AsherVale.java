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
    private String[] SCENE1 = {
        "March 16, 2025... 2:37 AM...",
        "(A dimly lit alley in Itaewon. Neon lights flicker. The air is thick with the scent of rain and cigarette smoke)",
        "(Kieran Vale walks alone, hands in his jacket pockets. His breath is visible in the cold night air)",
        "(His steps echo softly on the wet pavement, each footfall swallowed by the alley’s emptiness)",
        "Kieran (muttering): Should’ve just gone home...",
        "(A gust of wind rushes through the alley. The neon glow behind him flickers, then dies for half a second)",
        "(Kieran stops)",
        "Kieran: Hello?...",
        "(Silence...)",
        "(He turns his head slightly, scanning the alley. No one. Just the dark walls, the distant hum of the city)",
        "(Then, a shadow shifts)",
        "(A flicker of movement behind him)",
        "(Kieran barely has time to turn...)",
        "(A hand grabs his shoulder. A cold, sharp pain tears into his stomach)",
        "Kieran (sharp inhale): Ngh!",
        "(His body stiffens. His breath catches. His knees buckle)",
        "(He stumbles forward, one hand instinctively pressing against his abdomen)",
        "(His fingers meet something warm. Wet. Sticky)",
        "(He looks down, blood spills between his shaking fingers)",
        "Kieran (weakly): What...?",
        "(He turns his head, eyes locking onto a hooded figure)",
        "Kieran (gasping): No... wait...",
        "(His legs give out. He collapses onto the pavement)",
        "(His chest rises. Falls. Shudders)",
        "(The killer lingers, watching. Then, without a word, they disappear into the darkness)",
        "(The alley falls silent, except for the faint, dying echo of Kieran’s last breath)",
    };
    
    private String[] SCENE2 = {
        "(The alley remains still, the neon lights flickering faintly once more)",
        "(The city is alive-music, laughter, distant sirens. But in this alley, it’s suffocatingly quiet)",
        "(Asher Vale moves quickly, his breath uneven. His heart pounds in his chest)",
        "(His phone is in his hand. No new messages. No calls)",
        "Asher (under his breath): Where the hell are you, Kieran...?",
        "(He steps deeper into the alley. The neon lights barely reach past the entrance, leaving most of it in shadows)",
        "(Then, he sees something ahead. A shape on the ground)",
        "(His stomach tightens)",
        "(His pace quickens. Then, when he gets close enough, he stops cold)",
        "(Kieran lies motionless on the pavement. Blood smeared across the concrete)",
        "Asher: No...",
        "(His body moves before his mind catches up. He stumbles forward, dropping to his knees.)",
        "Asher (shaky): Kieran... hey! Can you hear me?!",
        "(He grabs Kieran’s shoulder, turning him slightly. His skin is too cold)",
        "(Asher’s breath catches. His chest tightens. No. No, no, no)",
        "(He presses two fingers to Kieran’s neck. Nothing.)",
        "Asher (barely a whisper): No… no, no, no...",
        "(His hands tremble. His vision blurs)",
        "(Then, a noise. Footsteps. Distant, but fast. Someone is running)",
        "(Asher’s head snaps up)",
        "(At the far end of the alley, a shadowy figure turns the corner, vanishing)",
        "(Asher's body tenses, but his legs refuse to move)",
        "(He’s still holding Kieran)",
        "(His mind screams at him to chase the bastard down. But he can’t)",
        "(Because this moment, this exact second—feels impossible)",
        "(The city keeps moving. The world keeps spinning. But Asher is frozen in place, trapped in a reality where his brother is gone)",
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
        new Timer(10000, new ActionListener() {
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
            // Display SCENE1 Dialogue
            displayText(SCENE1[dialogueIndex]);
        } else if (dialogueIndex - SCENE1.length < SCENE2.length) {
            // Transition To SCENE2
            if (dialogueIndex == SCENE1.length) {
                lblDialogue.setText("");
            }
            displayText(SCENE2[dialogueIndex - SCENE1.length]);
        } else {
            return; //Preventing Overflow
        }
    }

    private void advanceDialogue() {
        if (timer != null && timer.isRunning()) {
            timer.stop(); //Skip Animation
            stopTypewriterSound();
            if (dialogueIndex < SCENE1.length) {
                lblDialogue.setText(SCENE1[dialogueIndex]);
            } else if (dialogueIndex - SCENE1.length < SCENE2.length) {
                lblDialogue.setText(SCENE2[dialogueIndex - SCENE1.length]);
            }
        } else {
            dialogueIndex++;

            //Move To Scene2
            if (dialogueIndex == SCENE1.length) {
                lblDialogue.setText("");
                lblDialogue.revalidate();
                lblDialogue.repaint();
            }

            showNextDialogue(); //Move To Next Dialogue
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