/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author binguyen.com
 */
public abstract class GameState {
    protected GameStateManager gsm;
    public GameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }
    public abstract void init();
    public abstract void paintComponent(Graphics g);
    public abstract void doDrawing(Graphics g);
    public abstract void actionPerformed(ActionEvent e);
    public abstract void keyPressed(KeyEvent e);
 
}
