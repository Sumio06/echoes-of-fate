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
public class AsherValeGameplaySeoulToBusan extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplaySeoulToBusan
     */
    
    private MainFrame frame;
    
    private String[] SCENE1 = {
        "Asher’s mind raced as he left the Hanseong Complex.",
        "He needed answers, and they pointed to Busan",
        "The noise of Seoul’s streets faded as he reached the train station",
        "The city buzzed around him, but his focus was singular",
        "His footsteps echoed on the platform as he prepared to board"
    };
    
    private String[] SCENE2 = {
        "With a deep breath, Asher stepped onto the train platform",
        "He glanced at the train, its destination clear: Busan",
        "The sound of the train doors opening felt like an invitation",
        "Asher boarded the train, settling into a quiet seat",
        "The hum of the tracks beneath him brought a sense of calm"
    };
    
    private String[] SCENE3 = {
        "Asher gazed out the window as the city faded away",
        "The train sped past rolling hills, fields, and distant shores",
        "The afternoon sun bathed the world in golden light",
        "His thoughts drifted, memories of simpler days with Kieran",
        "'Remember that day, Kieran?' he wondered softly",
        "The peaceful scene outside only reminded him of what was lost"
    };
    
    private String[] SCENE4 = {
        "As the sun began to set, vibrant colors filled the sky",
        "The light reflected off the horizon, making Asher's heart ache",
        "It was a time long past, when everything felt easier",
        "'We didn't have to chase shadows back then,' he thought",
        "But there was no turning back now. Only forward to Busan",
        "As the train rumbled on, his resolve"
    };
    
    private String[] SCENE5 = {
        "Asher stepped off the train, boots hitting the pavement of Busan’s station",
        "The city, once quieter, greeted him with a foggy haze and the faint scent of saltwater",
        "Busan, now a place thick with secrets, seemed different, like it had been waiting for him",
        "The clues led him here, and every step felt heavier with the weight of the mystery",
        "He scanned the bustling streets, feeling the pressure mount as he moved towards the docks",
        "Each moment pulled him closer to Kieran’s killer. There was no going back now"
    };
    
    private String[] SCENE6 = {
        "Asher’s destination was clear—a rundown apartment on the edge of town",
        "The address had surfaced in his investigation, promising more answers",
        "The busy city faded as he walked deeper into quieter, darker streets",
        "With every step, the tension inside him grew; he was getting closer",
        "The building loomed ahead, a forgotten structure filled with uncertainty",
        "Asher's hand tightened around the strap of his bag. He was almost there"
    };
    
    private String[] SCENE7 = {
        "(Asher steps inside. The door creaks behind him)",
        "Asher (quietly): You were here, Kieran...",
        "(A rustle. Not the wind. Just memory)",
        "(He sets down the bloodstained letter from Seoul—stares at the room like it might speak)",
        "Asher: Let’s find what you left behind"
    };

    private int dialogueIndex = 0;
    private int charIndex = 0;
    private Timer timer;
    private Clip typewriterClip;
    
    public AsherValeGameplaySeoulToBusan(MainFrame frame) {
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

    private void showNextDialogue() {
        if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) {
             frame.afterTrainRideBusan();
    }        
        
        if (dialogueIndex < SCENE1.length) {
            displayText(SCENE1[dialogueIndex]);
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length) {
            displayText(SCENE2[dialogueIndex - SCENE1.length]);

            if (dialogueIndex == SCENE1.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/entering_train.png")));
            }

            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length) {
            displayText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/sunset_train.png")));
            }

            dialogueIndex++;
        }
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
            displayText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/evening_train.png")));
            }
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) {
            displayText(SCENE5[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/busan_station.png")));
            }

            dialogueIndex++;
        }
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length) {
            displayText(SCENE6[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/apartment.png")));
            }

            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length) {
            displayText(SCENE7[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length]);

            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_apartment.png")));
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
            }
        } else {
            if (dialogueIndex >= SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length) {
                frame.showScreen("AsherValeGameplayApartment");
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
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/seoul_station.png"))); // NOI18N
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
