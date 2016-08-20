/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.DynamicObject.Snakes;
import GamePlay.Classic.Board;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

/**
 *
 * @author Hanh
 */
public class Coffee extends StaticObject {
    public Coffee() {
        point = 1;
        icon = loadImage(icon, "res\\Items\\coffee-bean.png");
    }   
    
    @Override
    public int specialEffect(Snakes snake) {
        if (snake.getSpeed() < 200) {
            return 5;
        }
        return 0;
    }
}
