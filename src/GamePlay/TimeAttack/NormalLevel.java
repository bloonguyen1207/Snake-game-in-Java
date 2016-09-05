package GamePlay.TimeAttack;


import GamePlay.TimeAttack.TimeLevel;
import Menu.GameStateManager;
import Menu.MenuState;
import java.awt.event.KeyEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hanh
 */
public class NormalLevel extends TimeLevel {
    public NormalLevel(GameStateManager gsm) {
        super(gsm);
        setAllBorders();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        snake.keyPressed(e); 
        int k = e.getKeyCode();
        if(k == KeyEvent.VK_DOWN){
            CurrentSelection++;
            if (CurrentSelection >= options.length){
                CurrentSelection = 0;
            }
    }
        else if (k == KeyEvent.VK_UP){
            CurrentSelection--;
            if (CurrentSelection <0){
                CurrentSelection = options.length-1;
            }
        }
        if(k == KeyEvent.VK_ENTER){
            //Start button 
            if(CurrentSelection == 0){
                gsm.states.push(new NormalLevel(gsm));
            }
            else if(CurrentSelection == 1){
                  gsm.states.push(new MenuState(gsm));
            }
        }
    }
    
    protected void setMap() {
        mapName = "res\\Map\\normalMap.txt";
    }
}
