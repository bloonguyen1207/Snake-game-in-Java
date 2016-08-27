/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import GamePlay.EasyGame;
import GamePlay.TimeAttack.EasyBoard;
import GamePlay.TimeAttack.GameBoardPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import GamePlay.ClassicGame;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
/**
 *
 * @author binguyen.com
 */
public class MenuState2 extends GameState {
    private String[] options = {"Classic","Time Attack","Back to Main Menu"}; 
    private int CurrentSelection = 0;
    public MenuState2(GameStateManager gsm){
        super(gsm);
        
    }
    public void init() {
        
    }

    public void paintComponent(Graphics g) {
        
    }

    
    public void doDrawing(Graphics g) {
        g.setColor(new Color(7, 123, 83));
        g.fillRect(0, 0, GameBoardPanel.B_WIDTH, GameBoardPanel.B_HEIGHT);
        for (int i = 0; i < options.length;i++){
            if(i == CurrentSelection){
                g.setColor(Color.YELLOW);
            }
            else {
                g.setColor(Color.WHITE);
            }
            g.setFont(new Font("Arial",Font.PLAIN,30));
            g.drawString(options[i],EasyBoard.B_WIDTH/2-50 , 100 + i*100); 
        }
    }

    public void actionPerformed(ActionEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {
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
            }
            else if(CurrentSelection == 1){
                
            }
            else if (CurrentSelection == 2){
                gsm.states.push(new MenuState(gsm));
            }
        }
    }

}
