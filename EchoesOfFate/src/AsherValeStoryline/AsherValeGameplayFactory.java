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
public class AsherValeGameplayFactory extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayFactory
     */
    
    private MainFrame frame;
    
    public AsherValeGameplayFactory(MainFrame frame) {
        this.frame = frame;
        initComponents();
        
        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblLighterCheck.setVisible(false);
        lblLetterCheck.setVisible(false);
        lblGlovesCheck.setVisible(false);
        lblCupCheck.setVisible(false);
        lblKnifeCheck.setVisible(false);

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

        Timer clueTypewriterTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex[0] < clueText.length()) {
                    lblCluesFound.setText(lblCluesFound.getText() + clueText.charAt(charIndex[0]));
                    charIndex[0]++;
                } else {
                    ((Timer)e.getSource()).stop();
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
                    frame.showScreen("AsherValeGameplaySeoulToBusan");
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
    
    private boolean lighterClicked = false;
    private boolean letterClicked = false;
    private boolean glovesClicked = false;
    private boolean cupClicked = false;
    private boolean knifeClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (lighterClicked && letterClicked && glovesClicked && cupClicked && knifeClicked) {
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
            case "lighter":
                lines = new String[] {
                    "A killer’s lighter...",
                    "It’s a silver cigarette lighter with the initials 'HL' engraved on it, covered in blood",
                    "It smells faintly of gasoline, suggesting it may have been used to set a fire or destroy evidence after a kill",
                    "This lighter is commonly used by a known assassin operating in Busan",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "letter":
                lines = new String[] {
                    "A bloodstained letter...",
                    "It’s crumpled and found on the floor, containing a message with instructions to meet in Busan",
                    "The letter indicates a connection to the city's underground operations",
                    "This could be a crucial lead, connecting the operation to Busan",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "gloves":
                lines = new String[] {
                    "A pair of blood-stained gloves...",
                    "They are black and found near the exit of the factory, stained with blood",
                    "They were likely used during the killing, but there’s no indication of a specific city involved",
                    "This could provide a lead to trace the killer",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "cup":
                lines = new String[] {
                    "Hale’s espresso cup...",
                    "It’s discarded, with Hale’s coffee shop logo visible, found among crates",
                    "A napkin with a phone number written on it connects to someone involved in the killing operation",
                    "This could be the next piece in the puzzle",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "knife":
                lines = new String[] {
                    "A bloodstained knife...",
                    "It’s large, covered in fresh blood, found in a corner of the factory",
                    "It seems to have been used in a recent killing, but there’s no direct link to a specific location",
                    "This could be important",
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
        lblLighterCheck = new javax.swing.JLabel();
        btnKnife = new javax.swing.JButton();
        btnCup = new javax.swing.JButton();
        btnGloves = new javax.swing.JButton();
        btnLetter = new javax.swing.JButton();
        btnLighter = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblKnifeCheck = new javax.swing.JLabel();
        lblCupCheck = new javax.swing.JLabel();
        lblGlovesCheck = new javax.swing.JLabel();
        lblLetterCheck = new javax.swing.JLabel();
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

        lblLighterCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblLighterCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, -1, -1));

        btnKnife.setContentAreaFilled(false);
        btnKnife.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKnifeActionPerformed(evt);
            }
        });
        add(btnKnife, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 750, 70, 50));

        btnCup.setContentAreaFilled(false);
        btnCup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCupActionPerformed(evt);
            }
        });
        add(btnCup, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 30, 20));

        btnGloves.setContentAreaFilled(false);
        btnGloves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGlovesActionPerformed(evt);
            }
        });
        add(btnGloves, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 790, 90, 50));

        btnLetter.setContentAreaFilled(false);
        btnLetter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLetterActionPerformed(evt);
            }
        });
        add(btnLetter, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 730, 40, 40));

        btnLighter.setContentAreaFilled(false);
        btnLighter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLighterActionPerformed(evt);
            }
        });
        add(btnLighter, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, 40, 30));

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

        lblKnifeCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblKnifeCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 770, -1, -1));

        lblCupCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblCupCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, -1, -1));

        lblGlovesCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblGlovesCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 800, -1, -1));

        lblLetterCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblLetterCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 740, -1, -1));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_factory.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -70, 1600, -1));
    }// </editor-fold>//GEN-END:initComponents

    private String lastClicked = "";
    
    private void btnLighterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLighterActionPerformed
        lblLighterCheck.setVisible(true);
        lighterClicked = true;
        lastClicked = "lighter";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A killer’s lighter...",
            "It’s a silver cigarette lighter with the initials 'HL' engraved on it, covered in blood",
            "It smells faintly of gasoline, suggesting it may have been used to set a fire or destroy evidence after a kill",
            "This lighter is commonly used by a known assassin operating in Busan"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnLighter.setEnabled(false);
    }//GEN-LAST:event_btnLighterActionPerformed

    private void btnLetterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLetterActionPerformed
        lblLetterCheck.setVisible(true);
        letterClicked = true;
        lastClicked = "letter";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A bloodstained letter...",
            "It’s crumpled and found on the floor, containing a message with instructions to meet in Busan",
            "The letter indicates a connection to the city's underground operations",
            "This could be a crucial lead, connecting the operation to Busan"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnLetter.setEnabled(false);
    }//GEN-LAST:event_btnLetterActionPerformed

    private void btnGlovesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGlovesActionPerformed
        lblGlovesCheck.setVisible(true);
        glovesClicked = true;
        lastClicked = "gloves";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A pair of blood-stained gloves...",
            "They are black and found near the exit of the factory, stained with blood",
            "They were likely used during the killing, but there’s no indication of a specific city involved",
            "This could provide a lead to trace the killer"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnGloves.setEnabled(false);
    }//GEN-LAST:event_btnGlovesActionPerformed

    private void btnCupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCupActionPerformed
        lblCupCheck.setVisible(true);
        cupClicked = true;
        lastClicked = "cup";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "Hale’s espresso cup...",
            "It’s discarded, with Hale’s coffee shop logo visible, found among crates",
            "A napkin with a phone number written on it connects to someone involved in the killing operation",
            "This could be the next piece in the puzzle"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnCup.setEnabled(false);
    }//GEN-LAST:event_btnCupActionPerformed

    private void btnKnifeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKnifeActionPerformed
        lblKnifeCheck.setVisible(true);
        knifeClicked = true;
        lastClicked = "knife";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A bloodstained knife...",
            "It’s large, covered in fresh blood, found in a corner of the factory",
            "It seems to have been used in a recent killing, but there’s no direct link to a specific location",
            "This could be important"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnKnife.setEnabled(false);
    }//GEN-LAST:event_btnKnifeActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (isDialogueComplete || isSkipping) {
            showNextLine();
        } else {
            isSkipping = true;
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnCup;
    private javax.swing.JButton btnGloves;
    private javax.swing.JButton btnKnife;
    private javax.swing.JButton btnLetter;
    private javax.swing.JButton btnLighter;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblCupCheck;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblGlovesCheck;
    private javax.swing.JLabel lblKnifeCheck;
    private javax.swing.JLabel lblLetterCheck;
    private javax.swing.JLabel lblLighterCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    // End of variables declaration//GEN-END:variables
}
