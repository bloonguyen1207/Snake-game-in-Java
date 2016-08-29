/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay.Classic;

import Entity.DynamicObject.Snakes;
import Entity.StaticObject.ClassicFood;
import GamePlay.Classic.Board;
import static GamePlay.Classic.Board.BLOCK_SIZE;
import GamePlay.ClassicGame;
import GamePlay.TimeAttack.GameBoardPanel;
import static GamePlay.TimeAttack.GameBoardPanel.B_HEIGHT;
import static GamePlay.TimeAttack.GameBoardPanel.B_WIDTH;
import Menu.GameState;
import Menu.GameStateManager;
import Menu.Menu;
import Menu.MenuState;
import Menu.MenuState2;
import Score.OperationAdd;
import Score.Score;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author binguyen.com
 */
public class ClassicLevel extends GameState {
Snakes snake = Snakes.getInstance();
ClassicFood classicfood= new ClassicFood();;
Score classic_score = new Score(new OperationAdd());
private boolean inGame = true;
private Timer timer;
private String[] options = {"Replay","Menu"}; 
private int CurrentSelection = 0;


    public ClassicLevel(GameStateManager gsm) {
        super(gsm);
        classicfood.locateFood(snake);
        snake.initSnake();
//        timer = new Timer(20,this);
//        timer.start();
    }
    public void init() {
      
    }
 
    public void paintComponent(Graphics g) {

    }

    public void doDrawing(Graphics g) {
         if (inGame) {
        classicfood.paintComponent(g);
        snake.paintComponent(g);
        //gameover.doDrawing(g);
        String score = "Score: " + Integer.toString(classic_score.getScore());
        Font small = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
            //FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(score, 10, 30);
         }
         else{
             gameOver(g);
//             gsm.states.push(new MenuState2(gsm));
         }
            

    }
    public void actionPerformed(ActionEvent e) {
        if(inGame){
          snake.autoMove();
          checkCollision();
          checkFood();
        }
    }
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
                gsm.states.push(new ClassicLevel(gsm));
            }
            else if(CurrentSelection == 1){
                  gsm.states.push(new MenuState(gsm));
            }
        }
    }
    public void checkCollision(){
          for (int z = snake.getLength(); z > 0; z--) {
            if ((z > 3) && (snake.getX(0) == snake.getX(z)) && (snake.getY(0) == snake.getY(z))) {
                inGame = false;
            }
        }
        if (snake.getY(0) >= B_HEIGHT) {
            //inGame = false;
            snake.setY(0, 0);
        }

        if (snake.getY(0) < 0) {
            //inGame = false;
            snake.setY(0, B_HEIGHT - BLOCK_SIZE);
        }

        if (snake.getX(0) >= B_WIDTH) {
            //inGame = false;
            snake.setX(0, 0);
        }

        if (snake.getX(0) < 0) {
            //inGame = false;
            snake.setX(0, B_WIDTH - BLOCK_SIZE);
        }
        
//        if(!inGame) {
//            timer.stop();
//        }
    }
    private void checkFood() {
        if ((snake.getX(0) == classicfood.posX) && (snake.getY(0) == classicfood.posY)) {
            snake.setLength(snake.getLength() + 1);
            classic_score.executeStrategy(classicfood.point);            
            snake.setSpeed(snake.getSpeed() + classicfood.specialEffect(snake));
            classicfood.locateFood(snake);
        }
    }
    public void Over(Graphics g){
        String msg = "Game Over";
        Font text = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
        Font buttons = new Font("Berlin Sans FB Demi", 1, 24);
        FontMetrics metr = g.getFontMetrics(text);

        g.setColor(Color.orange);
        g.setFont(text);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 150);
        
        String score = "Score: " + Integer.toString(classic_score.getScore());

        g.setColor(Color.WHITE);
        g.setFont(text);
        g.drawString(score, (B_WIDTH - metr.stringWidth(msg)) / 2 + 20, B_HEIGHT / 2 - 90);
        
    }
        private void gameOver(Graphics g){
        Over(g);
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
            g.drawString(options[i],B_WIDTH/2-50 , 300 + i*100); 
        }
        
