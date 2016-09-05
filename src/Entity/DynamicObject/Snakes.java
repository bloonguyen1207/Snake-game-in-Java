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
import GamePlay.TimeAttack.GameBoardPanel;
import static GamePlay.TimeAttack.GameBoardPanel.B_HEIGHT;
import static GamePlay.TimeAttack.GameBoardPanel.B_WIDTH;
import static GamePlay.TimeAttack.GameBoardPanel.BLOCK_SIZE;
import static GamePlay.TimeAttack.GameBoardPanel.ALL_DOTS;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;



public class Snakes extends DynamicObject{

    private int length = 3;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
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
    public Snakes() {
        head = loadImage(head, "res\\Items\\heada.png");
        icon = loadImage(icon, "res\\Items\\body_1.png");
    }

//fixed
    public static Snakes getInstance() {
        return instance;
    }
    
    public double getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(double speed) {
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
    
    private void setDefault() {
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
            x[0] -= GameBoardPanel.BLOCK_SIZE;
        }

        if (rightDirection) {
            x[0] += GameBoardPanel.BLOCK_SIZE;
        }

        if (upDirection) {
            y[0] -= GameBoardPanel.BLOCK_SIZE;
        }

        if (downDirection) {
            y[0] += GameBoardPanel.BLOCK_SIZE;
        }
    }
    
    @Override
    public void autoMove(){     
        currentTime = System.currentTimeMillis();        
        double del = (currentTime - previousTime) / 10000.0;
        double timepoint = 1.0 / speed;      
        if (del >= timepoint) {
            System.out.println("automove------------");
            move();
            previousTime = currentTime;
        }
    }
    
    public void initSnake() {
        setDefault();
        for (int z = 0; z < length; z++) {
            x[z] = GameBoardPanel.BLOCK_SIZE * 5 - z * (GameBoardPanel.BLOCK_SIZE / 2);
            y[z] = GameBoardPanel.BLOCK_SIZE * 5;
        }
        previousTime = System.currentTimeMillis();
        speed = 100;
        isRevert = false;
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
        for (int z = 0; z < getLength(); z++) {
                if (z == 0) {
                    g.drawImage(getHead(),getX(z), getY(z), this);
                } else {
                    g.drawImage(getIcon(), getX(z),getY(z), this);
                }
            }
    }
   
    public void keyPressed(KeyEvent e) {
       int key = e.getKeyCode();
       if ((key == KeyEvent.VK_LEFT) && (
                    (!isRightDirection() && !isIsRevert())
                    || (isIsRevert() && !isLeftDirection()))) {
                setLeftDirection(true);
                setUpDirection(false);
                setDownDirection(false);
                setRightDirection(false);
                revertDirection();
            }

            if ((key == KeyEvent.VK_RIGHT) && (
                    (!isLeftDirection() && !isIsRevert())
                    || (isIsRevert() && !isRightDirection()))) {
                setRightDirection(true);
                setUpDirection(false);
                setDownDirection(false);
                setLeftDirection(false);
                revertDirection();
            }

            if ((key == KeyEvent.VK_UP) && (
                    (!isDownDirection() && !isIsRevert())
                    || (isIsRevert() && !isUpDirection()))) {
                setUpDirection(true);
                setRightDirection(false);
                setLeftDirection(false);
                setDownDirection(false);
                revertDirection();
            }

            if ((key == KeyEvent.VK_DOWN) && (
                    (!isUpDirection() && !isIsRevert())
                    || (isIsRevert() && !isDownDirection()))) {
                setDownDirection(true);
                setRightDirection(false);
                setLeftDirection(false);
                setUpDirection(false);
                revertDirection();
            }
   }
   
//    public void checkCollision(){
//        for (int z = getLength(); z > 0; z--) {
//
////            if ((z > 3) && (getX(0) == getX(z)) && (getY(0) ==getY(z))) {
////                inGame = false;
////            }
//        }
//
//        if (getY(0) >= GameBoardPanel.B_HEIGHT) {
//            //inGame = false;
//            setY(0, 0);
//        }
//
//        if (getY(0) < 0) {
//            //inGame = false;
//            setY(0, GameBoardPanel.B_HEIGHT - GameBoardPanel.BLOCK_SIZE);
//        }
//
//        if (getX(0) >= GameBoardPanel.B_WIDTH) {
//            //inGame = false;
//            setX(0, 0);
//        }
//
//        if (getX(0) < 0) {
//            //inGame = false;
//            setX(0, GameBoardPanel.B_WIDTH - GameBoardPanel.BLOCK_SIZE);
//        }
//        
////        if(!inGame) {
////            timer.stop();
////        }
//   }
}
