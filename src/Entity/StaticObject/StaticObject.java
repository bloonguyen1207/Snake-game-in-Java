/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.Entity;
import static GamePlay.Board.DOT_SIZE;

/**
 *
 * @author Hanh
 */
public abstract class StaticObject extends Entity {
    private final int RAND_POS_X = 49;
    private final int RAND_POS_Y = 29;
    
    public void locateFood() {
        int r = (int) (Math.random() * RAND_POS_X);
        posX = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS_Y);
        posY = ((r * DOT_SIZE));
    }
    
    abstract public void specialEffect();
    
}
