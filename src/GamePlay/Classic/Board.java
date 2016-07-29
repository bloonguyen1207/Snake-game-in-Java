/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay.Classic;

/**
 *
 * @author Bloo
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.DynamicObject.Snakes;
import Entity.StaticObject.ClassicFood;
import GamePlay.Snake;
import Menu.Menu;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Board extends JPanel implements ActionListener {

    private static final int B_WIDTH = 1000;
    private static final int B_HEIGHT = 600;
    public static final int DOT_SIZE = 20;
    public static final int ALL_DOTS = B_WIDTH * B_HEIGHT / DOT_SIZE / DOT_SIZE;
//    private final int RAND_POS_X = 49;
//    private final int RAND_POS_Y = 29;
    public int DELAY = 50;
    public int SCORE = 0;

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

    static Snakes snake = new Snakes();
    static ClassicFood food = new ClassicFood();
    
    public Board() {
        addKeyListener(new TAdapter());
        setBackground(new java.awt.Color(7, 123, 83));
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //loadImages();
        initGame();
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
        snake.speed = 100;
        DELAY = snake.speed;
        food.locateFood();

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
            g.drawImage(food.getIcon(), food.getPosX(), food.getPosY(), this);

            for (int z = 0; z < snake.getLength(); z++) {
                if (z == 0) {
                    g.drawImage(snake.getHead(), snake.getX(z), snake.getY(z), this);
                } else {
                    g.drawImage(snake.getIcon(), snake.getX(z), snake.getY(z), this);
                }
            }

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
        JButton ReplayButton = new javax.swing.JButton();
        
        ReplayButton.setBackground(new java.awt.Color(255, 204, 0));
        ReplayButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24));
        ReplayButton.setForeground(new java.awt.Color(204, 51, 0));
        ReplayButton.setText("Replay");
        ReplayButton.addActionListener(this::ReplayButtonActionPerformed);
        add(ReplayButton);
        ReplayButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 + 20, 200, 59);
        
        // Back to menu - In Progess
//        JButton MenuButton = new javax.swing.JButton();
//        
//        MenuButton.setBackground(new java.awt.Color(255, 204, 0));
//        MenuButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24));
//        MenuButton.setForeground(new java.awt.Color(204, 51, 0));
//        MenuButton.setText("Back to menu");
//        MenuButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                MenuButtonActionPerformed(evt);
//            }
//        });
//        add(MenuButton);
//        MenuButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 + 100, 200, 59);
    }
    
    private void ReplayButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        Snake newGame = new Snake();
        newGame.setVisible(true);
        setVisible(false);
                      
    }
    
    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        Menu mainMenu = new Menu();
        mainMenu.setVisible(true);
        setVisible(false);
                      
    }

    private void checkFood() {
        if ((snake.getX(0) == food.posX) && (snake.getY(0) == food.posY)) {
            snake.setLength(snake.getLength() + 1);
            SCORE += food.point;
            timer.setDelay(timer.getDelay() - 1);
            food.locateFood();
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
            snake.setY(0, B_HEIGHT);
        }

        if (snake.getX(0) >= B_WIDTH) {
            //inGame = false;
            snake.setX(0, 0);
        }

        if (snake.getX(0) < 0) {
            //inGame = false;
            snake.setX(0, B_WIDTH);
        }
        
        if(!inGame) {
            timer.stop();
        }
    }

    //private void locateFood() {

    //    int r = (int) (Math.random() * RAND_POS_X);
    //    food_x = ((r * DOT_SIZE));

    //    r = (int) (Math.random() * RAND_POS_Y);
    //    food_y = ((r * DOT_SIZE));
    //}

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

            if ((key == KeyEvent.VK_LEFT) && (!snake.isRightDirection())) {
                snake.setLeftDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
            }

            if ((key == KeyEvent.VK_RIGHT) && (!snake.isLeftDirection())) {
                snake.setRightDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
            }

            if ((key == KeyEvent.VK_UP) && (!snake.isDownDirection())) {
                snake.setUpDirection(true);
                snake.setRightDirection(false);
                snake.setLeftDirection(false);
            }

            if ((key == KeyEvent.VK_DOWN) && (!snake.isUpDirection())) {
                snake.setDownDirection(true);
                snake.setRightDirection(false);
                snake.setLeftDirection(false);
            }
        }
    }
}