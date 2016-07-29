/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

/**
 *
 * @author Bloo
 */
import GamePlay.Classic.Board;
import GamePlay.TimeAttack.Board2;
import GamePlay.TimeAttack.Board3;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Snake extends javax.swing.JFrame {

    public Snake() {

        add(new Board3());
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new Snake();
                ex.setVisible(true);                
            }
        });
        
    }
}
