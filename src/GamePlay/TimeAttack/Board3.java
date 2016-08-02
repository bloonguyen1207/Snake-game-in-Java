/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay.TimeAttack;

/**
 *
 * @author Bloo
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.DynamicObject.Snakes;
import Entity.StaticObject.Coffee;
import Entity.StaticObject.Apple;
import Entity.StaticObject.Border;
import Entity.StaticObject.Heal;
import Entity.StaticObject.Revert;
import Entity.StaticObject.StaticObject;
import Entity.StaticObject.TeaLeaf;
import GamePlay.EasyGame;
import Menu.Menu;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board3 extends JPanel implements ActionListener {

    public static final int B_WIDTH = 1000;
    public static final int B_HEIGHT = 600;
    public static final int DOT_SIZE = 20;
    public static final int ALL_DOTS = B_WIDTH * B_HEIGHT / DOT_SIZE / DOT_SIZE;
//    private final int RAND_POS_X = 49;
//    private final int RAND_POS_Y = 29;
    private final int DELAY = 100;
    public int SCORE = 0;
    public JFrame Game;

//    private final int x[] = new int[ALL_DOTS];
//    private final int y[] = new int[ALL_DOTS];

//    private int dots;
//    private int food_x;
//    private int food_y;

//    private boolean leftDirection = false;
//    private boolean rightDirection = true;
//    private boolean upDirection = false;
//    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
//    private Image ball;
//    private Image food;
//    private Image head;

    Snakes snake = new Snakes();
    Border border = new Border();
    
    //static TeaLeaf food = new TeaLeaf();
    
    public Board3(JFrame Game) {
        this.Game = Game;
        addKeyListener(new TAdapter());
        setBackground(new Color(7, 123, 83));
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //loadImages();
        initGame();
    }
    
    public JFrame getContainer() {
        return this.Game;
    }

    //private void loadImages() {

        //ImageIcon iid = new ImageIcon(new ImageIcon("res\\Items\\dot.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        //ball = iid.getImage();

        //ImageIcon iia = new ImageIcon(new ImageIcon("res\\Items\\coffee-bean.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        //leaf = iia.getImage();

        //ImageIcon iih = new ImageIcon(new ImageIcon("res\\Items\\head.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        //head = iih.getImage();
    //}

    private void initGame() {

        //dots = 3;

        //for (int z = 0; z < dots; z++) {
        //    x[z] = 50 - z * 10;
        //    y[z] = 50;
        //}
        snake.initSnake();
        //food.locateFood();
        initMultiFood();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        if (inGame) {
            //g.drawImage(food.getIcon(), food.getPosX(), food.getPosY(), this);
//            snake.paintComponent(g);
            //food.paintComponent(g);
           border.drawBorder(g);
            for (int i = 0; i < foodsPos.length; i++) {
                if (foodsPos[i][0] > -1) {
                    multiFood[i].paintComponent(g);
                }
            }
            
            for (int z = 0; z < snake.getLength(); z++) {
                if (z == 0) {
                    g.drawImage(snake.getHead(), snake.getX(z), snake.getY(z), this);
                } else {
                    g.drawImage(snake.getIcon(), snake.getX(z), snake.getY(z), this);
                }
            }
            
            String score = "Score: " + Integer.toString(SCORE);
            Font small = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
            FontMetrics metr = getFontMetrics(small);
            g.setColor(Color.black);
            g.setFont(small);
            g.drawString(score, 10, 30);
            
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.orange);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 150);
        
        String score = "Score: " + Integer.toString(SCORE);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(score, (B_WIDTH - metr.stringWidth(msg)) / 2 + 20, B_HEIGHT / 2 - 90);
        
        //TODO: Delete when done
        String speed = "Speed: " + Integer.toString(timer.getDelay());

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(speed, (B_WIDTH - metr.stringWidth(msg)) / 2 + 20, B_HEIGHT / 2 - 30);
        // END
        
        // Replay
        JButton ReplayButton = new JButton();
        
        ReplayButton.setBackground(new java.awt.Color(255, 204, 0));
        ReplayButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24));
        ReplayButton.setForeground(new java.awt.Color(204, 51, 0));
        ReplayButton.setText("Replay");
        ReplayButton.addActionListener(this::ReplayButtonActionPerformed);
        add(ReplayButton);
        ReplayButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 + 20, 200, 59);
        
        // Back to menu - In Progess
        JButton MenuButton = new JButton();
//        
        MenuButton.setBackground(new java.awt.Color(255, 204, 0));
        MenuButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24));
        MenuButton.setForeground(new java.awt.Color(204, 51, 0));
        MenuButton.setText("Back to menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });
        add(MenuButton);
        MenuButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 + 100, 200, 59);
    }
    
    private void ReplayButtonActionPerformed(ActionEvent evt) {                                            
        // TODO add your handling code here:
        EasyGame newGame = new EasyGame();
        newGame.setVisible(true);
        this.getContainer().setVisible(false);
                      
    }
    
    private void MenuButtonActionPerformed(ActionEvent evt) {                                            
        // TODO add your handling code here:
        Menu mainMenu = new Menu();
        mainMenu.setVisible(true);
        this.getContainer().setVisible(false);
    }

    private void checkFood() {                
        //collision with multifood
        for (int j = 0; j < foodsPos.length; j++) {
            if (snake.getX(0) == foodsPos[j][0] && snake.getY(0) == foodsPos[j][1]) {
                numOnScreen -= 1;
                fIndex = j;
                SCORE += multiFood[fIndex].point;
                snake.setLength(snake.getLength()+ 1);
                if (multiFood[fIndex].getClass().equals(Heal.class)) {
                    timer.setDelay(DELAY);
                    multiFood[fIndex].specialEffect(snake);
                } else if (timer.getDelay() <= 20 && multiFood[fIndex].getClass().equals(TeaLeaf.class)) {
                    timer.setDelay(timer.getDelay() + multiFood[fIndex].specialEffect(snake));
                } else if (timer.getDelay() >= 200 && multiFood[fIndex].getClass().equals(Coffee.class)) {
                    timer.setDelay(timer.getDelay() + multiFood[fIndex].specialEffect(snake));
                } else if (timer.getDelay() > 20 && timer.getDelay() < 200) {
                    timer.setDelay(timer.getDelay() + multiFood[fIndex].specialEffect(snake));
                } else {
                    multiFood[fIndex].specialEffect(snake);
                }
                locateMultiFood();
                break;
            } else if (fSet < totalFood && j == foodsPos.length - 1){
                fIndex = fSet;
                locateMultiFood();
            }
        }
    }

/*    private void move() {

        for (int z = snake.getDots(); z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }
*/
    private void checkCollision() {

        for (int z = snake.getLength(); z > 0; z--) {

            if ((z > 4) && (snake.getX(0) == snake.getX(z)) && (snake.getY(0) == snake.getY(z))) {
                inGame = false;
            }
        }

        if (snake.getY(0) == B_HEIGHT-20) {
            inGame = false;
            //snake.setY(0, 0);
        }

        if (snake.getY(0) < 20) {
            inGame = false;
            //snake.setY(0, B_HEIGHT);
        }

        if (snake.getX(0) == B_WIDTH-20) {
            inGame = false;
            //snake.setX(0, 0);
        }

        if (snake.getX(0) < 20) {
            inGame = false;
            //snake.setX(0, B_WIDTH);
        }
        
        if(!inGame) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {

            checkFood();
            checkCollision();
            snake.move();
          
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            
            if ((key == KeyEvent.VK_LEFT) && (
                    (!snake.isRightDirection() && !snake.isIsRevert())
                    || (snake.isIsRevert() && !snake.isLeftDirection()))) {
                snake.setLeftDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
                snake.setRightDirection(false);
                snake.revertDirection();
            }

            if ((key == KeyEvent.VK_RIGHT) && (
                    (!snake.isLeftDirection() && !snake.isIsRevert())
                    || (snake.isIsRevert() && !snake.isRightDirection()))) {
                snake.setRightDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
                snake.setLeftDirection(false);
                snake.revertDirection();
            }

            if ((key == KeyEvent.VK_UP) && (
                    (!snake.isDownDirection() && !snake.isIsRevert())
                    || (snake.isIsRevert() && !snake.isUpDirection()))) {
                snake.setUpDirection(true);
                snake.setRightDirection(false);
                snake.setLeftDirection(false);
                snake.setDownDirection(false);
                snake.revertDirection();
            }

            if ((key == KeyEvent.VK_DOWN) && (
                    (!snake.isUpDirection() && !snake.isIsRevert())
                    || (snake.isIsRevert() && !snake.isDownDirection()))) {
                snake.setDownDirection(true);
                snake.setRightDirection(false);
                snake.setLeftDirection(false);
                snake.setUpDirection(false);
                snake.revertDirection();
            }
        }
    }

    private int numOnScreen;
    private int fIndex = 0;
    private int fSet = 0;
    private final int totalFood = 10;
    private StaticObject[] multiFood;
    public static int[][] foodsPos;
    private void initMultiFood() {
        this.numOnScreen = 0;
        this.fIndex = 0;
        this.fSet = 0;
        multiFood = new StaticObject[totalFood];
        foodsPos = new int[totalFood][2];
        for (int[] foodPos : foodsPos) {
            for (int j = 0; j < foodsPos[0].length; j++) {
                foodPos[j] = -1;
            }
        }
    }
    private void locateMultiFood() {
        if (numOnScreen < totalFood) {
            numOnScreen += 1;
            int r = (int) (Math.random() * 5);
            int i = (int) ((Math.random() * 4));
            if (r <= 1) {
                multiFood[fIndex] = new Apple();
            } else {
                switch(i) {
                    case 0: multiFood[fIndex] = new TeaLeaf();
                        break;
                    case 1: multiFood[fIndex] = new Coffee();
                        break;
                    case 2: multiFood[fIndex] = new Revert();
                        break;
                    case 3: multiFood[fIndex] = new Heal();
                        break;
                }
            }
//            if (r == 0 || r == 1 || r = 2 || r = 3) {
//                multiFood[fIndex] = new Apple();
//            } else if (r == 0 || r == 1) {
//                multiFood[fIndex] = new TeaLeaf();
//            } else if (r == 3) {
//                multiFood[fIndex] = new Heal();
//            } else if (r == 4) {
//                multiFood[fIndex] = new Coffee();
//            } else if (r == 5) {
//                multiFood[fIndex] = new Revert();
//            }
            multiFood[fIndex].locateFood(snake);
            foodsPos[fIndex][0] = multiFood[fIndex].getPosX();
            foodsPos[fIndex][1] = multiFood[fIndex].getPosY();
            if (fIndex == fSet) {
                fSet += 1;
            }
        }
    }
}