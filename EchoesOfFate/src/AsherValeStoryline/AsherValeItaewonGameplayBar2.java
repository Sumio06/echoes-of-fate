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
public class AsherValeItaewonGameplayBar2 extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeItaewonGameplayBar2
     */
    
    private MainFrame frame; 
    
    public AsherValeItaewonGameplayBar2(MainFrame frame) {
        this.frame = frame;
        initComponents();

        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblBaristaCheck.setVisible(false);
        lblCloseFriendCheck.setVisible(false);
        lblNoteCheck.setVisible(false);
        lblGlassCheck.setVisible(false);
        lblStainCheck.setVisible(false);

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
                    frame.showScreen("AsherValeGameplayOutsideBar");
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

    private boolean baristaClicked = false;
    private boolean closeFriendClicked = false;
    private boolean glassClicked = false;
    private boolean noteClicked = false;
    private boolean stainClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (baristaClicked && closeFriendClicked && glassClicked && noteClicked && stainClicked) {
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
            case "barista":
                lines = new String[] {
                    "A barista",
                    "When Asher asks about Kieran, the bartender stiffens",
                    "He wipes the same glass over and over, avoiding eye contact",
                    "\"He asked too many questions\"",
                    "Kieran wasn’t just waiting for someone. He was pressing for answers",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "closeFriend":
                lines = new String[] {
                    "A friend close to Kieran",
                    "That guy... the bartender... saw Kieran last. They argued. Then Kieran left alone",
                    "A pause. A shake of the head",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "glass":
                lines = new String[] {
                    "A Glass with No Fingerprints",
                    "Asher’s eyes land on a whiskey glass. But something is wrong.",
                    "The surface is spotless. No smudges. No fingerprints",
                    "His gut tightens. Everyone leaves prints. Even Kieran. Unless someone wiped them away",
                    "Beside it, Hale lazily polishes another glass, his movements practiced, too practiced",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "note":
                lines = new String[] {
                    "A torn note...",
                    "Hastily written, the ink smudged from sweat or rain. The words barely readable:",
                    "\"Asher, March 17, NOT an accident. They know.\"",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "stain":
                lines = new String[] {
                    "A Bar Rag with a Strange Stain",
                    "Stuffed into the trash, it looks like any other dirty rag",
                    "But when Asher picks it up, he notices something off, a dark, reddish-brown stain, still damp",
                    "Not just spilled wine. Not just whiskey. Something else",
                    "A chill runs down his spine. Had someone tried to clean up a mess that shouldn’t have been there?",
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

        lblObjective = new javax.swing.JLabel();
        lblObjective1 = new javax.swing.JLabel();
        lblCluesFound = new javax.swing.JLabel();
        lblCluesFoundBackground = new javax.swing.JLabel();
        lblObjectiveBackground = new javax.swing.JLabel();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblGlassCheck = new javax.swing.JLabel();
        lblStainCheck = new javax.swing.JLabel();
        lblNoteCheck = new javax.swing.JLabel();
        lblBaristaCheck = new javax.swing.JLabel();
        lblCloseFriendCheck = new javax.swing.JLabel();
        btnNote = new javax.swing.JButton();
        btnStain = new javax.swing.JButton();
        btnGlass = new javax.swing.JButton();
        btnBarista = new javax.swing.JButton();
        btnCloseFriend = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblObjective.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblObjective.setForeground(new java.awt.Color(255, 255, 255));
        lblObjective.setText("[Text]");
        add(lblObjective, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        lblObjective1.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblObjective1.setForeground(new java.awt.Color(51, 255, 0));
        lblObjective1.setText("[Text]");
        add(lblObjective1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        lblCluesFound.setFont(new java.awt.Font("Lucida Fax", 0, 22)); // NOI18N
        lblCluesFound.setForeground(new java.awt.Color(51, 255, 0));
        lblCluesFound.setText("[Text]");
        add(lblCluesFound, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 40, -1, -1));

        lblCluesFoundBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblCluesFoundBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblCluesFoundBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblCluesFoundBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCluesFoundBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblCluesFoundBackground.setOpaque(true);
        add(lblCluesFoundBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 330, 120));

        lblObjectiveBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblObjectiveBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblObjectiveBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblObjectiveBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObjectiveBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblObjectiveBackground.setOpaque(true);
        add(lblObjectiveBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 30, 340, 50));

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

        lblGlassCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check.png"))); // NOI18N
        add(lblGlassCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 460, -1, -1));

        lblStainCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check.png"))); // NOI18N
        add(lblStainCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 760, -1, -1));

        lblNoteCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check.png"))); // NOI18N
        add(lblNoteCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 610, -1, -1));

        lblBaristaCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check.png"))); // NOI18N
        add(lblBaristaCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 420, -1, -1));

        lblCloseFriendCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check.png"))); // NOI18N
        add(lblCloseFriendCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, -1, -1));

        btnNote.setContentAreaFilled(false);
        btnNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoteActionPerformed(evt);
            }
        });
        add(btnNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 620, 180, 100));

        btnStain.setContentAreaFilled(false);
        btnStain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStainActionPerformed(evt);
            }
        });
        add(btnStain, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 830, 70, 90));

        btnGlass.setContentAreaFilled(false);
        btnGlass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGlassActionPerformed(evt);
            }
        });
        add(btnGlass, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 470, 60, 130));

        btnBarista.setContentAreaFilled(false);
        btnBarista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaristaActionPerformed(evt);
            }
        });
        add(btnBarista, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 220, 310, 380));

        btnCloseFriend.setContentAreaFilled(false);
        btnCloseFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseFriendActionPerformed(evt);
            }
        });
        add(btnCloseFriend, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 500, 810));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/bar.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, -80, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private String lastClicked = "";
    
    private void btnBaristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaristaActionPerformed
        lblBaristaCheck.setVisible(true);
        lastClicked = "barista";
        baristaClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "When Asher asks about Kieran, the bartender stiffens",
            "He wipes the same glass over and over, avoiding eye contact",
            "\"He asked too many questions\"",
            "Kieran wasn’t just waiting for someone. He was pressing for answers"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnBarista.setEnabled(false);
    }//GEN-LAST:event_btnBaristaActionPerformed

    private void btnCloseFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseFriendActionPerformed
        lblCloseFriendCheck.setVisible(true);
        lastClicked = "closeFriend";
        closeFriendClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A friend close to Kieran",
            "That guy... the bartender... saw Kieran last. They argued. Then Kieran left alone",
            "A pause. A shake of the head",
            "\"Or so we thought\""
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnCloseFriend.setEnabled(false);
    }//GEN-LAST:event_btnCloseFriendActionPerformed

    private void btnGlassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGlassActionPerformed
        lblGlassCheck.setVisible(true);
        lastClicked = "glass";
        glassClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A Glass with No Fingerprints",
            "Asher’s eyes land on a whiskey glass. But something is wrong.",
            "The surface is spotless. No smudges. No fingerprints",
            "His gut tightens. Everyone leaves prints. Even Kieran. Unless someone wiped them away",
            "Beside it, Hale lazily polishes another glass, his movements practiced, too practiced"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnGlass.setEnabled(false);
    }//GEN-LAST:event_btnGlassActionPerformed

    private void btnStainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStainActionPerformed
        lblStainCheck.setVisible(true);
        lastClicked = "stain";
        stainClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A Bar Rag with a Strange Stain",
            "Stuffed into the trash, it looks like any other dirty rag",
            "But when Asher picks it up, he notices something off, a dark, reddish-brown stain, still damp",
            "Not just spilled wine. Not just whiskey. Something else",
            "A chill runs down his spine. Had someone tried to clean up a mess that shouldn’t have been there?"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnStain.setEnabled(false);
    }//GEN-LAST:event_btnStainActionPerformed

    private void btnNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoteActionPerformed
        lblNoteCheck.setVisible(true);
        lastClicked = "note";
        noteClicked = true;
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A torn note...",
            "Hastily written, the ink smudged from sweat or rain. The words barely readable:",
            "\"Asher, March 17, NOT an accident. They know.\""
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnNote.setEnabled(false);
    }//GEN-LAST:event_btnNoteActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (isDialogueComplete || isSkipping) {
            showNextLine();
        } else {
            isSkipping = true;
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBarista;
    private javax.swing.JButton btnCloseFriend;
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnGlass;
    private javax.swing.JButton btnNote;
    private javax.swing.JButton btnStain;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBaristaCheck;
    private javax.swing.JLabel lblCloseFriendCheck;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblGlassCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblNoteCheck;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    private javax.swing.JLabel lblStainCheck;
    // End of variables declaration//GEN-END:variables
}