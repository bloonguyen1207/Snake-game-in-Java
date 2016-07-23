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
import Entity.StaticObject.TeaLeaf;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import javax.swing.JLabel;

public class Board2 extends JPanel implements ActionListener {

    private static final int B_WIDTH = 1000;
    private static final int B_HEIGHT = 600;
    public static final int DOT_SIZE = 20;
    public static final int ALL_DOTS = B_WIDTH * B_HEIGHT / DOT_SIZE / DOT_SIZE;
//    private final int RAND_POS_X = 49;
//    private final int RAND_POS_Y = 29;
    private int DELAY = 100;

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
    static TeaLeaf food = new TeaLeaf();
    
    public Board2() {
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

            for (int z = 0; z < snake.getDots(); z++) {
                if (z == 0) {
                    g.drawImage(snake.getHead(), snake.getX(z), snake.getY(z), this);
                } else {
                    g.drawImage(snake.getIcon(), snake.getX(z), snake.getY(z), this);
                }
            }
            /*Drawing border*/
            for (int z =0; z< B_WIDTH;z++){ /*Drawing width border*/
            g.setColor(new Color(105,46,4));
            g.fillRect(z, 0, 20, 20); /*Top Border*/
            g.fillRect(z, B_HEIGHT-20, 20, 20); /*Bottom Border*/
            
            g.fillRect(B_WIDTH-740,20, 20, 20);
            g.fillRect(B_WIDTH-740,40, 20, 20);
            
            g.fillRect(B_WIDTH-600,20, 20, 20);
            g.fillRect(B_WIDTH-600,40, 20, 20);
            g.fillRect(B_WIDTH-600,60, 20, 20);
            g.fillRect(B_WIDTH-580,60, 20, 20);
            
            g.fillRect(B_WIDTH-300,20, 20, 20);
            g.fillRect(B_WIDTH-300,40, 20, 20);
            
            g.fillRect(B_WIDTH-320,B_HEIGHT-40, 20, 20);
            g.fillRect(B_WIDTH-320,B_HEIGHT-60, 20, 20);
            g.fillRect(B_WIDTH-320,B_HEIGHT-80, 20, 20);
            g.fillRect(B_WIDTH-300,B_HEIGHT-80, 20, 20);
            
            g.fillRect(B_WIDTH-720,B_HEIGHT-40, 20, 20);
            g.fillRect(B_WIDTH-720,B_HEIGHT-60, 20, 20);
            
            }
            for (int z =0; z< B_HEIGHT;z++){ /*Drawing height border*/
            g.setColor(new Color(105,46,4));
            g.fillRect(0, z, 20, 20); /*Left Border*/
            g.fillRect(B_WIDTH-20, z, 20, 20); /*Right Border*/
            
            g.fillRect(B_WIDTH-40,120, 20, 20);
            g.fillRect(B_WIDTH-60,120, 20, 20);
            g.fillRect(B_WIDTH-80,120, 20, 20);
            g.fillRect(B_WIDTH-80,100, 20, 20);
            
            g.fillRect(B_WIDTH-980,200, 20, 20);
            g.fillRect(B_WIDTH-960,200, 20, 20);
            }
            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkFood() {

        if ((snake.getX(0) == food.posX) && (snake.getY(0) == food.posY)) {
            if (DELAY > 50) {
                timer.setDelay(timer.getDelay() - food.specialEffect());
                DELAY -= food.specialEffect();
            }
            snake.setDots(snake.getDots() + 1);
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

        for (int z = snake.getDots(); z > 0; z--) {

            if ((z > 4) && (snake.getX(0) == snake.getX(z)) && (snake.getY(0) == snake.getY(z))) {
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