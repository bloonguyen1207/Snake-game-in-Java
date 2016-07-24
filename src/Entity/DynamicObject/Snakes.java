/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.DynamicObject;

/**
 *
 * @author Hanh
 */
import static GamePlay.Classic.Board.ALL_DOTS;
import static GamePlay.Classic.Board.DOT_SIZE;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class Snakes extends DynamicObject{
    private int dots = 3;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private Image head;
    
    public Snakes() {
        head = loadImage(head, "res\\Items\\heada.png");
        icon = loadImage(icon, "res\\Items\\body_1.png");
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setDots(int length) {
        this.dots = length;
    }
    
    public int getDots() {
        return this.dots;
    }
    
    public void setX(int index, int value) {
        x[index] = value;
    }
    
    public void setY(int index, int value) {
        y[index] = value;
    }
    
    public int getX(int index) {
        return x[index];
    }
    
    public int getY(int index) {
        return y[index];
    }
    
    public Image getHead() {
        return this.head;
    }
    
    public void setDefault() {
        setDots(3);
        setLeftDirection(false);
        setUpDirection(false);
        setDownDirection(false);
        setRightDirection(true);
    }
    
    @Override
    public void move() {
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

    public void initSnake() {
        setDefault();
        for (int z = 0; z < dots; z++) {
            x[z] = DOT_SIZE * 5 - z * (DOT_SIZE / 2);
            y[z] = DOT_SIZE * 5;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(icon, x[z], y[z], this);
                }
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
