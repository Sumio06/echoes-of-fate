/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package echoesoffate.ashervalestoryline;

import echoesoffate.MainFrame;
import echoesoffate.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author User
 */

public class AsherVale extends javax.swing.JPanel {
     
    private MainFrame frame;
    private String[] SCENE1 = {
        "March 16, 2025... 2:37 AM...",
        "(A dimly lit alley in Itaewon. Neon lights flicker. The air is thick with the scent of rain and cigarette smoke)",
        "(Kieran Vale walks alone, hands in his jacket pockets. His breath is visible in the cold night air)",
        "(His steps echo softly on the wet pavement, each footfall swallowed by the alley’s emptiness)",
        "Kieran (muttering): Should’ve just gone home...",
        "(A gust of wind rushes through the alley. The neon glow behind him flickers, then dies for half a second)",
        "(Kieran stops)",
        "Kieran: Hello?...",
        "(Silence...)",
        "(He turns his head slightly, scanning the alley. No one. Just the dark walls, the distant hum of the city)",
        "(Then, a shadow shifts)",
        "(A flicker of movement behind him)",
        "(Kieran barely has time to turn...)",
        "(A hand grabs his shoulder. A cold, sharp pain tears into his stomach)",
        "Kieran (sharp inhale): Ngh!",
        "(His body stiffens. His breath catches. His knees buckle)",
        "(He stumbles forward, one hand instinctively pressing against his abdomen)",
        "(His fingers meet something warm. Wet. Sticky)",
        "(He looks down, blood spills between his shaking fingers)",
        "Kieran (weakly): What...?",
        "(He turns his head, eyes locking onto a hooded figure)",
        "Kieran (gasping): No... wait...",
        "(His legs give out. He collapses onto the pavement)",
        "(His chest rises. Falls. Shudders)",
        "(The killer lingers, watching. Then, without a word, they disappear into the darkness)",
        "(The alley falls silent, except for the faint, dying echo of Kieran’s last breath)",
        "(The city is alive-music, laughter, distant sirens. But in this alley, it’s suffocatingly quiet)",
        "(Asher Vale moves quickly, his breath uneven. His heart pounds in his chest)",
        "(His phone is in his hand. No new messages. No calls)",
        "Asher (under his breath): Where the hell are you, Kieran...?",
        "(He steps deeper into the alley. The neon lights barely reach past the entrance, leaving most of it in shadows)",
        "(Then, he sees something ahead. A shape on the ground)",
        "(His stomach tightens)",
        "(His pace quickens. Then, when he gets close enough, he stops cold)",
        "(Kieran lies motionless on the pavement. Blood smeared across the concrete)",
        "Asher: No...",
        "(His body moves before his mind catches up. He stumbles forward, dropping to his knees)",
        "Asher (shaky): Kieran... hey! Can you hear me?!",
        "(He grabs Kieran’s shoulder, turning him slightly. His skin is too cold)",
        "(Asher’s breath catches. His chest tightens. No. No, no, no)",
        "(He presses two fingers to Kieran’s neck. Nothing)",
        "Asher (barely a whisper): No… no, no, no...",
        "(His hands tremble. His vision blurs)"
    };
    
    private String[] SCENE2 = {
        "(Then, a noise. Footsteps. Distant, but fast. Someone is running)",
        "(Asher’s head snaps up)",
        "(At the far end of the alley, a shadowy figure turns the corner, vanishing)",
        "Asher (sharply): HEY!",
        "(The figure bolts, Asher doesn’t hesitate. He’s up and running before his mind even registers it)",
        "(His boots slam against the pavement, adrenaline replacing his grief)",
        "(The figure weaves through the maze of alleyways, but Asher is fast, he’s gaining)",
        "Asher (gritted teeth): You’re not getting away, you bastard!",
        "(The killer cuts left, Asher follows. He’s so close, almost close enough to reach out and grab...)",
        "(Then, the figure suddenly disappears)",
        "(Asher skids to a stop, chest heaving. He looks around wildly. No sign of them)",
    };
    
    private String[] SCENE3 = {
        "(Then, he sees it. A door. Old. Rusted. It wasn’t there before)",
        "(Asher stands before the old, weathered door, his breath still ragged from the chase)",
        "(The killer is gone. Vanished. But this door... it wasn’t here before)",
        "Asher (gritted teeth): Tch... Did they go in here?",
        "(He exhales sharply, jaw clenched, and grips the handle)",
        "(The metal is ice-cold. Too cold. Like it's been sitting in the snow.)",
    };
    
