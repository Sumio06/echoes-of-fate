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
public class AsherValeGameplayRooftop extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayRooftop
     */
    
    private MainFrame frame;
    
    public AsherValeGameplayRooftop(MainFrame frame) {
        this.frame = frame;
        initComponents();
        
        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblTicketCheck.setVisible(false);
        lblLetterCheck.setVisible(false);
        lblDocumentCheck.setVisible(false);
        lblKeycardCheck.setVisible(false);
        lblDrugsCheck.setVisible(false);

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
                    frame.showScreen("AsherValeGameplayAfterRooftop");
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

    private boolean letterClicked = false;
    private boolean drugsClicked = false;
    private boolean keycardClicked = false;
    private boolean documentClicked = false;
    private boolean ticketClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (letterClicked && drugsClicked && keycardClicked && documentClicked && ticketClicked) {
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
            case "letter":
                lines = new String[] {
                    "A direct order from the bartender, confirming Hale's involvement",
                    "It explicitly states that Hale ordered Kieran’s elimination",
                    "But the reason behind it is still a mystery",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "keycard":
                lines = new String[] {
                    "A keycard from Hotel Haneul in Jeju, found on the informant",
                    "An address is scribbled on the back, pointing to a specific location",
                    "This hotel could hold the key to unraveling the mystery",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "ticket":
                lines = new String[] {
                    "A ferry ticket to Jeju, dated for the same night Kieran vanished",
                    "It shows Kieran was supposed to leave, but something stopped him",
                    "Was he caught in something bigger than he realized?",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "drugs":
                lines = new String[] {
                    "A stash of drugs, directly linked to the smuggling operation",
                    "It’s more evidence of the bartender’s illegal dealings",
                    "This could be the key to understanding the entire network",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "document":
                lines = new String[] {
                    "A note with a time and location—'Meet at the docks, 3 AM.'",
                    "It suggests a final exchange, or possibly a departure.",
                    "This could be where everything comes together.",
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
        btnDrugs = new javax.swing.JButton();
        btnKeycard = new javax.swing.JButton();
        btnLetter = new javax.swing.JButton();
        btnTicket = new javax.swing.JButton();
        btnDocument = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblDrugsCheck = new javax.swing.JLabel();
        lblKeycardCheck = new javax.swing.JLabel();
        lblDocumentCheck = new javax.swing.JLabel();
        lblLetterCheck = new javax.swing.JLabel();
        lblTicketCheck = new javax.swing.JLabel();
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

        btnDrugs.setContentAreaFilled(false);
        btnDrugs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrugsActionPerformed(evt);
            }
        });
        add(btnDrugs, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 40, 40));

        btnKeycard.setContentAreaFilled(false);
        btnKeycard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeycardActionPerformed(evt);
            }
        });
        add(btnKeycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, 30, 30));

        btnLetter.setContentAreaFilled(false);
        btnLetter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLetterActionPerformed(evt);
            }
        });
        add(btnLetter, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 470, 40, 40));

        btnTicket.setContentAreaFilled(false);
        btnTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketActionPerformed(evt);
            }
        });
        add(btnTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 720, 40, 30));

        btnDocument.setContentAreaFilled(false);
        btnDocument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentActionPerformed(evt);
            }
        });
        add(btnDocument, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 590, 30, 30));

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

        lblDrugsCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblDrugsCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, -1, -1));

        lblKeycardCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblKeycardCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, -1, -1));

        lblDocumentCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblDocumentCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 590, -1, -1));

        lblLetterCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblLetterCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, -1, -1));

        lblTicketCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblTicketCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 720, -1, -1));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/rooftop.png"))); // NOI18N
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

    private void btnDrugsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrugsActionPerformed
        lblDrugsCheck.setVisible(true);
        drugsClicked = true;
        lastClicked = "drugs";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A stash of drugs, directly linked to the smuggling operation",
            "It’s more evidence of the bartender’s illegal dealings",
            "This could be the key to understanding the entire network"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnDrugs.setEnabled(false);
    }//GEN-LAST:event_btnDrugsActionPerformed

    private void btnKeycardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeycardActionPerformed
        lblKeycardCheck.setVisible(true);
        keycardClicked = true;
        lastClicked = "keycard";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A keycard from Hotel Haneul in Jeju, found on the informant",
            "An address is scribbled on the back, pointing to a specific location",
            "This hotel could hold the key to unraveling the mystery"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnKeycard.setEnabled(false);
    }//GEN-LAST:event_btnKeycardActionPerformed

    private void btnLetterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLetterActionPerformed
        lblLetterCheck.setVisible(true);
        letterClicked = true;
        lastClicked = "letter";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A direct order from the bartender, confirming Hale's involvement",
            "It explicitly states that Hale ordered Kieran’s elimination",
            "But the reason behind it is still a mystery"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnLetter.setEnabled(false);
    }//GEN-LAST:event_btnLetterActionPerformed

    private void btnTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketActionPerformed
        lblTicketCheck.setVisible(true);
        ticketClicked = true;
        lastClicked = "ticket";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A ferry ticket to Jeju, dated for the same night Kieran vanished",
            "It shows Kieran was supposed to leave, but something stopped him",
            "Was he caught in something bigger than he realized?"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnTicket.setEnabled(false);
    }//GEN-LAST:event_btnTicketActionPerformed

    private void btnDocumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentActionPerformed
        lblDocumentCheck.setVisible(true);
        documentClicked = true;
        lastClicked = "document";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A note with a time and location—'Meet at the docks, 3 AM'",
            "It suggests a final exchange, or possibly a departure",
            "This could be where everything comes together"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnDocument.setEnabled(false);
    }//GEN-LAST:event_btnDocumentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnDocument;
    private javax.swing.JButton btnDrugs;
    private javax.swing.JButton btnKeycard;
    private javax.swing.JButton btnLetter;
    private javax.swing.JButton btnTicket;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblDocumentCheck;
    private javax.swing.JLabel lblDrugsCheck;
    private javax.swing.JLabel lblKeycardCheck;
    private javax.swing.JLabel lblLetterCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    private javax.swing.JLabel lblTicketCheck;
    // End of variables declaration//GEN-END:variables
}
