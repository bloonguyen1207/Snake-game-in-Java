/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import GamePlay.Classic.Board;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

/**
 *
 * @author Hanh
 */
public class Heal extends StaticObject {
    public Heal() {
        icon = loadImage(icon, "res\\Items\\heal.png");
    }   
    
    @Override
    public int specialEffect() {
        return 0;
    }
}
