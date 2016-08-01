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
import static GamePlay.Classic.Board.BLOCK_SIZE;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class Mouse extends DynamicObject{
    private Image leftImage;
    private Image upImage;
    private Image downImage;
    
    public Mouse() {
        leftImage = loadImage(leftImage, "res\\Items\\mouse-left.png");
        downImage = loadImage(downImage, "res\\Items\\mouse.png");
        upImage = loadImage(upImage, "res\\Items\\mouse-up.png");
        icon = loadImage(icon, "res\\Items\\mouse-right.png");
        previousTime = System.nanoTime();
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setDefault() {
        setLeftDirection(false);
        setUpDirection(false);
        setDownDirection(false);
        setRightDirection(true);
    }
    
    public void changeDirection() {
        int r = (int) (Math.random() * 4);
        upDirection = false;
        leftDirection = false;
        downDirection = false;
        rightDirection = false;
        switch(r) {
            case 0: upDirection = true; break;
            case 1: downDirection = true; break;
            case 2: rightDirection = true; break;
            case 3: leftDirection = true; break;
        }
    }
    
    public void avoidSnake(Snakes snake) {
        if (posX <= snake.getPosX() - BLOCK_SIZE * 5 && 
                posY <= snake.getPosY() - BLOCK_SIZE * 5 &&
                (downDirection || rightDirection)) {
            changeDirection();
        } else if (posX >= snake.getPosX() - BLOCK_SIZE * 5 && 
                posY <= snake.getPosY() - BLOCK_SIZE * 5 &&
                (downDirection || leftDirection)) {
            changeDirection();
        } else if (posX >= snake.getPosX() - BLOCK_SIZE * 5 && 
                posY >= snake.getPosY() - BLOCK_SIZE * 5 &&
                (upDirection || leftDirection)) {
            changeDirection();
        } else if (posX <= snake.getPosX() - BLOCK_SIZE * 5 && 
                posY >= snake.getPosY() - BLOCK_SIZE * 5 &&
                (upDirection || rightDirection)) {
            changeDirection();
        }
    }
    
    @Override
    public void move() {
        currentTime = System.nanoTime();
        long deltaTime = (currentTime - previousTime) / 1000000000;
        if (deltaTime > 1) {
            previousTime = currentTime;
            changeDirection();
        }
        
        if (leftDirection) {
            posX -= BLOCK_SIZE;
        }

        if (rightDirection) {
            posX += BLOCK_SIZE;
        }

        if (upDirection) {
            posY -= BLOCK_SIZE;
        }

        if (downDirection) {
            posY += BLOCK_SIZE;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (leftDirection) {
            g.drawImage(leftImage, posX, posY, this);
        } else if (downDirection) {
            g.drawImage(downImage, posX, posY, this);
        } else if (upDirection) {
            g.drawImage(upImage, posX, posY, this);
        } else {
            g.drawImage(icon, posX, posY, this);
        }
        
    }
    
}
