/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Stack;
import Menu.GameState;

/**
 *
 * @author binguyen.com
 */
public class GameStateManager {
    public Stack<GameState> states;
    public GameStateManager() { 
        states = new Stack<GameState>();
        states.push(new MenuState(this));
    }
    public void paintComponent(Graphics g) {  
        states.peek().paintComponent(g);
    }
    public void doDrawing(Graphics g) {
        states.peek().doDrawing(g);
    }
    public void actionPerformed(ActionEvent e) {
        states.peek().actionPerformed(e);}
    public void keyPressed(KeyEvent e) {
        states.peek().keyPressed(e);
    }
   
}
