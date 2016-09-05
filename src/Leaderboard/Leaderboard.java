/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leaderboard;

import Player.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Bloo
 */
public final class Leaderboard extends JFrame{
    // Variables declaration - do not modify//
    public JPanel Background;
    public JLabel Title, Info;
    public JButton BackButton;
    private static File highscores;
    private static Scanner readFiles;
    // End of variables declaration//
    
    public Leaderboard() {
        initComponents();
    }
    
    public void initComponents() {
        
        int counter = 0;
        Background = new JPanel();
        Title = new JLabel();
        BackButton = new JButton();
        Font big = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        Title.setText("Leaderboard");
        Title.setFont(big);
        Title.setForeground(Color.white);
        add(Title);
        Title.setBounds(420, 0, 300, 100);
        
        try {
            highscores = new File(System.getProperty("user.dir") + ("/classic.txt"));
            readFiles = new Scanner(highscores);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        while (readFiles.hasNext()) {
            counter++;
            Info = new JLabel();
            Info.setText(Integer.toString(counter) + ". " + readFiles.nextLine());
            Info.setFont(big);
            Info.setForeground(new Color(255, 204, 0));
            if (counter <= 5) {
                Info.setBounds(300, counter * 80, 300, 100);
            } else {
                Info.setBounds(600, (counter - 5) * 80, 300, 100);
            }
            add(Info);
        }
        readFiles.close();
        
        BackButton.setBackground(new Color(255, 204, 0));
        BackButton.setFont(new Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        BackButton.setForeground(new Color(204, 51, 0));
        BackButton.setText("Back");
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        add(BackButton);
        BackButton.setBounds(410, 500, 200, 59);
        
        Background.setBackground(new Color(7, 123, 83));
        Background.setBounds(0, 0, 1000, 600);
        add(Background);
        
        pack();
    }
    
    public void BackButtonActionPerformed(ActionEvent evt) {                                              
        // TODO add your handling code here:
        LeaderboardChoice s = new LeaderboardChoice();
        s.setVisible(true);
        setVisible(false);
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
