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
        
    }
    
    
       private void setupButtonActions() {
        btnCelesteNakamura.addActionListener(e -> selectCharacter("Celeste Nakamura"));
        btnRenTakahashi.addActionListener(e -> selectCharacter("Ren Takahashi"));
        btnAsherVale.addActionListener(e -> selectCharacter("Asher Vale"));
    }
       
        private void selectCharacter(String characterName) {
          int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to select " + characterName + "?",
        "Confirm Character Selection",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) { 
        userData.setCharacter(characterName); 
        System.out.println("Selected Character: " + characterName);
        frame.showScreen("GameScreen"); 
    }
}


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGameTitle = new javax.swing.JLabel();
        btnCelesteNakamura = new javax.swing.JButton();
        btnRenTakahashi = new javax.swing.JButton();
        btnAsherVale = new javax.swing.JButton();
        lblQuote1 = new javax.swing.JLabel();
        lblChooseCharacter = new javax.swing.JLabel();
        lblQuote = new javax.swing.JLabel();
        lblChooseCharacterBackground = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        lblGameTitle.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 70)); // NOI18N
        lblGameTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblGameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameTitle.setText("Echoes of Fate");

        btnCelesteNakamura.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnCelesteNakamura.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnCelesteNakamura.setForeground(new java.awt.Color(255, 255, 255));
        btnCelesteNakamura.setText("Celeste Nakamura");

        btnRenTakahashi.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnRenTakahashi.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnRenTakahashi.setForeground(new java.awt.Color(255, 255, 255));
        btnRenTakahashi.setText("Ren Takahashi");

        btnAsherVale.setBackground(new java.awt.Color(83, 77, 169, 100));
        btnAsherVale.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnAsherVale.setForeground(new java.awt.Color(255, 255, 255));
        btnAsherVale.setText("Asher Vale");
        btnAsherVale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsherValeActionPerformed(evt);
            }
        });

        lblQuote1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        lblQuote1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote1.setText("Step into the past, before it erases you.");

        lblChooseCharacter.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        lblChooseCharacter.setForeground(new java.awt.Color(255, 255, 255));
        lblChooseCharacter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChooseCharacter.setText("Choose Character");

        lblQuote.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        lblQuote.setForeground(new java.awt.Color(255, 255, 255));
        lblQuote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuote.setText("@2025 OOP 2 Project. All Rights Reserved");

        lblChooseCharacterBackground.setBackground(new java.awt.Color(36, 43, 53, 200));
        lblChooseCharacterBackground.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        lblChooseCharacterBackground.setForeground(new java.awt.Color(0, 0, 0));
        lblChooseCharacterBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChooseCharacterBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.blue));
        lblChooseCharacterBackground.setOpaque(true);

        lblBackground.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/echoesoffate/echoesoffateassets/loginandregisterbackground.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(645, Short.MAX_VALUE)
                .addComponent(btnAsherVale, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(btnRenTakahashi, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnCelesteNakamura, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(642, 642, 642))
            .addGroup(layout.createSequentialGroup()
                .addGap(837, 837, 837)
                .addComponent(lblChooseCharacter, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBackground)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(lblGameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(130, 130, 130)
                            .addComponent(lblQuote1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblChooseCharacterBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(210, 210, 210)
                            .addComponent(lblQuote, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(494, Short.MAX_VALUE)
                .addComponent(lblChooseCharacter, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRenTakahashi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAsherVale, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCelesteNakamura, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(384, 384, 384))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(202, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblGameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(lblQuote1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(60, 60, 60)
                    .addComponent(lblChooseCharacterBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(57, 57, 57)
                    .addComponent(lblQuote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsherValeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsherValeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsherValeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsherVale;
    private javax.swing.JButton btnCelesteNakamura;
    private javax.swing.JButton btnRenTakahashi;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblChooseCharacter;
    private javax.swing.JLabel lblChooseCharacterBackground;
    private javax.swing.JLabel lblGameTitle;
    private javax.swing.JLabel lblQuote;
    private javax.swing.JLabel lblQuote1;
    // End of variables declaration//GEN-END:variables
}