    private String[] SCENE4 = {
        "(With a deep breath, he pushes the door open)",
        "(The hinges groan as it swings inward, revealing nothing.)",
        "(A dimly lit corridor stretches ahead, empty, silent. A single, flickering lightbulb buzzes overhead)",
        "(The air is thick with dust, untouched. No footprints. No movement. The killer isn’t here)",
        "Asher (scanning the space, frustrated): Where did he go?",
        "(His footsteps echo as he walks inside, searching every corner)",
        "(Nothing. Just old walls and silence)",
        "Asher (under his breath): Damn... waste of time",
        "(Shaking his head, he turns back toward the door)",
        "(He grips the handle and pushes it open)",
        "(The alley is still there... but something is wrong)",
        "(A thick fog has settled across the ground, creeping unnaturally toward him)",
        "(The neon signs are flickering. Distorted. Like they’re struggling to exist)",
        "(The air feels heavier, thick, like moving through water)",
        "Asher (frowning): What the...?",
        "(He steps forward and the ground bends beneath him. Like stepping onto something that isn’t solid)",
        "(His vision blurs for a moment. Shapes twist. The alley stretches, pulling away from him, no, pulling him in)",
        "(A sharp ringing pierces his ears. The sound warps, stretching like a voice speaking underwater)",
        "(His breath catches. His head spins)",
        "(He blinks and the world snaps back)"
    };
    
    private String[] SCENE5 = {
        "(The alley is normal again. But the sounds of Itaewon’s nightlife are... gone)",
        "(No voices. No cars. Just... silence)",
        "(His phone buzzes in his pocket)",
        "(He pulls it out and his stomach drops. The screen reads: February 15, 2025. One month before Kieran’s death)",
        "Asher (staring at the screen, whispering): No...",
        "(His breath quickens. His hands shake)",
        "(The air still feels wrong. Heavy. Like something is pressing down on reality itself)",
        "(He looks back at the door, but it's gone. As if it never existed)",
        "(The realization sinks in)",
        "(The world hasn’t just changed. Time has unraveled)",
        "(Asher takes a shaky breath, his pulse pounding in his ears)",
        "(The alley is the same... but not. The neon lights flicker strangely, the air feels thicker, distorted)",
        "(His fingers curl into fists, his instincts screaming that something is deeply, impossibly wrong)",
        "(Then a sound cuts through the silence.)"
    };
    
    private String[] SCENE6 = {
        "(A telephone. Ringing.)",
        "(Not his phone. A payphone.)",
        "(His head snaps toward the alley’s entrance where an old telephone booth stands under a flickering streetlight)",
        "(His stomach twists. That booth wasn’t there before)",
        "(The ringing continues, sharp and urgent. Beckoning)",
        "(Asher swallows hard, his breath shallow. Every instinct tells him to walk away)",
        "(But his feet move forward. Step by step)",
        "(The night feels heavier with every step, like the world is holding its breath)",
        "(He pushes open the door to the booth. The air inside is stale. Freezing)",
        "(His fingers hover over the receiver, hesitating for just a second)",
        "(Then, he picks up)",
        "Mysterious Voice (calm, emotionless): You have one month",
        "Asher (gritted teeth): Who the hell is this?!",
        "Mysterious Voice: Stop the murder",
        "Asher (sharply): Kieran’s murder?!",
        "Mysterious Voice: Or time will erase you",
        "(The voice is steady. Too steady. Like it isn’t human)",
        "Asher (demanding): Who are you?! Why is this happening?!",
        "(A low, static hum distorts through the line like a frequency trying to break through)",
        "Mysterious Voice: The clock is already ticking",
        "(Then, the call ends)",
        "(No beep. No tone. Just... silence)",
        "(Asher pulls the receiver away, staring at it, his pulse hammering)",
        "(Then, he blinks)"
    };
    
    private String[] SCENE7 = {
        "(The phone booth is gone)",
        "(Like it was never there)",
        "(His chest tightens. His breath quickens)",
        "(He spins in place, his mind racing. Did he imagine it?)",
        "(Then, his phone buzzes in his pocket)",
        "(He pulls it out. His stomach drops. A new message)",
        "(From Kieran)",
        "Kieran: 'Yo, you free? Kinda bored'", 
        "(Asher stares at the screen, his breath catching in his throat)",
        "(Kieran is alive. For now)",
        "(The realization slams into him like a freight train)",
        "(He isn’t just in the past. He has a chance to change it)",
        "(Asher's breath is unsteady, his grip tightening around his phone)",
        "(Kieran is alive. But for how long?)",
        "(The weight of the message settles in his chest, heavy and suffocating)",
        "(This isn’t just a second chance, it’s a countdown)",
        "(Asher swallows hard, pushing the thought away)",
        "(He needs to focus. If Kieran was reaching out, it means he knew something was coming)",
        "(His fingers move quickly over his phone, scrolling through old messages)",
        "(Then he sees it, a location pin, Kieran sent just hours before his death)",
        "(Asher’s stomach twists. It wasn’t for this alley)",
        "Asher (muttering): So why the hell was he here?",
        "(He exhales sharply, his mind locking into place. The first step is clear)",
        "(If he wants answers, he needs to retrace Kieran’s last movements. Find out what changed)",
        "(He tucks his phone away, glancing at the alley one last time)",
        "(Then, without hesitation, he steps into the night, heading to the bar where Kieran was supposed to be)",
        "(The investigation begins now)"
    };
    
