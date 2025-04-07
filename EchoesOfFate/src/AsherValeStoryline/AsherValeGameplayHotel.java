/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AsherValeStoryline;

import echoesoffate.MainFrame;
import java.awt.Color;
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
public class AsherValeGameplayHotel extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayHotel
     */
    
    private MainFrame frame;
    
    public AsherValeGameplayHotel(MainFrame frame) {
        this.frame = frame;
        initComponents();
        
        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblKeyCheck.setVisible(false);
        lblMirrorCheck.setVisible(false);
        lblGlovesCheck.setVisible(false);
        lblGlassCheck.setVisible(false);
        lblLampCheck.setVisible(false);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                startGameplay();
            }
        });
    }
    
    private Timer typewriterTimer;
    private int charIndex = 0;
    private String fullText;
    private Clip typewriterClip;
    private int cluesFound = 0; 

    public void startGameplay() {
        
        lblObjective.setText("");
        lblObjective1.setText("");
        lblCluesFound.setText("");
        
        playTypewriterEffect("Objective:", "Look for evidences...", new Runnable() {
            @Override
            public void run() {
                playClueCountTypewriterEffect();
            }
        });
    }

    private void playClueCountTypewriterEffect() {
        final String clueText = "Clues Found: 0/5";
        final int[] charIndex = {0};
        lblCluesFound.setForeground(Color.RED);
        lblCluesFound.setText("");

        playTypewriterSound();

        Timer clueTypewriterTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex[0] < clueText.length()) {
                    lblCluesFound.setText(lblCluesFound.getText() + clueText.charAt(charIndex[0]));
                    charIndex[0]++;
                } else {
                    ((Timer)e.getSource()).stop();
                    stopTypewriterSound();
                }
            }
        });

        clueTypewriterTimer.start();
    }
    
    private void playTypewriterEffect(String firstText, String secondText, Runnable onComplete) {
        fullText = firstText;
        charIndex = 0;
        lblObjective.setText("");
        lblObjective1.setText("");

        playTypewriterSound();

        typewriterTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < fullText.length()) {
                    lblObjective.setText(lblObjective.getText() + fullText.charAt(charIndex));
                    charIndex++;
                } else {
                    typewriterTimer.stop();
                    stopTypewriterSound();
                    javax.swing.SwingUtilities.invokeLater(() -> startSecondText(secondText, onComplete));
                }
            }
        });

        typewriterTimer.start();
    }

    private void startSecondText(String secondText, Runnable onComplete) {
        fullText = secondText;
        charIndex = 0;
        playTypewriterSound();

        typewriterTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < fullText.length()) {
                    lblObjective1.setText(lblObjective1.getText() + fullText.charAt(charIndex));
                    charIndex++;
                } else {
                    typewriterTimer.stop();
                    stopTypewriterSound();
                    if (onComplete != null) {
                        onComplete.run();
                    }
                }
            }
        });

        typewriterTimer.start();
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
            typewriterClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopTypewriterSound() {
        if (typewriterClip != null && typewriterClip.isRunning()) {
            typewriterClip.stop();
        }
    }
    
    private boolean isDialogueComplete = false;  
    private boolean isSkipping = false;

    private String[] dialogueLines;
    private int dialogueIndex = 0;

    private void playDialogueTypewriterEffect(String[] lines) {
        dialogueLines = lines;
        dialogueIndex = 0;

        lblDialogue.setVisible(true);
        btnContinue.setVisible(true);
        lblContinue.setVisible(true);
        lblLoginFormBackground.setVisible(true);

        showNextLine();
    }
    
    private void showNextLine() {
        if (dialogueIndex < dialogueLines.length) {
            String currentLine = dialogueLines[dialogueIndex];

            if (currentLine.equals("N")) {
                stopBlinkingEffect();

                javax.swing.SwingUtilities.invokeLater(() -> {
                    frame.showScreen("AsherValeGameplayOutsideHotel");
                });
            } else {
                playTypewriterEffect(currentLine);
            }
            dialogueIndex++;
        } else {
            lblDialogue.setVisible(false);
            btnContinue.setVisible(false);
            lblContinue.setVisible(false);
            lblLoginFormBackground.setVisible(false);
            stopTypewriterSound();
        }
    }
    
    private Timer blinkTimer;
    private boolean isBlinking = false;

    private void startBlinkingEffect() {
        if (!isBlinking) {
            isBlinking = true;
            
            blinkTimer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lblObjective.setVisible(!lblObjective.isVisible());
                }
            });
            blinkTimer.start();
        }
    }

    private void stopBlinkingEffect() {
        if (blinkTimer != null) {
            blinkTimer.stop();
            lblObjective.setVisible(true);
        }
    }


    private void playTypewriterEffect(String text) {
        lblDialogue.setText("");
        charIndex = 0;
        fullText = text;
        playTypewriterSound();

        isDialogueComplete = false;
        isSkipping = false;

        typewriterTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSkipping) {
                    lblDialogue.setText(fullText);
                    typewriterTimer.stop();
                    stopTypewriterSound();
                    isDialogueComplete = true;
                } else {
                    if (charIndex < fullText.length()) {
                        lblDialogue.setText(lblDialogue.getText() + fullText.charAt(charIndex));
                        charIndex++;
                    } else {
                        typewriterTimer.stop();
                        stopTypewriterSound();
                        isDialogueComplete = true;
                    }
                }
            }
        });

        typewriterTimer.start();
    }
    
    private boolean glassClicked = false;
    private boolean mirrorClicked = false;
    private boolean keyClicked = false;
    private boolean lampClicked = false;
    private boolean glovesClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (glassClicked && mirrorClicked && glovesClicked && keyClicked && glovesClicked) {
            if (!isObjectiveComplete) {
                lblObjective.setText("Objective Complete");
                lblObjective.setForeground(new java.awt.Color(51, 255, 0));
                lblObjective1.setText("");

                playSolvedSound();

                startDialogueAfterObjectiveComplete();
                isObjectiveComplete = true;
            }
        }
    }
    
    private void playSolvedSound() {
        try {
            File soundFile = new File("src/echoesoffateassets/solved.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip solvedClip = AudioSystem.getClip();
            solvedClip.open(audioStream);
            solvedClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startDialogueAfterObjectiveComplete() {
        stopTypewriterSound();

        lblDialogue.setVisible(true);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(true);

        startBlinkingEffect();
        
        String[] lines;

        switch(lastClicked) {
            case "lamp":
                lines = new String[] {
                    "A shattered lamp with a hidden compartment...",
                    "Inside the cracked base lies a half-burned note: 'He’s watching. Don’t trust anyone, not even Hale'",
                    "A warning. Someone else was involved—someone dangerous.",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "key":
                lines = new String[] {
                    "A brass key with etched coordinates...",
                    "It was hidden in a drawer, tucked between pages of a journal",
                    "The coordinates point to a location outside the city, a bunker",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "gloves":
                lines = new String[] {
                    "A torn pair of gloves stained with ink and oil...",
                    "Found under the mattress, the fingertips worn from frantic use",
                    "Ink from a manifest, oil from machinery—these weren’t just gloves. They were part of something bigger",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "glass":
                lines = new String[] {
                    "A blood-streaked glass shard...",
                    "Jagged and fresh, found near the desk. The blood still damp",
                    "There was a fight here. Someone tried to escape, and someone got hurt",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "mirror":
                lines = new String[] {
                    "A shattered, bloodied mirror fragment...",
                    "Its cracked surface reflects a face—distorted, almost familiar",
                    "The scratches suggest a struggle. The reflection hints at something Kieran saw—something terrifying",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            default:
                lines = new String[] {
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;
        }

        playDialogueTypewriterEffect(lines);
    }

    private Clip foundClip;
    
    private void playFoundSound() {
        try {
            if (foundClip != null && foundClip.isRunning()) {
                foundClip.stop();
            }
            File soundFile = new File("src/echoesoffateassets/found.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            foundClip = AudioSystem.getClip();
            foundClip.open(audioStream);
            foundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateClueCount() {
        lblCluesFound.setText("Clues Found: " + cluesFound + "/5");

        if (cluesFound < 5) {
            lblCluesFound.setForeground(Color.RED);
        } else {
            lblCluesFound.setForeground(new java.awt.Color(51, 255, 0));
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

        lblCluesFound = new javax.swing.JLabel();
        lblObjectiveBackground = new javax.swing.JLabel();
        lblObjective1 = new javax.swing.JLabel();
        lblObjective = new javax.swing.JLabel();
        lblCluesFoundBackground = new javax.swing.JLabel();
        btnGlass = new javax.swing.JButton();
        btnLamp = new javax.swing.JButton();
        btnMirror = new javax.swing.JButton();
        btnGloves = new javax.swing.JButton();
        btnKey = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblGlassCheck = new javax.swing.JLabel();
        lblMirrorCheck = new javax.swing.JLabel();
        lblKeyCheck = new javax.swing.JLabel();
        lblLampCheck = new javax.swing.JLabel();
        lblGlovesCheck = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCluesFound.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblCluesFound.setForeground(new java.awt.Color(51, 255, 0));
        lblCluesFound.setText("[Text]");
        add(lblCluesFound, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 40, -1, -1));

        lblObjectiveBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblObjectiveBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblObjectiveBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblObjectiveBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObjectiveBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblObjectiveBackground.setOpaque(true);
        add(lblObjectiveBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 30, 330, 50));

        lblObjective1.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblObjective1.setForeground(new java.awt.Color(51, 255, 0));
        lblObjective1.setText("[Text]");
        add(lblObjective1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        lblObjective.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblObjective.setForeground(new java.awt.Color(255, 255, 255));
        lblObjective.setText("[Text]");
        add(lblObjective, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        lblCluesFoundBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblCluesFoundBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblCluesFoundBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblCluesFoundBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCluesFoundBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblCluesFoundBackground.setOpaque(true);
        add(lblCluesFoundBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 330, 120));

        btnGlass.setContentAreaFilled(false);
        btnGlass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGlassActionPerformed(evt);
            }
        });
        add(btnGlass, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 610, 60, 60));

        btnLamp.setContentAreaFilled(false);
        btnLamp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLampActionPerformed(evt);
            }
        });
        add(btnLamp, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 730, 120, 120));

        btnMirror.setContentAreaFilled(false);
        btnMirror.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMirrorActionPerformed(evt);
            }
        });
        add(btnMirror, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 600, 90, 80));

        btnGloves.setContentAreaFilled(false);
        btnGloves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGlovesActionPerformed(evt);
            }
        });
        add(btnGloves, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 730, 90, 90));

        btnKey.setContentAreaFilled(false);
        btnKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeyActionPerformed(evt);
            }
        });
        add(btnKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 50, 50));

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

        lblGlassCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblGlassCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 620, -1, -1));

        lblMirrorCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblMirrorCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 620, -1, -1));

        lblKeyCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblKeyCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, -1, -1));

        lblLampCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblLampCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 760, -1, -1));

        lblGlovesCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblGlovesCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 760, -1, -1));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_room.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -70, 1600, -1));
    }// </editor-fold>//GEN-END:initComponents

    private String lastClicked = "";
    
    private void btnLampActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLampActionPerformed
        lblLampCheck.setVisible(true);
        lampClicked = true;
        lastClicked = "lamp";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A shattered lamp with a hidden compartment...",
            "Inside the cracked base lies a half-burned note: 'He’s watching. Don’t trust anyone, not even Hale'",
            "A warning. Someone else was involved—someone dangerous."
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnLamp.setEnabled(false);
    }//GEN-LAST:event_btnLampActionPerformed

    private void btnMirrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMirrorActionPerformed
        lblMirrorCheck.setVisible(true);
        mirrorClicked = true;
        lastClicked = "mirror";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A shattered, bloodied mirror fragment...",
            "Its cracked surface reflects a face—distorted, almost familiar",
            "The scratches suggest a struggle. The reflection hints at something Kieran saw—something terrifying"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnMirror.setEnabled(false);
    }//GEN-LAST:event_btnMirrorActionPerformed

    private void btnGlassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGlassActionPerformed
        lblGlassCheck.setVisible(true);
        glassClicked = true;
        lastClicked = "glass";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A blood-streaked glass shard...",
            "Jagged and fresh, found near the desk. The blood still damp",
            "There was a fight here. Someone tried to escape, and someone got hurt"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnGlass.setEnabled(false);
    }//GEN-LAST:event_btnGlassActionPerformed

    private void btnGlovesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGlovesActionPerformed
        lblGlovesCheck.setVisible(true);
        glovesClicked = true;
        lastClicked = "gloves";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A torn pair of gloves stained with ink and oil...",
            "Found under the mattress, the fingertips worn from frantic use",
            "Ink from a manifest, oil from machinery—these weren’t just gloves. They were part of something bigger"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnGloves.setEnabled(false);
    }//GEN-LAST:event_btnGlovesActionPerformed

    private void btnKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeyActionPerformed
        lblKeyCheck.setVisible(true);
        keyClicked = true;
        lastClicked = "key";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A brass key with etched coordinates...",
            "It was hidden in a drawer, tucked between pages of a journal",
            "The coordinates point to a location outside the city, a bunker"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnKey.setEnabled(false);
    }//GEN-LAST:event_btnKeyActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (isDialogueComplete || isSkipping) {
            showNextLine();
        } else {
            isSkipping = true;
        }
    }//GEN-LAST:event_btnContinueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnGlass;
    private javax.swing.JButton btnGloves;
    private javax.swing.JButton btnKey;
    private javax.swing.JButton btnLamp;
    private javax.swing.JButton btnMirror;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblGlassCheck;
    private javax.swing.JLabel lblGlovesCheck;
    private javax.swing.JLabel lblKeyCheck;
    private javax.swing.JLabel lblLampCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblMirrorCheck;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    // End of variables declaration//GEN-END:variables
}
