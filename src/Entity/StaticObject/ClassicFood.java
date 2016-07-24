/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

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
    
    @Override
    public int specialEffect() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}
