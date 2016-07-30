/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leaderboard;

import Player.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Bloo
 */
public class Leaderboard extends JFrame{
    // Variables declaration - do not modify//
    public JLabel Background, Title;
    public JButton BackButton;
    private ArrayList<Player> playersInfo = new ArrayList<Player>(10);    
    private static File highscores;
    private static Scanner readFiles;
    // End of variables declaration//
    
    public Leaderboard() {
        initComponents();
    }
    
    public static void readFile() throws Exception {
        String[] chc; 
        try {
            highscores = new File(System.getProperty("user.dir") + ("/classic.txt"));
            readFiles = new Scanner(highscores);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        while (readFiles.hasNext()) {
            System.out.println(readFiles.nextLine());
        }
    }
    
    public void initComponents() {
        
        Background = new JLabel();
        Title = new JLabel();
        Font small = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(small);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        Background.setBackground(Color.yellow);
//        Background.setIcon(new javax.swing.ImageIcon("res\\Menu\\bg1.png"));
        Background.setText("Background");
        add(Background);
        Background.setBounds(0, 0, 1000, 600);
        
        Title.setText("Leaderboard");
        Title.setFont(small);
        add(Title);
        Title.setBounds(400, 0, 300, 150);
        
        pack();
    }
    
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
            java.util.logging.Logger.getLogger(Leaderboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Leaderboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Leaderboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Leaderboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Leaderboard().setVisible(true);
            }
        });
    }
}
