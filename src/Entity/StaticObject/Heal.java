/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.DynamicObject.Snakes;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

/**
 *
 * @author Hanh
 */
public class Heal extends StaticObject {
    public Heal() {
        point = 1;
        icon = loadImage(icon, "res\\Items\\heal.png");
    }   
    
    @Override
    public int specialEffect(Snakes snake) {
        snake.setIsRevert(false);
        return 0;
    }
}
