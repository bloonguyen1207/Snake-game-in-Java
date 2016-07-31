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
import GamePlay.TimeAttack.Board3;
import Player.Player;
import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ClassicGame extends JFrame {

    private static File highscores;
    private static Scanner readFiles;
    
    public ClassicGame() {
        add(new Board(this));
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Don't touch this
    public static void readFile() throws Exception {
        String info;
        int counter = 0;
        ArrayList<Player> players = new ArrayList<Player>(10);

        try {
            highscores = new File(System.getProperty("user.dir") + ("/classic.txt"));
            readFiles = new Scanner(highscores);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        while (readFiles.hasNext()) {
            info = readFiles.nextLine();
            String[] piece = info.split(" ");
            System.out.println(piece[0]);
        }
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new ClassicGame();
                ex.setVisible(true);                
            }
        });
//        try {
//            readFile();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
