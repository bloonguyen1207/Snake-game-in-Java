/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.DynamicObject.Snakes;
import Entity.Entity;
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
public class Border extends StaticObject {
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
    
//    public static ArrayList<Border> setBorders(ArrayList<Border> borders, int beginX, int endX, int beginY, int endY) {        
//        //System.out.println(beginX != endX);
//        //System.out.println(beginY == endY);
//        if (beginX != endX && beginY == endY) {
//            for (int i = beginX / 20; i < endX / 20; i++) {
//                Border temp = new Border();
//                temp.setPosX(i * 20);
//                temp.setPosY(beginY);
//                //System.out.println(i);
//                borders.add(temp);
//            }
//        } else if (beginX == endX && beginY != endY) {
//            for (int i = beginY / 20; i < endY / 20; i++) {
//                Border temp = new Border();
//                temp.setPosY(i * 20);
//                temp.setPosX(beginX);
//                borders.add(temp);
//            }
//        } else if (beginX == endX && beginY == endY) {
//            Border temp = new Border();
//            temp.setPosX(beginX);
//            temp.setPosY(beginY);
//            borders.add(temp);
//        }
//        
//        return borders;
//    }
    
    public void drawBorder(Graphics g) {        
        super.paintComponent(g);
        g.setColor(new Color(105, 46, 4));
        g.fillRect(posX, posY, 20, 20);
    }
    
    public void drawBorder1(Graphics g){
        super.paintComponent(g);
        /*Drawing border*/
        for (int z =0; z< B_WIDTH;z++){ /*Drawing width border*/
            g.setColor(new Color(105,46,4));
            g.fillRect(z, 0, 20, 20); /*Top Border*/
            g.fillRect(z, B_HEIGHT-20, 20, 20); /*Bottom Border*/
            
            g.fillRect(B_WIDTH-740,20, 20, 20);
            g.fillRect(B_WIDTH-740,40, 20, 20);
            
            g.fillRect(B_WIDTH-600,20, 20, 20);
            g.fillRect(B_WIDTH-600,40, 20, 20);
            g.fillRect(B_WIDTH-600,60, 20, 20);
            g.fillRect(B_WIDTH-580,60, 20, 20);
            
            g.fillRect(B_WIDTH-300,20, 20, 20);
            g.fillRect(B_WIDTH-300,40, 20, 20);
            
            g.fillRect(B_WIDTH-320,B_HEIGHT-40, 20, 20);
            g.fillRect(B_WIDTH-320,B_HEIGHT-60, 20, 20);
            g.fillRect(B_WIDTH-320,B_HEIGHT-80, 20, 20);
            g.fillRect(B_WIDTH-300,B_HEIGHT-80, 20, 20);
            
            g.fillRect(B_WIDTH-720,B_HEIGHT-40, 20, 20);
            g.fillRect(B_WIDTH-720,B_HEIGHT-60, 20, 20);
            
        }
        for (int z =0; z< B_HEIGHT;z++){ /*Drawing height border*/
            g.setColor(new Color(105,46,4));
            g.fillRect(0, z, 20, 20); /*Left Border*/
            g.fillRect(B_WIDTH-20, z, 20, 20); /*Right Border*/
            
            g.fillRect(B_WIDTH-40,120, 20, 20);
            g.fillRect(B_WIDTH-60,120, 20, 20);
            g.fillRect(B_WIDTH-80,120, 20, 20);
            g.fillRect(B_WIDTH-80,100, 20, 20);
            
            g.fillRect(B_WIDTH-980,200, 20, 20);
            g.fillRect(B_WIDTH-960,200, 20, 20);
            }
        }

    @Override
    public int specialEffect(Snakes snake) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
