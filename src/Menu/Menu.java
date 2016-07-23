/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author nguyentranngocdiep
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//
    private void initComponents() {

        StartButton = new javax.swing.JButton();
        StoreButton = new javax.swing.JButton();
        LeaderBoardButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        SnakeLabels = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        StartButton.setBackground(new java.awt.Color(255, 204, 0));
        StartButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24));
        StartButton.setForeground(new java.awt.Color(204, 51, 0));
        StartButton.setText("Start");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });
        getContentPane().add(StartButton);
        StartButton.setBounds(290, 230, 200, 59);

        StoreButton.setBackground(new java.awt.Color(255, 204, 0));
        StoreButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        StoreButton.setForeground(new java.awt.Color(204, 51, 0));
        StoreButton.setText("Store");
        StoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StoreButtonActionPerformed(evt);
            }
        });
        getContentPane().add(StoreButton);
        StoreButton.setBounds(290, 300, 200, 59);

        LeaderBoardButton.setBackground(new java.awt.Color(255, 204, 0));
        LeaderBoardButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        LeaderBoardButton.setForeground(new java.awt.Color(204, 51, 0));
        LeaderBoardButton.setText("Leader board");
        LeaderBoardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaderBoardButtonActionPerformed(evt);
            }
        });
        getContentPane().add(LeaderBoardButton);
        LeaderBoardButton.setBounds(290, 370, 200, 59);

        ExitButton.setBackground(new java.awt.Color(255, 204, 0));
        ExitButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        ExitButton.setForeground(new java.awt.Color(204, 51, 0));
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ExitButton);
        ExitButton.setBounds(290, 440, 200, 59);

        SnakeLabels.setIcon(new javax.swing.ImageIcon("res\\Menu\\snake.png")); // NOI18N
        SnakeLabels.setText("jLabel2");
        getContentPane().add(SnakeLabels);
        SnakeLabels.setBounds(260, 80, 250, 143);

        Background.setIcon(new javax.swing.ImageIcon("res\\Menu\\bg.png")); // NOI18N
        Background.setText("jLabel1");
        getContentPane().add(Background);
        Background.setBounds(0, 0, 800, 600);

        pack();
    }// </editor-fold>    
    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//
        // TODO add your handling code here:
        System.exit(0);
         

    }//

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        Menu2 menu2 = new Menu2();
        menu2.setVisible(true);
        setVisible(false);
                      
    }//
    private void StoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StoreButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StoreButtonActionPerformed

    private void LeaderBoardButtonActionPerformed(java.awt.event.ActionEvent evt) {//
        // TODO add your handling code here:
    }//

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//
    public javax.swing.JLabel Background;
    public javax.swing.JButton ExitButton;
    public javax.swing.JButton LeaderBoardButton;
    public javax.swing.JLabel SnakeLabels;
    public javax.swing.JButton StartButton;
    public javax.swing.JButton StoreButton;
    // End of variables declaration//
}
