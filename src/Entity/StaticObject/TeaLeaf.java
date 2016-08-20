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
public class TeaLeaf extends StaticObject {
    public TeaLeaf() {
        point = 1;
        icon = loadImage(icon, "res\\Items\\leaf.png");
    }
    
    @Override
    public int specialEffect(Snakes snake) {
        return -10;
    }
}