//        if (newHighScore() > -1) {
//            JTextField name = new JTextField(10);
//            name.setBackground(new Color(255, 204, 0));
//            name.setFont(text);
//            name.setText("AAA");
//            name.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 - 70, 150, 50);
//            add(name);
//
//            //TODO: Delete when done
//            String newHighScore = "NEW HIGHSCORE!!!";
//    
//            g.setColor(Color.yellow);
//            g.setFont(text);
//            g.drawString(newHighScore, (B_WIDTH - metr.stringWidth(msg)) / 2 - 50, B_HEIGHT / 2 - 200);
//            // END
//
//            // Submit
//            JButton SubmitButton = new JButton();
//
//            SubmitButton.setBackground(new Color(255, 204, 0));
//            SubmitButton.setFont(buttons);
//            SubmitButton.setForeground(new Color(204, 51, 0));
//            SubmitButton.setIcon(new ImageIcon(new ImageIcon("res\\Menu\\submit.png").getImage().getScaledInstance(BLOCK_SIZE, BLOCK_SIZE, Image.SCALE_DEFAULT)));
//            SubmitButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt){
//                    try {
//                        StringBuilder data = new StringBuilder();
//                        int index = newHighScore();
//                        ArrayList<String> infos = readFile();
//                        for (int i = 9; i > index; i--) {
//                            infos.set(i, infos.get(i - 1));
//                        }
//                        infos.set(index, name.getText() + " " + Integer.toString(classic_score.getScore()));
//                        try {
//                            output = new FileWriter(highscores, false);
//                            writeFiles = new BufferedWriter(output);
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
//                        }
//                        for (int i = 0; i < 10; i++) {
//                            data.append(infos.get(i));
//
//                            writeFiles.write(data.toString());
//                            writeFiles.newLine();
//                            writeFiles.flush();
//                            data.delete(0, data.length());
//                        }
//                    } catch (Exception ex) {
//                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    SubmitButton.setIcon(new ImageIcon(new ImageIcon("res\\Menu\\check.png").getImage().getScaledInstance(BLOCK_SIZE, BLOCK_SIZE, Image.SCALE_DEFAULT)));
//                    SubmitButton.setEnabled(false);
//                }   
//            });
//            add(SubmitButton);
//            SubmitButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 + 130, B_HEIGHT / 2 - 70, 50, 50);
//        }
//        // Replay
//        JButton ReplayButton = new JButton();
//        
//        ReplayButton.setBackground(new Color(255, 204, 0));
//        ReplayButton.setFont(buttons);
//        ReplayButton.setForeground(new Color(204, 51, 0));
//        ReplayButton.setText("Replay");
//        ReplayButton.addActionListener(this::ReplayButtonActionPerformed);
//        add(ReplayButton);
//        ReplayButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 + 20, 200, 59);
//        
//        // Back to menu - In Progess
//        JButton MenuButton = new JButton();
//        
//        MenuButton.setBackground(new Color(255, 204, 0));
//        MenuButton.setFont(buttons);
//        MenuButton.setForeground(new Color(204, 51, 0));
//        MenuButton.setText("Back to menu");
//        //MenuButton.addActionListener(this::MenuButtonActionPerformed);
//        add(MenuButton);
//        MenuButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 + 100, 200, 59);
    }
    
//    private void ReplayButtonActionPerformed(ActionEvent evt) {                                            
//        // TODO add your handling code here:
//        ClassicGame newGame = new ClassicGame();
//        newGame.setVisible(true);
//        this.getContainer().setVisible(false);
//                      
//    }
//    
//    private void MenuButtonActionPerformed(ActionEvent evt) {                                            
//        // TODO add your handling code here:
//        Menu mainMenu = new Menu();
//        mainMenu.setVisible(true);
//        this.getContainer().setVisible(false);
//    }
//      public int newHighScore() throws Exception {
//        String info;
//        int counter = -1;
////        ArrayList<Player> players = new ArrayList<Player>(10);
//        if (!inGame) {
//            try {
//                highscores = new File(System.getProperty("user.dir") + ("/classic.txt"));
//                readFiles = new Scanner(highscores);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//
//            while (readFiles.hasNext()) {
//                counter++;
//                info = readFiles.nextLine();
//                String[] piece = info.split(" ");
//                if (classic_score.getScore() >= Integer.parseInt(piece[1])) {
//                    return counter;
//                }
//            }
//        }
//        return counter;
//    }

    

}
