/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

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

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 1000;
    private final int B_HEIGHT = 600;
    private final int DOT_SIZE = 20;
    private final int ALL_DOTS = B_WIDTH * B_HEIGHT / (DOT_SIZE * DOT_SIZE);
    private final int RAND_POSX = 49;
    private final int RAND_POSY = 29;
    private final int DELAY = 50;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int foodX;
    private int foodY;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image leaf;
    private Image head;

    public Board() {

        addKeyListener(new TAdapter());
        setBackground(Color.white);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon(new ImageIcon("res\\Items\\dot.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(new ImageIcon("res\\Items\\coffee-bean.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        leaf = iia.getImage();

        ImageIcon iih = new ImageIcon(new ImageIcon("res\\Items\\head.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        head = iih.getImage();
    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = DOT_SIZE * 5 - z * (DOT_SIZE / 2);
            y[z] = DOT_SIZE * 5;
        }

        locateFood();

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

            g.drawImage(leaf, foodX, foodY, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Comic Sans", Font.BOLD, 40);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkFood() {

        if ((x[0] == foodX) && (y[0] == foodY)) {

            dots++;
            locateFood();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
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

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            y[0] = 0;
//            inGame = false;
        }

        if (y[0] < 0) {
            y[0] = B_HEIGHT;
//            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            x[0] = 0;
//            inGame = false;
        }

        if (x[0] < 0) {
            x[0] = B_HEIGHT;
            inGame = false;
        }
        
        if(!inGame) {
            timer.stop();
        }
    }

    private void locateFood() {

        int r = (int) (Math.random() * RAND_POSX);
        foodX = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POSY);
        foodY = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkFood();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}