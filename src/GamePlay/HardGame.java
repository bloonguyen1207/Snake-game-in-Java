/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import GamePlay.TimeAttack.HardBoard;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Bloo
 */
public class HardGame extends JFrame{
    public HardGame() {

        add(new HardBoard(this));
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
                JFrame ex = new HardGame();
                ex.setVisible(true);                
            }
        });
        
    }
}
