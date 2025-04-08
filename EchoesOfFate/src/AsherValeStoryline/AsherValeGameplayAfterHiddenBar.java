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
public class AsherValeGameplayAfterHiddenBar extends javax.swing.JPanel {

    /**
     * Creates new form AsherValeGameplayAfterHiddenBar
     */
    
    private MainFrame frame;
    
    private int dialogueIndex = 0;
    private int charIndex = 0;
    private Timer timer;
    private Clip typewriterClip;
    
    public AsherValeGameplayAfterHiddenBar(MainFrame frame) {
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
    
    private String[] SCENE1 = {
        "(Asher walks toward the locked backroom door, key in hand)",
        "(The key fits perfectly. He turns it, and the door creaks open)",
        "(Hale is seated behind a desk, calm and collected, eyes cold but knowing)",
        "Hale (smiling faintly): You’re late, Asher. You always were. But you’re here, and that’s something",
        "(Hale’s fingers tap on the desk, his gaze locked on Asher)",
        "Asher (darkly, stepping forward): I’m done with the games, Hale. This ends now",
        "Hale (leaning back): Games? Oh, Asher, don’t be so naïve. This was never about games",
        "(Hale chuckles, eyes glinting with something sinister)",
        "Hale: You’ve been chasing shadows. Trying to rewrite things that were never meant to be rewritten",
        "Asher (growling, stepping closer): Enough talk",
        "(Asher slams his hand on the desk, knocking papers aside as the tension builds)"
    };

    private String[] SCENE2 = {
        "(Asher lunges toward Hale. The room erupts in chaos)",
        "(Hale rises to meet him, the fight brutal and fast)",
        "(Fists fly, furniture crashes. It’s raw, physical, personal)",
        "(Each blow carries years of pain, betrayal, and desperation)",
        "(Neither man holds back. This is the reckoning they both knew would come)",
        "(Asher slams Hale against the ground, pinning him hard)",
        "(Both are bruised and breathless, but Asher doesn’t flinch)",
        "Hale (panting, grinning): You think this ends with me?",
        " You’ve played right into it, Asher. You have no idea what’s coming",
        "Asher (growling): Not anymore. It's over.",
        "(Hale’s grin fades slightly, but the defiance in his eyes remains)"
    };

    private String[] SCENE3 = {
        "(Asher presses the call button on his phone, voice steady)",
        "(Moments later, sirens wail in the distance)",
        "(Police burst in. Hale is cuffed and dragged away, still smirking)",
        "(The chaos settles. The fight is over—but the war might not be)",
        "(Asher watches silently as Hale is taken out of the room)"
    };

    private String[] SCENE4 = {
        "(Asher steps out of the bar, breathing heavily, adrenaline fading)",
        "(The cold night wraps around him as he stares into the street)",
        "(The sound of distant traffic echoes. The world feels quieter, heavier)",
        "(He pulls out his phone, hands trembling slightly)",
        "(Then—it rings. Unknown number. Asher’s heart stops for a second)"
    };

    private String[] SCENE5 = {
        "(Asher answers the phone, voice steady): Asher here",
        "(There’s a long, tense pause. The silence is thick)",
        "Voice (cold, emotionless): Kieran’s gone. He’s dead. The timeline’s been rewritten",
        "(Asher freezes, chest tightening as the words sink in)",
        "Asher (quietly, shaken): What do you mean? How could this happen?",
        "Voice (solemnly): Kieran was shot. Point-blank. He never had a chance",
        "Voice: It happened before you even reached him. The bullet was meant for him",
        "(Asher’s stomach drops, disbelief flooding him)",
        "Asher (softly): He was alive when I heard from him... How could he be gone now?",
        "Voice: It doesn’t matter. He was never meant to survive. The timeline locked in",
        "Voice: You couldn’t change it, no matter what you did",
        "(Asher’s hand trembles, the finality crashing down)",
        "Asher (whispers): He’s gone… I couldn’t save him",
        "(Asher stands frozen in the street, the phone still pressed to his ear)",
        "(The world feels distant. Kieran’s death echoes in the silence)",
        "(The End.)"
    };
    
    private Clip doorClip;
    private boolean isDoorSoundPlayed = false;
    
    private void playDoorSound() {
        try {
            if (doorClip != null && doorClip.isRunning()) {
                doorClip.stop();
            }
            File soundFile = new File("src/echoesoffateassets/door.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            doorClip = AudioSystem.getClip();
            doorClip.open(audioStream);
            doorClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void startDialogue() {
        if (!isDoorSoundPlayed) {
                playDoorSound();
                isDoorSoundPlayed = true;
            }
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
        if(dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length+ SCENE4.length){
            frame.afterHiddenBar();
        }
        
        if (dialogueIndex < SCENE1.length) {
            displayText(SCENE1[dialogueIndex]);
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length) {
            if (dialogueIndex == SCENE1.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/fight.png")));
            }
            displayText(SCENE2[dialogueIndex - SCENE1.length]);
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/fight.png")));
            }
            displayText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/night_bar.png")));
            }
            displayText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length]);
            dialogueIndex++;
        } 
        else if (dialogueIndex < SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) {
            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) {
                lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/bar_exit.png")));
            }
            displayText(SCENE5[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length]);
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
            }
        } else {
            if (dialogueIndex >= SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) {
                frame.showScreen("AsherValeGameplayEnding");
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
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/inside_backroom.png"))); // NOI18N
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
