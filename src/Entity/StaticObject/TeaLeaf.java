/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.DynamicObject.Snakes;


public class TeaLeaf extends StaticObject {
    public TeaLeaf() {
        point = 1;
        icon = loadImage(icon, "res\\Items\\leaf.png");
    }
    
    @Override
    public int specialEffect(Snakes snake) {
        if (snake.getSpeed() > 50) {
            return -5;                        
        }
        return 0;
    }
}
