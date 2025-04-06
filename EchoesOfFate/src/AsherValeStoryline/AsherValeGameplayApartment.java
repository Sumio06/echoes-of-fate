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
public class AsherValeGameplayApartment extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayApartment
     */
    
    private MainFrame frame;
    
    public AsherValeGameplayApartment(MainFrame frame) {
        this.frame = frame;
        initComponents();

        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblFrameCheck.setVisible(false);
        lblDocumentCheck.setVisible(false);
        lblPaperCheck.setVisible(false);
        lblRecorderCheck.setVisible(false);
        lblMagCheck.setVisible(false);

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
    
    private int i;
    
    private void showNextLine() {
        if (dialogueIndex < dialogueLines.length) {
            String currentLine = dialogueLines[dialogueIndex];

            if (currentLine.equals("N")) {
                stopBlinkingEffect();

                javax.swing.SwingUtilities.invokeLater(() -> {
                    frame.showScreen("AsherValeGameplayOutsideApartment");
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

    private boolean recorderClicked = false;
    private boolean magClicked = false;
    private boolean paperClicked = false;
    private boolean documentClicked = false;
    private boolean frameClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (recorderClicked && magClicked && paperClicked && documentClicked && frameClicked) {
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
             case "frame":
                lines = new String[] {
                    "A faded photograph...",
                    "It shows Kieran sitting at a dimly lit bar, locked in tense conversation with a man",
                    "No timestamp, no location... but that tattoo stands out",
                    "This could identify the contact",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "paper":
                lines = new String[] {
                    "A crumpled note...",
                    "Barely legible writing bleeds through the creases and ink smudges",
                    "'Hale. He knows. The back room.' That name again",
                    "This confirms the bartender's connection",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "document":
                lines = new String[] {
                    "A document with a shipment invoice, hidden in a rusty shipping container...",
                    "Stamped with Hale’s alias and a fake company name",
                    "Destination: 'Unit 47, Sector D – Dockside Logistics'",
                    "Date and time: March 3, 3:00 PM. A meeting, and it’s soon",
                    "Asher realizes this is the lead he's been waiting for",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'"
                };
                break;

            case "recorder":
                lines = new String[] {
                    "A damaged voice recorder...",
                    "The casing is cracked, but the data chip is mostly intact",
                    "Kieran's voice: '...the ledger... bartender... can’t trust...' Then silence",
                    "A direct warning—possibly his last",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "mag":
                lines = new String[] {
                    "An empty pistol magazine...",
                    "Lying cold on the floor, as if dropped mid-conflict",
                    "No sign of the weapon—only the implication of haste or struggle",
                    "This may be the moment Kieran vanished",
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
        btnPaper = new javax.swing.JButton();
        btnRecorder = new javax.swing.JButton();
        btnDocument = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblRecorderCheck = new javax.swing.JLabel();
        lblDocumentCheck = new javax.swing.JLabel();
        lblFrameCheck = new javax.swing.JLabel();
        lblPaperCheck = new javax.swing.JLabel();
        lblMagCheck = new javax.swing.JLabel();
        btnMag = new javax.swing.JButton();
        btnFrame = new javax.swing.JButton();
        lblCluesFoundBackground = new javax.swing.JLabel();
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

        btnPaper.setContentAreaFilled(false);
        btnPaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaperActionPerformed(evt);
            }
        });
        add(btnPaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 690, 40, 50));

        btnRecorder.setContentAreaFilled(false);
        btnRecorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecorderActionPerformed(evt);
            }
        });
        add(btnRecorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 720, 40, 40));

        btnDocument.setContentAreaFilled(false);
        btnDocument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentActionPerformed(evt);
            }
        });
        add(btnDocument, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 610, 50, 50));

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

        lblRecorderCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblRecorderCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 720, -1, -1));

        lblDocumentCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblDocumentCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 620, -1, -1));

        lblFrameCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblFrameCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 510, -1, -1));

        lblPaperCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblPaperCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 700, -1, -1));

        lblMagCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblMagCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 810, -1, -1));

        btnMag.setContentAreaFilled(false);
        btnMag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMagActionPerformed(evt);
            }
        });
        add(btnMag, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 810, 50, 30));

        btnFrame.setContentAreaFilled(false);
        btnFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrameActionPerformed(evt);
            }
        });
        add(btnFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 510, 60, 40));

        lblCluesFoundBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblCluesFoundBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblCluesFoundBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblCluesFoundBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCluesFoundBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblCluesFoundBackground.setOpaque(true);
        add(lblCluesFoundBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 330, 120));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_apartment.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -70, 1600, -1));
    }// </editor-fold>//GEN-END:initComponents

    private String lastClicked = "";
    
    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (isDialogueComplete || isSkipping) {
            showNextLine();
        } else {
            isSkipping = true;
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrameActionPerformed
        lblFrameCheck.setVisible(true);
        lastClicked = "frame";
        frameClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A faded photograph...",
            "It shows Kieran sitting at a dimly lit bar, locked in tense conversation with a man",
            "No timestamp, no location... but that tattoo stands out",
            "This could identify the contact"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnFrame.setEnabled(false);
    }//GEN-LAST:event_btnFrameActionPerformed

    private void btnDocumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentActionPerformed
        lblDocumentCheck.setVisible(true);
        lastClicked = "document";
        documentClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A document with a shipment invoice, hidden in a rusty shipping container...",
            "Stamped with Hale’s alias and a fake company name",
            "Destination: 'Unit 47, Sector D – Dockside Logistics'",
            "Date and time: March 3, 3:00 PM. A meeting, and it’s soon",
            "Asher realizes this is the lead he's been waiting for",
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnDocument.setEnabled(false);
    }//GEN-LAST:event_btnDocumentActionPerformed

    private void btnRecorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecorderActionPerformed
        lblRecorderCheck.setVisible(true);
        lastClicked = "recorder";
        recorderClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A damaged voice recorder...",
            "The casing is cracked, but the data chip is mostly intact",
            "Kieran's voice: '...the ledger... bartender... can’t trust...' Then silence",
            "A direct warning—possibly his last"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnRecorder.setEnabled(false);
    }//GEN-LAST:event_btnRecorderActionPerformed

    private void btnMagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMagActionPerformed
        lblMagCheck.setVisible(true);
        lastClicked = "mag";
        magClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "An empty pistol magazine...",
            "Lying cold on the floor, as if dropped mid-conflict",
            "No sign of the weapon—only the implication of haste or struggle",
            "This may be the moment Kieran vanished"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnMag.setEnabled(false);
    }//GEN-LAST:event_btnMagActionPerformed

    private void btnPaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaperActionPerformed
        lblPaperCheck.setVisible(true);
        lastClicked = "paper";
        paperClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A crumpled note...",
            "Barely legible writing bleeds through the creases and ink smudges",
            "'Hale. He knows. The back room.' That name again",
            "This confirms the bartender's connection"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnPaper.setEnabled(false);
    }//GEN-LAST:event_btnPaperActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnDocument;
    private javax.swing.JButton btnFrame;
    private javax.swing.JButton btnMag;
    private javax.swing.JButton btnPaper;
    private javax.swing.JButton btnRecorder;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblDocumentCheck;
    private javax.swing.JLabel lblFrameCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblMagCheck;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    private javax.swing.JLabel lblPaperCheck;
    private javax.swing.JLabel lblRecorderCheck;
    // End of variables declaration//GEN-END:variables
}