    private String[] SCENE8 = {
        "(Asher walks aimlessly through the quiet streets of Itaewon, his mind racing)",
        "(The city feels different now—like he's walking through a memory instead of reality)",
        "(Then, he stops)",
        "(Across the street, neon lights flicker against the glass of a familiar bar)",
        "(Kieran used to come here. A lot)",
        "Asher (muttering): You always did love this place",
        "(His feet move before his mind can catch up. If he's back in time, he needs to start somewhere)",
        "(And maybe, just maybe, this place still holds something he missed before)",
        "(He crosses the street and pushes open the door)",
        "(Inside, the bar is dimly lit, filled with murmured conversations and the clinking of glasses)",
        "(A few regulars glance up but quickly return to their drinks)",
        "(The scent of whiskey and cheap cologne lingers in the air)",
        "(Asher exhales slowly. If Kieran had a reason for coming here often, this is where he’ll find it)",
        "(The investigation begins)"
    };
    
    private int dialogueIndex = 0;
    private int charIndex = 0;
    private Timer timer;
    private Clip typewriterClip;

    public AsherVale(MainFrame frame) {
        this.frame = frame;
        initComponents();
        lblDialogue.setText("");
        lblDialogue.revalidate();
        lblDialogue.repaint();
        //Typewriter Effect
        new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                showNextDialogue();
            }
        }).start();
        btnContinue.addActionListener(e -> advanceDialogue());
    }

    private void playTypewriterSound() {
        try {
            if (typewriterClip != null && typewriterClip.isRunning()) {
                typewriterClip.stop();
            }
            File soundFile = new File("src/echoesoffate/echoesoffateassets/typings.wav");
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

        timer = new Timer(50, new ActionListener() { //Typewriter effect
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
    if (dialogueIndex < SCENE1.length) {
       
        displayText(SCENE1[dialogueIndex]);
    } else if (dialogueIndex - SCENE1.length < SCENE2.length) {
        if (dialogueIndex == SCENE1.length) lblDialogue.setText("");
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/scene2_alleyway.png"))); // Scene 2 background 
        displayText(SCENE2[dialogueIndex - SCENE1.length]);
    } else if (dialogueIndex - SCENE1.length - SCENE2.length < SCENE3.length) {
        if (dialogueIndex == SCENE1.length + SCENE2.length) lblDialogue.setText("");
         lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/scene3_door.png"))); // Scene 3 background 
        displayText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);
    } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length < SCENE4.length) {
        if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length) lblDialogue.setText("");
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/scene4_corridor.png"))); // Scene 4 background
        displayText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length]);
    } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length < SCENE5.length) {
        if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length) lblDialogue.setText("");
       lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/scene5_time_distortion.png"))); // Scene 5 background
        displayText(SCENE5[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length]);
    } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length < SCENE6.length) {
        if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length) lblDialogue.setText("");
      //  lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/scene6_phone_booth.png"))); // Scene 6 background
        displayText(SCENE6[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length]);
    } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length < SCENE7.length) {
        if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length) lblDialogue.setText("");
       // lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/scene7_reality_shift.png"))); // Scene 7 background
        displayText(SCENE7[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length]);
    } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length < SCENE8.length) {
        if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length) lblDialogue.setText("");
      //  lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/scene8_bar.png"))); // Scene 8 background
        displayText(SCENE8[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length]);
    } else {
        return;
    }
}


    private void advanceDialogue() {
        if (timer != null && timer.isRunning()) {
            timer.stop(); // Skip Animation
            stopTypewriterSound();

            if (dialogueIndex < SCENE1.length) {
                lblDialogue.setText(SCENE1[dialogueIndex]);
            } else if (dialogueIndex - SCENE1.length < SCENE2.length) {
                lblDialogue.setText(SCENE2[dialogueIndex - SCENE1.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length < SCENE3.length) {
                lblDialogue.setText(SCENE3[dialogueIndex - SCENE1.length - SCENE2.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length < SCENE4.length) {
                lblDialogue.setText(SCENE4[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length < SCENE5.length) {
                lblDialogue.setText(SCENE5[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length < SCENE6.length) {
                lblDialogue.setText(SCENE6[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length < SCENE7.length) {
                lblDialogue.setText(SCENE7[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length]);
            } else if (dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length < SCENE8.length) {
                lblDialogue.setText(SCENE8[dialogueIndex - SCENE1.length - SCENE2.length - SCENE3.length - SCENE4.length - SCENE5.length - SCENE6.length - SCENE7.length]);
            }
        } else {
            dialogueIndex++;

            // Move To SCENE 8
            if (dialogueIndex == SCENE1.length + SCENE2.length + SCENE3.length + SCENE4.length + SCENE5.length + SCENE6.length + SCENE7.length) {
                lblDialogue.setText(""); // Clear
                lblDialogue.revalidate();
                lblDialogue.repaint();
            }

            showNextDialogue();
        }
    }

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
        add(btnContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 1430, 230));

        lblContinue.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        lblContinue.setForeground(new java.awt.Color(255, 255, 255));
        lblContinue.setText("Click to continue...");
        add(lblContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 750, -1, -1));

        lblDialogue.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
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
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/scene1_alleyway.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblContinue;
    private javax.swing.JLabel lblDialogue;
    private javax.swing.JLabel lblLoginFormBackground;
    // End of variables declaration//GEN-END:variables
}