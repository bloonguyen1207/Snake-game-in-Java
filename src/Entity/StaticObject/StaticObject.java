/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.DynamicObject.Snakes;
import Entity.Entity;
import static GamePlay.Classic.Board.BLOCK_SIZE;
import static GamePlay.TimeAttack.Board3.foodsPos;
import java.awt.event.ActionListener;

/**
 *
 * @author Hanh
 */
public abstract class StaticObject extends Entity {
    public final int RAND_POS_X = 49;
    public final int RAND_POS_Y = 29;
    
    public void locateFood(Snakes snake ) {
        boolean isCorrect = false;   
        while (!isCorrect) {
            int checkFood = 0;
            int checkSnake = 0;
            int r = (int) (Math.random() * RAND_POS_X);
            posX = ((r * BLOCK_SIZE));

            r = (int) (Math.random() * RAND_POS_Y);
            posY = ((r * BLOCK_SIZE));
           
            for (int[] foodPos : foodsPos) {                
                if (!(posX == foodPos[0] && posY == foodPos[1])) {
                    checkFood += 1;
                }
            }
            
            for (int z = 0; z < snake.getLength(); z++) {
                if (!(posX == snake.getX(z) && posY == snake.getY(z))) {
                    checkSnake += 1;
                }
            }
                    
            if (checkFood == foodsPos.length && checkSnake == snake.getLength()) {
                isCorrect = true;
            }
            
        }       
                
    }
    
    abstract public int specialEffect(Snakes snake);
    
}
