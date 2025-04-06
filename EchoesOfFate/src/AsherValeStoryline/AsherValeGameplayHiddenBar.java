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
public class AsherValeGameplayHiddenBar extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayHiddenBar
     */
    
    private MainFrame frame;
    
    public AsherValeGameplayHiddenBar(MainFrame frame) {
        this.frame = frame;
        initComponents();
        
        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblPhotoCheck.setVisible(false);
        lblRustedKeyCheck.setVisible(false);
        lblPaperCheck.setVisible(false);
        lblKeyCheck.setVisible(false);
        lblDoorCheck.setVisible(false);

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
    
    private int i;
    
    private void showNextLine() {
        if (dialogueIndex < dialogueLines.length) {
            String currentLine = dialogueLines[dialogueIndex];

            if (currentLine.equals("N")) {
                stopBlinkingEffect();

                javax.swing.SwingUtilities.invokeLater(() -> {
                    frame.showScreen("AsherValeGameplayAfterHiddenBar");
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

    private boolean doorClicked = false;
    private boolean paperClicked = false;
    private boolean keyClicked = false;
    private boolean rustedKeyClicked = false;
    private boolean photoClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (doorClicked && photoClicked && paperClicked && keyClicked && rustedKeyClicked) {
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
             case "photo":
                lines = new String[] {
                    "On the table, a worn photo of Kieran, looking weary and untrusting...",
                    "A note is attached: 'You’re close, but this will cost you'",
                    "Asher (quietly, to himself): 'A key… but for what?'",
                    "Narrator: 'The photo is a clue, but it’s far from the answer.'",
                    "N"
                };
                break;

            case "door":
                lines = new String[] {
                    "The backroom door is on the right-hand side of the room...",
                    "The handle shows a small rusted indentation that matches the key Asher found earlier",
                    "Asher (with finality): 'If this key fits, there’s no turning back'",
                    "Narrator: 'The backroom is within reach. But only the right key will unlock its secrets'",
                    "N"
                };
                break;

            case "rustedKey":
                lines = new String[] {
                    "A rusted key found in the fireplace, tucked away and slightly burnt...",
                    "Asher knows immediately this is the key for the backroom door",
                    "Asher (determined): 'This is it. Let’s see what you’re hiding, Hale'",
                    "N"
                };
                break;

            case "key":
                lines = new String[] {
                   "A key hidden above the fireplace...",
                   "It feels like it should fit the backroom door, but when Asher tries it, it doesn’t",
                   "Asher (grumbling): 'Could be this is it. Let’s see what you’re hiding, Hale'",
                   "Narrator: 'The search continues. The backroom remains locked for now'",
                   "N"
                };
                break;

            case "paper":
                lines = new String[] {
                    "A crumpled note beside the fireplace, the edges scorched...",
                    "The writing is faint but legible: 'Don’t trust the mirror. It’s all a lie. Find the lock and it all ends'",
                    "Asher (nodding to himself): 'So much deception… I should’ve known'",
                    "(The note burns with hidden truths, but only more questions arise)'",
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
        lblDoorCheck = new javax.swing.JLabel();
        lblPaperCheck = new javax.swing.JLabel();
        btnPaper = new javax.swing.JButton();
        btnRustedKey = new javax.swing.JButton();
        btnPhoto = new javax.swing.JButton();
        btnKey = new javax.swing.JButton();
        btnDoor = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblKeyCheck = new javax.swing.JLabel();
        lblPhotoCheck = new javax.swing.JLabel();
        lblRustedKeyCheck = new javax.swing.JLabel();
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

        lblDoorCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblDoorCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 480, -1, -1));

        lblPaperCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblPaperCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 830, -1, -1));

        btnPaper.setContentAreaFilled(false);
        btnPaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaperActionPerformed(evt);
            }
        });
        add(btnPaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 830, 50, 50));

        btnRustedKey.setContentAreaFilled(false);
        btnRustedKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRustedKeyActionPerformed(evt);
            }
        });
        add(btnRustedKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 750, 40, 50));

        btnPhoto.setContentAreaFilled(false);
        btnPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhotoActionPerformed(evt);
            }
        });
        add(btnPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 680, 50, 40));

        btnKey.setContentAreaFilled(false);
        btnKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeyActionPerformed(evt);
            }
        });
        add(btnKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 40, 30));

        btnDoor.setContentAreaFilled(false);
        btnDoor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoorActionPerformed(evt);
            }
        });
        add(btnDoor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 0, 140, 960));

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

        lblKeyCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblKeyCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        lblPhotoCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblPhotoCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 690, -1, -1));

        lblRustedKeyCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblRustedKeyCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 760, -1, -1));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_hidden_bar.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -70, 1600, -1));
    }// </editor-fold>//GEN-END:initComponents

    private String lastClicked = "";
    
    private void btnPaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaperActionPerformed
        lblPaperCheck.setVisible(true);
        paperClicked = true;
        lastClicked = "paper";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "On the table, a worn photo of Kieran, looking weary and untrusting...",
            "A note is attached: 'You’re close, but this will cost you'",
            "Asher (quietly, to himself): 'A key… but for what?'"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnPaper.setEnabled(false);
    }//GEN-LAST:event_btnPaperActionPerformed

    private void btnRustedKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRustedKeyActionPerformed
        lblRustedKeyCheck.setVisible(true);
        rustedKeyClicked = true;
        lastClicked = "rustedKey";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A rusted key found in the fireplace, tucked away and slightly burnt...",
            "Asher knows immediately this is the key for the backroom door",
            "Asher (determined): 'This is it. Let’s see what you’re hiding, Hale'"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnRustedKey.setEnabled(false);
    }//GEN-LAST:event_btnRustedKeyActionPerformed

    private void btnPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhotoActionPerformed
        lblPhotoCheck.setVisible(true);
        photoClicked = true;
        lastClicked = "photo";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "On the table, a worn photo of Kieran, looking weary and untrusting...",
            "A note is attached: 'You’re close, but this will cost you'",
            "Asher (quietly, to himself): 'A key… but for what?'"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnPhoto.setEnabled(false);
    }//GEN-LAST:event_btnPhotoActionPerformed

    private void btnKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeyActionPerformed
        lblKeyCheck.setVisible(true);
        keyClicked = true;
        lastClicked = "key";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A key hidden above the fireplace...",
            "It feels like it should fit the backroom door, but when Asher tries it, it doesn’t",
            "Asher (grumbling): 'Could be this is it. Let’s see what you’re hiding, Hale'",
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnKey.setEnabled(false);
    }//GEN-LAST:event_btnKeyActionPerformed

    private void btnDoorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoorActionPerformed
        if (!keyClicked && !rustedKeyClicked) {
            String[] lines = {
                "Asher (frustrated): 'I need a key to open this door...'",
                "Narrator: 'Without the right key, the door remains locked.'"
            };
            playDialogueTypewriterEffect(lines);
            return;
        }
        
        if (keyClicked && !rustedKeyClicked) {
            String[] lines = {
                "Asher (grumbling): 'This isn’t the right key.'",
                "Narrator: 'The door remains locked. Asher needs the correct key to proceed.'"
            };
            playDialogueTypewriterEffect(lines);
            return;
        }

        if (rustedKeyClicked) {
            lblDoorCheck.setVisible(true);
            doorClicked = true;
            lastClicked = "door";
            cluesFound++;
            updateClueCount();
            playFoundSound();

            String[] lines = {
                "The backroom door is on the right-hand side of the room...",
                "The handle shows a small rusted indentation that matches the key Asher found earlier.",
                "Asher (with finality): 'If this key fits, there’s no turning back.'",
                "Narrator: 'The backroom is within reach. But only the right key will unlock its secrets.'"
            };
            playDialogueTypewriterEffect(lines);
            checkObjectiveCompletion();
            btnDoor.setEnabled(false);
        }
    }//GEN-LAST:event_btnDoorActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (isDialogueComplete || isSkipping) {
            showNextLine();
        } else {
            isSkipping = true;
        }
    }//GEN-LAST:event_btnContinueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnDoor;
    private javax.swing.JButton btnKey;
    private javax.swing.JButton btnPaper;
    private javax.swing.JButton btnPhoto;
    private javax.swing.JButton btnRustedKey;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblDoorCheck;
    private javax.swing.JLabel lblKeyCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    private javax.swing.JLabel lblPaperCheck;
    private javax.swing.JLabel lblPhotoCheck;
    private javax.swing.JLabel lblRustedKeyCheck;
    // End of variables declaration//GEN-END:variables
}
