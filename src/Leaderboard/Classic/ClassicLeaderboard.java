/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leaderboard.Classic;

import java.io.File;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Bloo
 */
public class ClassicLeaderboard extends JFrame{
    private static File highscores;
    private static Scanner readFiles;
    
    public static void main(String[] args) throws Exception {
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
}
