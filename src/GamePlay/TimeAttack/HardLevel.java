/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay.TimeAttack;

import Entity.DynamicObject.Mouse;
import Entity.DynamicObject.Snakes;
import Entity.StaticObject.Border;
import static Entity.StaticObject.Border.setBorders;
import Entity.StaticObject.ClassicFood;
import Entity.StaticObject.Clock;
import Entity.StaticObject.Heal;
import Entity.StaticObject.ItemFactory;
import Entity.StaticObject.StaticObject;
import Entity.StaticObject.Border;
import static Entity.StaticObject.Border.setBorders;
import GamePlay.Classic.Board;
import static GamePlay.Classic.Board.BLOCK_SIZE;
import GamePlay.ClassicGame;
import static GamePlay.TimeAttack.EasyBoard.foodsPos;
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
public class HardLevel extends GameState {
Snakes snake = Snakes.getInstance();
ClassicFood classicfood= new ClassicFood();;
Score time_score = new Score(new OperationAdd());
private boolean inGame = true;
private Timer timer;
private String[] options = {"Replay","Menu"}; 
private int CurrentSelection = 0;
Clock clock; 

    public HardLevel(GameStateManager gsm) {
        super(gsm);
        snake.initSnake();
        initMultiFood();
        clock = new Clock();
//        timer = new Timer(20, (ActionListener) this);
//        timer.start();
        setAllBorders();
    }
    public void init() {
      
    }
 
    public void paintComponent(Graphics g) {

    }

