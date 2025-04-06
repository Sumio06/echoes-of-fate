/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package echoesoffate;

import javax.swing.JOptionPane;

/**
 *
 * @author User
 */

public class ChooseCharacter extends javax.swing.JPanel {

    private MainFrame frame;
    private UserData userData;
    
    
    public ChooseCharacter(MainFrame frame, UserData userData) {
        initComponents();
        this.frame = frame;
        this.userData = userData;
        setupButtonActions();
        btnBack.addActionListener(e -> frame.showScreen("Menu"));
    }
    
    private void setupButtonActions() {
        btnCelesteNakamura.addActionListener(e -> selectCharacter("CelesteNakamura", "Celeste Nakamura"));
        btnRenTakahashi.addActionListener(e -> selectCharacter("RenTakahashi", "Ren Takahashi"));
        btnAsherVale.addActionListener(e -> selectCharacter("AsherVale", "Asher Vale"));
    }
       
    private void selectCharacter(String characterName, String character) {
        int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to select " + character + "?",
        "Confirm Character Selection",
        JOptionPane.YES_NO_OPTION
    );

        if (confirm == JOptionPane.YES_OPTION) { 
            userData.setCharacter(characterName); 
            System.out.println("Selected Character: " + userData.getCharacter());
            frame.showScreen(characterName);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        asherIcon1 = new javax.swing.JLabel();
        asherIcon = new javax.swing.JLabel();
        celesteIcon = new javax.swing.JLabel();
        lblGameTitle = new javax.swing.JLabel();
        btnCelesteNakamura = new javax.swing.JButton();
        btnRenTakahashi = new javax.swing.JButton();
        btnAsherVale = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblQuote1 = new javax.swing.JLabel();
        lblChooseCharacter = new javax.swing.JLabel();
        lblChooseCharacterBackground = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        asherIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/chooseCharacterPics/ren.png"))); // NOI18N
        add(asherIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 430, 420));

        asherIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/chooseCharacterPics/asher.png"))); // NOI18N
        add(asherIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 400, 420));

        celesteIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/chooseCharacterPics/celeste.png"))); // NOI18N
        add(celesteIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 310, 400, 420));

        lblGameTitle.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 70)); // NOI18N
        lblGameTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblGameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameTitle.setText("Echoes of Fate");
        add(lblGameTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 770, 90));

        btnCelesteNakamura.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnCelesteNakamura.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnCelesteNakamura.setForeground(new java.awt.Color(255, 255, 255));
        btnCelesteNakamura.setText("Celeste Nakamura");
        add(btnCelesteNakamura, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 750, 188, 50));

        btnRenTakahashi.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnRenTakahashi.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnRenTakahashi.setForeground(new java.awt.Color(255, 255, 255));
        btnRenTakahashi.setText("Ren Takahashi");
        add(btnRenTakahashi, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 750, 154, 50));

        btnAsherVale.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnAsherVale.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnAsherVale.setForeground(new java.awt.Color(255, 255, 255));
        btnAsherVale.setText("Asher Vale");
        btnAsherVale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsherValeActionPerformed(evt);
            }
        });
        add(btnAsherVale, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 750, 145, 50));

        btnBack.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnBack.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 140, 50));

        lblQuote1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblQuote1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote1.setText("Step into the past, before it erases you.");
        add(lblQuote1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 540, 40));

        lblChooseCharacter.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 36)); // NOI18N
        lblChooseCharacter.setForeground(new java.awt.Color(255, 255, 255));
        lblChooseCharacter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChooseCharacter.setText("Choose Character");
        add(lblChooseCharacter, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 370, 43));

        lblChooseCharacterBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblChooseCharacterBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblChooseCharacterBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblChooseCharacterBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChooseCharacterBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblChooseCharacterBackground.setOpaque(true);
        add(lblChooseCharacterBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 1410, 610));

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffateassets/echoesoffatebg.png"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsherValeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsherValeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsherValeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asherIcon;
    private javax.swing.JLabel asherIcon1;
    private javax.swing.JButton btnAsherVale;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCelesteNakamura;
    private javax.swing.JButton btnRenTakahashi;
    private javax.swing.JLabel celesteIcon;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblChooseCharacter;
    private javax.swing.JLabel lblChooseCharacterBackground;
    private javax.swing.JLabel lblGameTitle;
    private javax.swing.JLabel lblQuote1;
    // End of variables declaration//GEN-END:variables
}