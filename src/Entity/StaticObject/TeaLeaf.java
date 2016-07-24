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
public class TeaLeaf extends StaticObject {
    public TeaLeaf() {
        icon = loadImage(icon, "res\\Items\\leaf.png");
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(icon, posX, posY, this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    @Override
    public int specialEffect() {
        return 10;
    }
}