    public void doDrawing(Graphics g) {
        if (inGame) {    
            for (Border border : borders) {
                border.drawBorder(g);
            }
            for (int i = 0; i < foodsPos.length; i++) {
                if (foodsPos[i][0] > -1) {
                    multiFood[i].paintComponent(g);
                }
            }
            for (int i = 0; i < mice.size(); i++) {
                    mice.get(i).paintComponent(g);
            }
        snake.paintComponent(g);
        //gameover.doDrawing(g);
        String score = "Score: " + Integer.toString(time_score.getScore());
        Font small = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
        FontMetrics metr = g.getFontMetrics(small);
        g.setColor(Color.black);
        g.setFont(small);
        
        g.drawString(score, 10, 30);
        String time = "Time: " + Integer.toString(clock.getSec());    
        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(time, GameBoardPanel.B_WIDTH-(metr.stringWidth(time) + 20), 30);
            
         }
         else{
             gameOver(g);
//             gsm.states.push(new MenuState2(gsm));
         }
            

    }
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            clock.getTime();
            int count = clock.getSec();
            if(count > 60){
                inGame = false;
            }
            checkFood();
            checkCollision();
            snake.autoMove();
            locateMice();
              for (int i = 0; i < mice.size(); i++) {
                //mice.get(i).avoidSnake(snake);
                //mice.get(i).avoidOut();
                //mice.get(i).avoidBorder(borders);
                mice.get(i).autoMoveHard(borders);
            }
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
                gsm.states.push(new HardLevel(gsm));
            }
            else if(CurrentSelection == 1){
                  gsm.states.push(new MenuState(gsm));
            }
        }
    }
    public void checkCollision(){
             for (int z = snake.getLength(); z > 0; z--) {

            if ((z > 4) && (snake.getX(0) == snake.getX(z)) && (snake.getY(0) == snake.getY(z))) {
                inGame = false;
                break;
            }
        }

        for (Border border: borders) {
            if (snake.getX(0) == border.getPosX() && snake.getY(0) == border.getPosY()) {
                inGame = false;
                break;
            }
        }
                     
//        if(!inGame) {
//            timer.stop();
//        }
    }
    private void checkFood() {
         for (int j = 0; j < foodsPos.length; j++) {
            if (snake.getX(0) == foodsPos[j][0] && snake.getY(0) == foodsPos[j][1]) {
                foodOnScreen -= 1;
                fIndex = j;
                time_score.executeStrategy(multiFood[fIndex].point);
                snake.setLength(snake.getLength()+ 1);
                if (multiFood[fIndex].getClass().equals(Heal.class)) {
                    snake.setSpeed(100);
                    multiFood[fIndex].specialEffect(snake);
                } else {
                    snake.setSpeed(snake.getSpeed() + multiFood[fIndex].specialEffect(snake));
                }
                locateMultiFood();
                break;
            } else if (fSet < totalFood && j == foodsPos.length - 1){
                fIndex = fSet;
                locateMultiFood();
            }
        }
          for (int i = 0; i < mice.size(); i++) {
            if (snake.getX(0) == mice.get(i).getPosX() && snake.getY(0) == mice.get(i).getPosY()) {
                time_score.executeStrategy(mice.get(i).point);
                mice.remove(i);
            }
        }
    }
    
    public void Over(Graphics g){
        String msg = "Game Over";
        Font text = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
        Font buttons = new Font("Berlin Sans FB Demi", 1, 24);
        FontMetrics metr = g.getFontMetrics(text);

        g.setColor(Color.orange);
        g.setFont(text);
        g.drawString(msg, (GameBoardPanel.B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 150);
        
        String score = "Score: " + Integer.toString(time_score.getScore());

        g.setColor(Color.WHITE);
        g.setFont(text);
        g.drawString(score, (GameBoardPanel.B_WIDTH - metr.stringWidth(msg)) / 2 + 20, B_HEIGHT / 2 - 90);
        
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
            g.drawString(options[i],GameBoardPanel.B_WIDTH/2-50 , 300 + i*100); 
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
//                        infos.set(index, name.getText() + " " + Integer.toString(time_score.getScore()));
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
//                if (time_score.getScore() >= Integer.parseInt(piece[1])) {
//                    return counter;
//                }
//            }
//        }
//        return counter;
//    }

        private int foodOnScreen;
        private int fIndex = 0;
        private int fSet = 0;
        private final int totalFood = 10;
        private StaticObject[] multiFood;
        private ArrayList<Mouse> mice;
        public static int[][] foodsPos;
        private int awardMouse = 1;
    
     public void initMultiFood() {
       
        this.foodOnScreen = 0;
        this.fIndex = 0;
        this.fSet = 0;
        multiFood = new StaticObject[totalFood];
        mice = new ArrayList();
        foodsPos = new int[totalFood][2];
        for (int[] foodPos : foodsPos) {
            for (int j = 0; j < foodsPos[0].length; j++) {
                foodPos[j] = -1;
            }
        }
    }
      public void locateMice() {
      
        //System.out.println(awardMouse);
        //System.out.println(SCORE);
        if (time_score.getScore() >= 10 * awardMouse) {
            Mouse temp = new Mouse();
            temp.locateMouse(snake, borders);
            mice.add(temp);
            awardMouse++;
        }
    }
         public void locateMultiFood() {
        if (foodOnScreen < totalFood) {
            foodOnScreen += 1;
            int r = (int) (Math.random() * 5);
            int i = (int) ((Math.random() * 4));
//fixed            
            ItemFactory itemFactory = new ItemFactory();
            if (r <= 1) {
//fixed                
                multiFood[fIndex] = itemFactory.getItem("Apple");
            } else {
                switch(i) {
//fixed                     
                    case 0: multiFood[fIndex] = itemFactory.getItem("TeaLeaf");
                        break;
                    case 1: multiFood[fIndex] = itemFactory.getItem("Coffee");
                        break;
                    case 2: multiFood[fIndex] = itemFactory.getItem("Revert");
                        break;
                    case 3: multiFood[fIndex] = itemFactory.getItem("Heal");
                        break;
                }
            }

            multiFood[fIndex].locateFood(snake, borders,foodsPos);
            foodsPos[fIndex][0] = multiFood[fIndex].getPosX();
            foodsPos[fIndex][1] = multiFood[fIndex].getPosY();
            if (fIndex == fSet) {
                fSet += 1;
            }
        }
    }
    
    ArrayList<Border> borders = new ArrayList();
     private void setAllBorders() {
        borders = setBorders(borders, 0, GameBoardPanel.B_WIDTH, 0, 0);
        borders = setBorders(borders, 0, GameBoardPanel.B_WIDTH, GameBoardPanel.B_HEIGHT - 20, GameBoardPanel.B_HEIGHT - 20);
        borders = setBorders(borders, 0, 0, 0, GameBoardPanel.B_HEIGHT);  
        borders = setBorders(borders, GameBoardPanel.B_WIDTH - 20, GameBoardPanel.B_WIDTH - 20, 0, GameBoardPanel.B_HEIGHT);
      
        borders = setBorders(borders, 260, 260, 20, 60);
        
        borders = setBorders(borders, 400, 400, 20, 60);
        borders = setBorders(borders, 400, 440, 60, 60);
        
        borders = setBorders(borders, 700, 700, 20, 60);
        
        borders = setBorders(borders, 680, 680, 540, 580);
        borders = setBorders(borders, 680, 720, 520, 520);
        
        borders = setBorders(borders, 280, 280, 540, 580);
        
        borders = setBorders(borders, 940, GameBoardPanel.B_WIDTH, 120, 120);
        borders = setBorders(borders, 920, 920, 100, 140);        
        
        borders = setBorders(borders, 20, 60, 200, 200);
    }
       

}
