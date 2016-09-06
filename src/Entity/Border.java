/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.DynamicObject.Snakes;
import Entity.StaticObject.StaticObject;
import java.awt.Color;
import java.awt.Graphics;
import static GamePlay.TimeAttack.GameBoardPanel.*;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author binguyen.com
 */
public class Border extends Entity {
        //private int i = 0;  
    private Image brick;
    
    public Border(){
          brick = loadImage(brick, "res\\Items\\brick.png");
    }
    
    public static ArrayList<Border> setBorders(ArrayList<Border> borders, File map) {
        Scanner scanMap;
        int count_line = 0;
        int num_line = B_HEIGHT / 20;
        int num_char = B_WIDTH / 20;
        //System.out.println("Access");
        try {
            scanMap = new Scanner(map);
            while (scanMap.hasNext() && count_line < num_line ) {
               //System.out.println("Line: " + count_line);
                //System.out.println("Num_char: " + num_char);
                String line = scanMap.nextLine();
                for (int count_char = 0; count_char < num_char; count_char++) {
                    //System.out.println("Char: " + count_char); 
                    if (line.length() <= count_char) {
                        break;
                    }
                    if ('x' == line.charAt(count_char)) {
                        //System.out.println("Access x == ch");
                        Border temp = new Border();
                        temp.setPosX(count_char * 20);
                        temp.setPosY(count_line * 20);
                        borders.add(temp);                        
                    }
                }
                count_line += 1;
            }
            scanMap.close();
        } catch (FileNotFoundException e) {
            System.out.println("This file is not found!");
        }
        return borders;
    }
    
    public void drawBorder(Graphics g) {        
        super.paintComponent(g);
        g.setColor(new Color(105, 46, 4));
        g.fillRect(posX, posY, 20, 20);
    }
    
}
