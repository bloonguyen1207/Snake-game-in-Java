/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.DynamicObject.Snakes;
import static GamePlay.Classic.Board.BLOCK_SIZE;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

/**
 *
 * @author Bloo
 */
public class ClassicFood extends StaticObject {
    public ClassicFood() {
        point = 1;
        icon = loadImage(icon, "res\\Items\\dota.png");
    }
    
    public void locateFood(Snakes snake) {
        boolean isCorrect = false;   
        while (!isCorrect) {
            int checkFood = 0;
            int checkSnake = 0;
            int r = (int) (Math.random() * RAND_POS_X);
            posX = ((r * BLOCK_SIZE));

            r = (int) (Math.random() * RAND_POS_Y);
            posY = ((r * BLOCK_SIZE));
           
            for (int z = 0; z < snake.getLength(); z++) {
                if (!(posX == snake.getX(z) && posY == snake.getY(z))) {
                    checkSnake += 1;
                }
            }
                    
            if (checkSnake == snake.getLength()) {
                isCorrect = true;
            }
            
        }            
    }
    
    @Override
    public int specialEffect(Snakes snake) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}
