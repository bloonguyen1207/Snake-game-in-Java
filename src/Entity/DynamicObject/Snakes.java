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
import static GamePlay.Classic.Board.LENGTH;
import static GamePlay.Classic.Board.DELAY;
import static GamePlay.Classic.Board.BLOCK_SIZE;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Timer;



public class Snakes extends DynamicObject{

    private int length = 3;
    private final int x[] = new int[LENGTH];
    private final int y[] = new int[LENGTH];
    private Image head;
    private boolean isRevert = false;
//fixed
    private static final Snakes instance = new Snakes();

    public boolean isIsRevert() {
        return isRevert;
    }

    public void setIsRevert(boolean isRevert) {
        this.isRevert = isRevert;
    }

//fixed
    private Snakes() {
        head = loadImage(head, "res\\Items\\heada.png");
        icon = loadImage(icon, "res\\Items\\body_1.png");
        previousTime = System.currentTimeMillis();
        speed = 100;
    }

//fixed
    public static Snakes getInstance() {
        return instance;
    }
    
    public double getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public int getLength() {
        return this.length;
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
        setLength(3);
        setLeftDirection(false);
        setUpDirection(false);
        setDownDirection(false);
        setRightDirection(true);
    }
    
    public void revertDirection() {
        if (isRevert) {
            if (leftDirection) {
                leftDirection = false;
                rightDirection = true;
            } else if (rightDirection) {
                leftDirection = true;
                rightDirection = false;
            } else if (upDirection) {
                upDirection = false;
                downDirection = true;                
            } else if (downDirection) {
                upDirection = true;
                downDirection = false;
            }
        }
    }
    @Override
    protected void move() {
        for (int z = length; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
                
        if (leftDirection) {
            x[0] -= BLOCK_SIZE;
        }

        if (rightDirection) {
            x[0] += BLOCK_SIZE;
        }

        if (upDirection) {
            y[0] -= BLOCK_SIZE;
        }

        if (downDirection) {
            y[0] += BLOCK_SIZE;
        }
    }
    
    @Override
    public void autoMove(){        
        currentTime = System.currentTimeMillis();        
        double del = (currentTime - previousTime) / 100;
        System.out.println(del);
        double timepoint = 1.0 / speed;
        System.out.println(timepoint);
        if (del >= timepoint) {
            move();
            previousTime = currentTime;
        }
    }
    
    public void initSnake() {
        setDefault();
        for (int z = 0; z < length; z++) {
            x[z] = BLOCK_SIZE * 5 - z * (BLOCK_SIZE / 2);
            y[z] = BLOCK_SIZE * 5;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        for (int z = 0; z < length; z++) {
            if (z == 0) {
                g.drawImage(head, x[z], y[z], this);
            } else {
                g.drawImage(icon, x[z], y[z], this);
            }
        }
    }
    
}
