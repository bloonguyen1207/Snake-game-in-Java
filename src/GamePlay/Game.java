/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import GamePlay.TimeAttack.GameBoardPanel;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author Bloo
 */
public class Game extends JFrame{
    public Game() throws UnsupportedAudioFileException, IOException {
        add(new GameBoardPanel(this));
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
                try {
                    JFrame ex = new Game();
                    ex.setVisible(true);
                } catch (UnsupportedAudioFileException | IOException ex1) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        });
        
    }
}
