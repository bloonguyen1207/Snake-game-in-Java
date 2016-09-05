/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import GamePlay.Game;
import GamePlay.TimeAttack.GameBoardPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static javafx.scene.text.Font.font;
import GamePlay.Classic.ClassicLevel;
import GamePlay.TimeAttack.EasyLevel;
import GamePlay.TimeAttack.HardLevel;
import GamePlay.TimeAttack.NormalLevel;
import GamePlay.TimeAttack.ExpertLevel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
/**
 *
 * @author binguyen.com
 */
public class MenuState3 extends GameState {
    private String[] options = {"Easy","Normal","Hard","Expert","Back to Main Menu"}; 
    private int CurrentSelection = 0;
    public MenuState3(GameStateManager gsm){
        super(gsm);
    }
    public void init() {
        
    }

    public void paintComponent(Graphics g) {
         Graphics2D g1 = (Graphics2D) g;
        Image img1 = Toolkit.getDefaultToolkit().getImage("res\\Menu\\snake.png");
        g1.drawImage(img1,GameBoardPanel.B_WIDTH/2-110,50,null);
        
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
            g.setFont(new Font("Berlin Sans FB Demi",Font.PLAIN,30));
            g.drawString(options[i],GameBoardPanel.B_WIDTH/2-50, 250 + i*80); 
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
                gsm.states.push(new EasyLevel(gsm));
            }
            else if(CurrentSelection == 1){
                gsm.states.push(new NormalLevel(gsm));
            }
            else if (CurrentSelection == 2){
                gsm.states.push(new HardLevel(gsm));
            }
            else if (CurrentSelection == 3){
                gsm.states.push(new ExpertLevel(gsm));
            }
            else if (CurrentSelection == 4){
                gsm.states.push(new MenuState(gsm));
            }
        }
    }

}
