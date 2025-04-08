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
public class AsherValeGameplayBunker extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayBunker
     */
    
    private MainFrame frame;
    
    public AsherValeGameplayBunker(MainFrame frame) {
        this.frame = frame;
        initComponents();
        
        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblNotebookCheck.setVisible(false);
        lblBulletCheck.setVisible(false);
        lblMapCheck.setVisible(false);
        lblHandcuffsCheck.setVisible(false);
        lblWatchCheck.setVisible(false);

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
        
        playTypewriterEffect("Objective:", "Search the bunker...", new Runnable() {
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
                    frame.showScreen("AsherValeGameplayOutsideBunker");
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
    
    private boolean notebookClicked = false;
    private boolean watchClicked = false;
    private boolean ledgerClicked = false;
    private boolean bulletClicked = false;
    private boolean mapClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (notebookClicked && watchClicked && ledgerClicked && bulletClicked && mapClicked) {
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
            case "notebook":
                lines = new String[] {
                    "A bloodstained notebook with frantic handwriting...",
                    "Found on a rickety shelf, the pages are torn and stained with blood",
                    "One entry reads: 'They’re watching. The final meeting is set. I have to go... even if it’s a trap'",
                    "Clues of growing paranoia, betrayal, and Hale’s operation scatter across every page",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "map":
                lines = new String[] {
                    "A map with a final meeting point circled in red...",
                    "Found on the desk, weathered and worn from use",
                    "A location marked: 'The Last Deal. The Final Confrontation'",
                    "This was Kieran’s destination—the moment everything unraveled",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "bullet":
                lines = new String[] {
                    "A spent bullet casing buried beneath the floor...",
                    "The caliber matches Kieran’s sidearm—it was fired during a confrontation",
                    "He fought back. The evidence of the struggle is real, brutal, and recent",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "watch":
                lines = new String[] {
                    "A set of handcuffs with a broken lock...",
                    "Found hanging from a rusty pipe, the lock snapped cleanly in two",
                    "A sign of a violent escape—Kieran was likely restrained and broke free",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;
            case "ledger":
                lines = new String[] {
                    "A brass key with etched coordinates...",
                    "Hidden in a drawer, tucked between pages of a worn journal",
                    "The numbers lead deep into the woods—beneath an old tree, a hidden cave lies waiting",
                    "This key isn’t just a clue—it’s an invitation to the final chapter Kieran couldn’t finish",
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
        btnNotebook = new javax.swing.JButton();
        btnHandcuffs = new javax.swing.JButton();
        btnWatch = new javax.swing.JButton();
        btnMap = new javax.swing.JButton();
        btnBullet = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblHandcuffsCheck = new javax.swing.JLabel();
        lblBulletCheck = new javax.swing.JLabel();
        lblNotebookCheck = new javax.swing.JLabel();
        lblWatchCheck = new javax.swing.JLabel();
        lblMapCheck = new javax.swing.JLabel();
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

        btnNotebook.setContentAreaFilled(false);
        btnNotebook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotebookActionPerformed(evt);
            }
        });
        add(btnNotebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 620, 60, 70));

        btnHandcuffs.setContentAreaFilled(false);
        btnHandcuffs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHandcuffsActionPerformed(evt);
            }
        });
        add(btnHandcuffs, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 650, 60, 50));

        btnWatch.setContentAreaFilled(false);
        btnWatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWatchActionPerformed(evt);
            }
        });
        add(btnWatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 510, 60, 40));

        btnMap.setContentAreaFilled(false);
        btnMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapActionPerformed(evt);
            }
        });
        add(btnMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 680, 60, 60));

        btnBullet.setContentAreaFilled(false);
        btnBullet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBulletActionPerformed(evt);
            }
        });
        add(btnBullet, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 690, 30, 20));

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

        lblHandcuffsCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblHandcuffsCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 660, -1, -1));

        lblBulletCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblBulletCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 680, -1, -1));

        lblNotebookCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblNotebookCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 640, -1, -1));

        lblWatchCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblWatchCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 510, -1, -1));

        lblMapCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblMapCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 700, -1, -1));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_bunker.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -70, 1600, -1));
    }// </editor-fold>//GEN-END:initComponents

    private String lastClicked = "";
    
    private void btnNotebookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotebookActionPerformed
        lblNotebookCheck.setVisible(true);
        notebookClicked = true;
        lastClicked = "notebook";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A bloodstained notebook with frantic handwriting...",
            "Found on a rickety shelf, the pages are torn and stained with blood",
            "One entry reads: 'They’re watching. The final meeting is set. I have to go... even if it’s a trap'",
            "Clues of growing paranoia, betrayal, and Hale’s operation scatter across every page"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnNotebook.setEnabled(false);
    }//GEN-LAST:event_btnNotebookActionPerformed

    private void btnHandcuffsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHandcuffsActionPerformed
        lblHandcuffsCheck.setVisible(true);
        ledgerClicked = true;
        lastClicked = "ledger";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A shattered lamp with a hidden compartment...",
            "Inside the cracked base lies a half-burned note: 'He’s watching. Don’t trust anyone, not even Hale.'",
            "A warning. Someone else was involved—someone dangerous."
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnHandcuffs.setEnabled(false);
    }//GEN-LAST:event_btnHandcuffsActionPerformed

    private void btnWatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWatchActionPerformed
        lblWatchCheck.setVisible(true);
        watchClicked = true;
        lastClicked = "watch";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A set of handcuffs with a broken lock...",
            "Found hanging from a rusty pipe, the lock snapped cleanly in two",
            "A sign of a violent escape—Kieran was likely restrained and broke free",
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnWatch.setEnabled(false);
    }//GEN-LAST:event_btnWatchActionPerformed

    private void btnMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapActionPerformed
        lblMapCheck.setVisible(true);
        mapClicked = true;
        lastClicked = "map";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A map with a final meeting point circled in red...",
            "Found on the desk, weathered and worn from use",
            "A location marked: 'The Last Deal. The Final Confrontation'",
            "This was Kieran’s destination—the moment everything unraveled"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnMap.setEnabled(false);
    }//GEN-LAST:event_btnMapActionPerformed

    private void btnBulletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBulletActionPerformed
        lblBulletCheck.setVisible(true);
        bulletClicked = true;
        lastClicked = "bullet";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
           "A spent bullet casing buried beneath the floor...",
           "The caliber matches Kieran’s sidearm—it was fired during a confrontation",
           "He fought back. The evidence of the struggle is real, brutal, and recent",
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnBullet.setEnabled(false);
    }//GEN-LAST:event_btnBulletActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (isDialogueComplete || isSkipping) {
            showNextLine();
        } else {
            isSkipping = true;
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBullet;
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnHandcuffs;
    private javax.swing.JButton btnMap;
    private javax.swing.JButton btnNotebook;
    private javax.swing.JButton btnWatch;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBulletCheck;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblHandcuffsCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblMapCheck;
    private javax.swing.JLabel lblNotebookCheck;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    private javax.swing.JLabel lblWatchCheck;
    // End of variables declaration//GEN-END:variables
}
