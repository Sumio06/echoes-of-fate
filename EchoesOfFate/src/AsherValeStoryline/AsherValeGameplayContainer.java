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
public class AsherValeGameplayContainer extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayContainer
     */
    
    private MainFrame frame;
    
    public AsherValeGameplayContainer(MainFrame frame) {
        this.frame = frame;
        initComponents();
        
        lblObjective.setText("");
        lblObjective1.setText("");

        lblDialogue.setVisible(false);
        lblContinue.setVisible(false);
        lblLoginFormBackground.setVisible(false);
        btnContinue.setVisible(false);
        lblBlueprintCheck.setVisible(false);
        lblPhotoCheck.setVisible(false);
        lblFilesCheck.setVisible(false);
        lblMetroCheck.setVisible(false);
        lblJacketCheck.setVisible(false);

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
                    frame.showScreen("AsherValeGameplayOutsideContainer");
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

    private boolean jacketClicked = false;
    private boolean photoClicked = false;
    private boolean wiretapClicked = false;
    private boolean metroClicked = false;
    private boolean blueprintClicked = false;
    private boolean isObjectiveComplete = false;
    

    private void checkObjectiveCompletion() {
        if (jacketClicked && photoClicked && wiretapClicked && metroClicked && blueprintClicked) {
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
                    "A faded surveillance photo, wedged behind a crate panel...",
                    "A man enters the container at 12:42 AM. Hale’s shadow follows.",
                    "No timestamp, no location... but the details stand out.",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "files":
                lines = new String[] {
                    "A wiretap transcript stuffed in an oil-stained envelope...",
                    "Threats, paranoia. Hale’s operation unraveling.",
                    "There’s leverage, something bigger at stake.",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "metro":
                lines = new String[] {
                    "A torn metro pass with Hale’s alias...",
                    "Hale came to the port deliberately that night",
                    "The meeting, it’s soon. Something’s about to happen",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "jacket":
                lines = new String[] {
                    "A damaged voice recorder inside a torn jacket pocket...",
                    "The casing is cracked, but the data chip is intact.",
                    "A warning: '...the ledger... bartender... can’t trust...' Then silence.",
                    "This might be the last message from someone involved.",
                    "Narrator: 'The objective has been completed... but is that the end?'",
                    "Narrator: 'Something bigger is about to unfold...'",
                    "N"
                };
                break;

            case "blueprint":
                lines = new String[] {
                    "A marked blueprint of the port – an escape note.",
                    "Backside reads: 'Haneul Rooftop. Line of sight. Only safe way out.'",
                    "This points to the Haneul Rooftop",
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
        btnJacket = new javax.swing.JButton();
        btnMetro = new javax.swing.JButton();
        btnFiles = new javax.swing.JButton();
        btnPhoto = new javax.swing.JButton();
        btnBlueprint = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        lblContinue = new javax.swing.JLabel();
        lblDialogue = new javax.swing.JLabel();
        lblLoginFormBackground = new javax.swing.JLabel();
        lblJacketCheck = new javax.swing.JLabel();
        lblMetroCheck = new javax.swing.JLabel();
        lblFilesCheck = new javax.swing.JLabel();
        lblPhotoCheck = new javax.swing.JLabel();
        lblBlueprintCheck = new javax.swing.JLabel();
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

        btnJacket.setContentAreaFilled(false);
        btnJacket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJacketActionPerformed(evt);
            }
        });
        add(btnJacket, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 790, 150, 140));

        btnMetro.setContentAreaFilled(false);
        btnMetro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMetroActionPerformed(evt);
            }
        });
        add(btnMetro, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 630, 110, 30));

        btnFiles.setContentAreaFilled(false);
        btnFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilesActionPerformed(evt);
            }
        });
        add(btnFiles, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 620, 40, 40));

        btnPhoto.setContentAreaFilled(false);
        btnPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhotoActionPerformed(evt);
            }
        });
        add(btnPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 770, 40, 40));

        btnBlueprint.setContentAreaFilled(false);
        btnBlueprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlueprintActionPerformed(evt);
            }
        });
        add(btnBlueprint, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 670, 40, 30));

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

        lblJacketCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblJacketCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 820, -1, -1));

        lblMetroCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblMetroCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 630, -1, -1));

        lblFilesCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblFilesCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 630, -1, -1));

        lblPhotoCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblPhotoCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 770, -1, -1));

        lblBlueprintCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/check2.png"))); // NOI18N
        add(lblBlueprintCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 670, -1, -1));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_shipping_container.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -70, 1600, -1));
    }// </editor-fold>//GEN-END:initComponents

    private String lastClicked = "";
    
    private void btnMetroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMetroActionPerformed
        lblMetroCheck.setVisible(true);
        metroClicked = true;
        lastClicked = "metro";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A torn metro pass with Hale’s alias...",
            "Hale came to the port deliberately that night",
            "The meeting, it’s soon. Something’s about to happen"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnMetro.setEnabled(false);
    }//GEN-LAST:event_btnMetroActionPerformed

    private void btnFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilesActionPerformed
        lblFilesCheck.setVisible(true);
        wiretapClicked = true;
        lastClicked = "files";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A wiretap transcript stuffed in an oil-stained envelope...",
            "Threats, paranoia. Hale’s operation unraveling.",
            "There’s leverage, something bigger at stake."
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnFiles.setEnabled(false);
    }//GEN-LAST:event_btnFilesActionPerformed

    private void btnPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhotoActionPerformed
        lblPhotoCheck.setVisible(true);
        photoClicked = true;
        lastClicked = "photo";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A faded surveillance photo, wedged behind a crate panel...",
            "A man enters the container at 12:42 AM. Hale’s shadow follows.",
            "No timestamp, no location... but the details stand out."
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnPhoto.setEnabled(false);
    }//GEN-LAST:event_btnPhotoActionPerformed

    private void btnBlueprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlueprintActionPerformed
        lblBlueprintCheck.setVisible(true);
        blueprintClicked = true;
        lastClicked = "blueprint";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A marked blueprint of the port – an escape note.",
            "Backside reads: 'Haneul Rooftop. Line of sight. Only safe way out.'",
            "This points to the Haneul Rooftop",
            "This lighter is commonly used by a known assassin operating in Busan"
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnBlueprint.setEnabled(false);
    }//GEN-LAST:event_btnBlueprintActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (isDialogueComplete || isSkipping) {
            showNextLine();
        } else {
            isSkipping = true;
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnJacketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJacketActionPerformed
        lblJacketCheck.setVisible(true);
        jacketClicked = true;
        lastClicked = "jacket";
        cluesFound++;
        updateClueCount();
        playFoundSound();
        String[] lines = {
            "A damaged voice recorder inside a torn jacket pocket...",
            "The casing is cracked, but the data chip is intact.",
            "A warning: '...the ledger... bartender... can’t trust...' Then silence.",
            "This might be the last message from someone involved."
        };
        playDialogueTypewriterEffect(lines);
        checkObjectiveCompletion();
        btnJacket.setEnabled(false);
    }//GEN-LAST:event_btnJacketActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlueprint;
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnFiles;
    private javax.swing.JButton btnJacket;
    private javax.swing.JButton btnMetro;
    private javax.swing.JButton btnPhoto;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBlueprintCheck;
    private javax.swing.JLabel lblCluesFound;
    private javax.swing.JLabel lblCluesFoundBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblFilesCheck;
    private javax.swing.JLabel lblJacketCheck;
    private javax.swing.JLabel lblLoginFormBackground;
    private javax.swing.JLabel lblMetroCheck;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblObjective1;
    private javax.swing.JLabel lblObjectiveBackground;
    private javax.swing.JLabel lblPhotoCheck;
    // End of variables declaration//GEN-END:variables
}
