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
public class AsherValeGameplayWarehouse extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayWarehouse
     */
    
    private MainFrame frame; 
    
    public AsherValeGameplayWarehouse(MainFrame frame) {
        this.frame = frame;
        initComponents();
        
        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblPhoneCheck.setVisible(false);
        lblTowelCheck.setVisible(false);
        lblMapCheck.setVisible(false);
        lblUSBCheck.setVisible(false);
        lblDocumentCheck.setVisible(false);

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
                    frame.showScreen("AsherValeGameplayOutsideWarehouse");
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
    
    private boolean towelClicked = false;
    private boolean mapClicked = false;
    private boolean documentClicked = false;
    private boolean usbClicked = false;
    private boolean phoneClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (towelClicked && mapClicked && documentClicked && usbClicked && phoneClicked) {
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
            case "phone":
                lines = new String[] {
                    "A burner phone...",
                    "The screen is cracked, but the last received message is still visible",
                    "\"Too risky. Move now\"",
                    "This could be a crucial lead. The objective is getting closer to completion",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "usb":
                lines = new String[] {
                    "A hidden USB drive...",
                    "It was tucked away inside a crate, containing grainy footage",
                    "The footage shows the shadowy figure meeting someone in the warehouse hours before you arrived",
                    "This could tie the figure to a larger operation. The objective is getting closer to completion",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "document":
                lines = new String[] {
                    "A partially destroyed document...",
                    "The text is faded, but it contains an address in Seoul, \"Namdaemun Warehouse\"",
                    "The figure must have dropped it while escaping",
                    "This leads you closer to the truth",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "map":
                lines = new String[] {
                    "A torn and weathered map...",
                    "It shows locations in Seoul marked with red X's, but one stands out, circled several times",
                    "There's a faint coffee stain near one of the marks",
                    "This map points to a location of importance for the operation",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "towel":
                lines = new String[] {
                    "A bloodstained handkerchief...",
                    "It has fresh blood, suggesting someone was injured here recently",
                    "The initials \"HL\" are embroidered on it",
                    "This is a potential lead",
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
        lblObjective = new javax.swing.JLabel();
        lblObjective1 = new javax.swing.JLabel();
        lblCluesFoundBackground = new javax.swing.JLabel();
        btnPhone = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblTowelCheck = new javax.swing.JLabel();
        lblMapCheck = new javax.swing.JLabel();
        lblDocumentCheck = new javax.swing.JLabel();
        lblUSBCheck = new javax.swing.JLabel();
        lblPhoneCheck = new javax.swing.JLabel();
        btnTowel = new javax.swing.JButton();
        btnMap = new javax.swing.JButton();
        btnDocument = new javax.swing.JButton();
        btnUSB = new javax.swing.JButton();
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

        lblObjective.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblObjective.setForeground(new java.awt.Color(255, 255, 255));
        lblObjective.setText("[Text]");
        add(lblObjective, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        lblObjective1.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblObjective1.setForeground(new java.awt.Color(51, 255, 0));
        lblObjective1.setText("[Text]");
        add(lblObjective1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        lblCluesFoundBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblCluesFoundBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblCluesFoundBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblCluesFoundBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCluesFoundBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblCluesFoundBackground.setOpaque(true);
        add(lblCluesFoundBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 330, 120));

        btnPhone.setContentAreaFilled(false);
        btnPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhoneActionPerformed(evt);
            }
        });
        add(btnPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 730, 40, 30));

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

        lblTowelCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblTowelCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 740, -1, -1));

        lblMapCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblMapCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, -1, -1));

        lblDocumentCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblDocumentCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 820, -1, -1));

        lblUSBCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblUSBCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 680, -1, -1));

        lblPhoneCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblPhoneCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 730, -1, -1));

        btnTowel.setContentAreaFilled(false);
        btnTowel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTowelActionPerformed(evt);
            }
        });
        add(btnTowel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 740, 40, 40));

        btnMap.setContentAreaFilled(false);
        btnMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapActionPerformed(evt);
            }
        });
        add(btnMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, 30, 30));

        btnDocument.setContentAreaFilled(false);
        btnDocument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentActionPerformed(evt);
            }
        });
        add(btnDocument, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 810, 60, 50));

        btnUSB.setContentAreaFilled(false);
        btnUSB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUSBActionPerformed(evt);
            }
        });
        add(btnUSB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 680, 40, 30));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_warehouse.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -70, 1600, -1));
    }// </editor-fold>//GEN-END:initComponents

    private String lastClicked = "";
    
    private void btnPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhoneActionPerformed
        lblPhoneCheck.setVisible(true);
        phoneClicked = true;
        lastClicked = "phone";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A burner phone...",
            "The screen is cracked, but the last received message is still visible",
            "\"Too risky. Move now\"",
            "This could be a crucial lead. The objective is getting closer to completion"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnPhone.setEnabled(false);
    }//GEN-LAST:event_btnPhoneActionPerformed

    private void btnUSBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUSBActionPerformed
        lblUSBCheck.setVisible(true);
        usbClicked = true;
        lastClicked = "usb";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A hidden USB drive...",
            "It was tucked away inside a crate, containing grainy footage",
            "The footage shows the shadowy figure meeting someone in the warehouse hours before you arrived",
            "This could tie the figure to a larger operation. The objective is getting closer to completion"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnUSB.setEnabled(false);
    }//GEN-LAST:event_btnUSBActionPerformed

    private void btnDocumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentActionPerformed
        lblDocumentCheck.setVisible(true);
        documentClicked = true;
        lastClicked = "document";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A partially destroyed document...",
            "The text is faded, but it contains an address in Seoul, \"Namdaemun Warehouse\"",
            "The figure must have dropped it while escaping",
            "This leads you closer to the truth"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnDocument.setEnabled(false);
    }//GEN-LAST:event_btnDocumentActionPerformed

    private void btnMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapActionPerformed
        lblMapCheck.setVisible(true);
        mapClicked = true;
        lastClicked = "map";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A torn and weathered map...",
            "It shows locations in Seoul marked with red X's, but one stands out, circled several times",
            "There's a faint coffee stain near one of the marks",
            "This map points to a location of importance for the operation"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnMap.setEnabled(false);
    }//GEN-LAST:event_btnMapActionPerformed

    private void btnTowelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTowelActionPerformed
        lblTowelCheck.setVisible(true);
        towelClicked = true;
        lastClicked = "towel";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A bloodstained handkerchief...",
            "It has fresh blood, suggesting someone was injured here recently",
            "The initials \"HL\" are embroidered on it",
            "This is a potential lead"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnTowel.setEnabled(false);
    }//GEN-LAST:event_btnTowelActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (isDialogueComplete || isSkipping) {
            showNextLine();
        } else {
            isSkipping = true;
        }
    }//GEN-LAST:event_btnContinueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnDocument;
    private javax.swing.JButton btnMap;
    private javax.swing.JButton btnPhone;
    private javax.swing.JButton btnTowel;
    private javax.swing.JButton btnUSB;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblDocumentCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblMapCheck;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    private javax.swing.JLabel lblPhoneCheck;
    private javax.swing.JLabel lblTowelCheck;
    private javax.swing.JLabel lblUSBCheck;
    // End of variables declaration//GEN-END:variables
}
