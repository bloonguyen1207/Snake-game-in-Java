/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.DynamicObject.Snakes;
import Entity.Entity;
import static GamePlay.Classic.Board.DOT_SIZE;
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
        int x, y;
        boolean isCorrect = false;   
        while (!isCorrect) {
            int checkFood = 0;
            int checkSnake = 0;
            x = (int) (Math.random() * RAND_POS_X);
            posX = ((x * DOT_SIZE));

            y = (int) (Math.random() * RAND_POS_Y);
            posY = ((y * DOT_SIZE));
           
            for (int[] foodsPo : foodsPos) {
                if (!(x == foodsPo[0] && y == foodsPo[1])) {
                    checkFood += 1;
                }
            }
            
            for (int z = 0; z < snake.getLength(); z++) {
                if (!(x == snake.getX(z) && y == snake.getY(z))) {
                    checkSnake += 1;
                }
            }
                    
            if (checkFood == foodsPos.length && checkSnake == snake.getLength()) {
                isCorrect = true;
            }
            
        }       
                
    }
    
    abstract public int specialEffect();
    
}
