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
public class AsherValeGameplayAfterRooftop extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayAfterRooftop
     */
    
    private MainFrame frame;
    
    public AsherValeGameplayAfterRooftop(MainFrame frame) {
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
    
    private int dialogueIndex = 0;
    private int charIndex = 0;
    private Timer timer;
    private Clip typewriterClip;
    
    
    private String[] SCENE1 = {
        "(Asher steps out onto the quiet, dimly lit street from the building’s entrance)",
        "(The night air is cool, the sound of distant traffic echoes through the alley)",
        "Asher (to himself): The port’s just down the road. Need to keep a low profile",
        "(He walks down the sidewalk, his footsteps the only sound in the empty street)",
        "(The neon signs flicker as he passes, casting faint glows across his face)",
        "Asher (focused): No turning back. It’s all or nothing now"
    };
    
    private String[] SCENE2 = {
        "Asher (to himself): Almost there...",
        "(He enters the port area, the smell of salt and metal filling the air)",
        "(Asher looks around, the port is quiet, save for the occasional creak of boats in the harbor)",
        "Asher (decisive): This is where everything starts"
    };

    private String[] SCENE3 = {
        "(Asher boards the ferry, the engine humming softly in the night)",
        "(He stands at the railing, staring at the dark, moonlit water as the port fades behind him)",
        "Asher (to himself): Jeju... the final step",
        "(The cool night air brushes against him as the ferry glides through the water)",
        "(The rhythmic motion is calming, but Asher stays focused, eyes fixed on the horizon)",
        "Asher (determined): No turning back now. I’m ready"
    };
    
    private String[] SCENE4 = {
        "(Asher steps off the boat, feeling the cool island breeze against his skin)",
        "(He looks around, Jeju’s calm streets and unfamiliar scenery greeting him)",
        "Asher (to himself): I’ve made it... now to find what I came here for",
        "(He begins walking off the pier, the sounds of the ocean fading behind him as the island’s quiet envelops him)",
        "Asher (resolute): Let’s see what Jeju has in store"
    };
    
    private String[] SCENE5 = {
        "(Asher walks the quiet streets, the island’s weight pressing in)",
        "(The sound of the ocean fades as he focuses on the hotel ahead)",
        "Asher (thinking): 'Hotel Haneul... Kieran was here. The next step.'",
        "(Neon signs flicker briefly as he moves past alleyways)",
        "(Asher’s boots echo on wet pavement, breath visible in the cool air)",
        "Narrator: 'Hotel Haneul, ‘sky.’ A fitting name for what’s to come'",
        "(The hotel looms ahead, reading Hotel Haneul)"
    };
    
    private String[] SCENE6 = {
        "Asher (thinking): 'This is it. The answers are inside'",
        "(He steps inside, the door creaking, a scent of old wood)",
        "(The lobby is quiet, dimly lit)",
        "Asher (thinking): 'Someone’s maintaining this place'",
        "Receptionist: 'Good evening. How may I help you?'",
        "Asher: 'I’m looking for Kieran Vale. He stayed here recently'",
        "Receptionist: 'Yes, a week ago. Quiet guest, room near the back'",
        "Asher (thinking): 'Near the stairwell... I’ll check'",
        "(Asher heads down the hallway)"
    };
    
    private String[] SCENE7 = {
        "Asher (thinking): 'Let’s see what’s inside'",
        "(He stops in front of Room 203, door slightly ajar)",
        "Asher (thinking): 'This is it'",
        "(He pushes the door open, stepping inside)",
    };
    
    private String[] SCENE8 = {
        "Asher (thinking): 'Let’s see what’s inside'",
        "(He stops in front of Room 203, door slightly ajar)",
        "Asher (thinking): 'This is it'",
        "(He pushes the door open, stepping inside)",
        "Asher (thinking): 'It’s quiet... too quiet.'",
        "(The room is dim, with a faint smell of old wood and dust)",
        "(Asher steps inside cautiously, scanning the room)",
        "Asher (thinking): 'Something feels off. I need to be careful.'",
        "(He walks further into the room, the door creaking shut behind him)",
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
        if (dialogueIndex < SCENE1.length) {
            displayText(SCENE1[dialogueIndex]);
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length) {
            displayText(SCENE2[dialogueIndex - SCENE1.length]);

            if (dialogueIndex == SCENE1.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_busan_port.png")));
            }

            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length) {
            displayText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/leaving_busan.png")));
            }

            dialogueIndex++;
        }
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
            displayText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/jeju_arrival.png")));
            }
            dialogueIndex++;
        }
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) {
            displayText(SCENE5[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/hotel_haneul.png")));
            }

            dialogueIndex++;
        }
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length) {
            displayText(SCENE6[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/hotel_lobby.png")));
            }

            dialogueIndex++;
        }
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length) {
            displayText(SCENE7[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/outside_room.png")));
            }

            dialogueIndex++;
        }
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length + SCENE8.length) {
            displayText(SCENE8[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_room.png")));
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
            } else if (dialogueIndex - 1 < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) {
                lblDialogue.setText(SCENE5[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - 1]);
            } else if (dialogueIndex - 1 < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length) {
                lblDialogue.setText(SCENE6[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - 1]);
            } else if (dialogueIndex - 1 < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length) {
                lblDialogue.setText(SCENE7[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - 1]);
            } else if (dialogueIndex - 1 < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length + SCENE8.length) {
                lblDialogue.setText(SCENE8[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length - 1]);
            }
        } else {
            if (dialogueIndex >= SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length + SCENE8.length) {
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
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/busan_street.png"))); // NOI18N
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
