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
import java.awt.EventQueue;
import javax.swing.JFrame;


public class ClassicGame extends JFrame {
    
    public ClassicGame() {
        add(new Board(this));
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
                JFrame ex = new ClassicGame();
                ex.setVisible(true);                
            }
        });
    }
}
