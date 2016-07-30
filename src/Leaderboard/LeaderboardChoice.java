/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leaderboard;

/**
 *
 * @author Bloo
 */

import GamePlay.ClassicGame;
import Menu.Menu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class LeaderboardChoice extends JFrame {
    
    /**
     * Creates new form Menu
     */
    public LeaderboardChoice() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code"> 
    private void initComponents() {

        ClassicButton = new JButton();
        TimeButton = new JButton();
        BackButton = new JButton();
        LeaderLabel = new JLabel();
        Background = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));
//        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        ClassicButton.setBackground(new java.awt.Color(255, 204, 0));
        ClassicButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        ClassicButton.setForeground(new java.awt.Color(204, 51, 0));
        ClassicButton.setText("Classic Mode");
        ClassicButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ClassicButtonActionPerformed(evt);
            }
        });
        add(ClassicButton);
        ClassicButton.setBounds(400, 200, 200, 59);

        TimeButton.setBackground(new Color(255, 204, 0));
        TimeButton.setFont(new Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        TimeButton.setForeground(new Color(204, 51, 0));
        TimeButton.setText("Time Mode");
        TimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TimeButtonActionPerformed(evt);
            }
        });
        add(TimeButton);
        TimeButton.setBounds(400, 300, 200, 59);
        
        BackButton.setBackground(new Color(255, 204, 0));
        BackButton.setFont(new Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        BackButton.setForeground(new Color(204, 51, 0));
        BackButton.setText("Back to Main Menu");
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        add(BackButton);
        BackButton.setBounds(400, 400, 200, 59);

        LeaderLabel.setText("Leaderboard");
        LeaderLabel.setFont(new Font("Berlin Sans FB Demi", 1, 36));
        add(LeaderLabel);
        LeaderLabel.setBounds(395, 50, 250, 143);

        Background.setIcon(new ImageIcon("res\\Menu\\bg1.png")); // NOI18N
        Background.setText("Background");
        add(Background);
        Background.setBounds(0, 0, 1000, 600);

        pack();
    }// </editor-fold>                        

    public void ClassicButtonActionPerformed(ActionEvent evt) {                                              
        // TODO add your handling code here:
        Leaderboard c = new Leaderboard();
        c.setVisible(true);
        setVisible(false);
    } 

    private void TimeButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        LeaderboardTimeChoice t = new LeaderboardTimeChoice();
        t.setVisible(true);
        setVisible(false);
    } 
    
    private void BackButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        Menu m = new Menu();
        m.setVisible(true);
        setVisible(false);
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LeaderboardChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaderboardChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaderboardChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaderboardChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaderboardChoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    public JLabel Background;
    public JButton ClassicButton, TimeButton, BackButton;
    public JLabel LeaderLabel;
    // End of variables declaration
    
}
