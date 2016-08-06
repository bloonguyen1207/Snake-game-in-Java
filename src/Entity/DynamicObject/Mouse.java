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
import static Entity.StaticObject.StaticObject.RAND_POS_X;
import static Entity.StaticObject.StaticObject.RAND_POS_Y;
import static GamePlay.Classic.Board.LENGTH;
import static GamePlay.Classic.Board.BLOCK_SIZE;
import static GamePlay.TimeAttack.Board3.B_HEIGHT;
import static GamePlay.TimeAttack.Board3.B_WIDTH;
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
        point = 5;
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
    
    private void changeDirection() {
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
        //System.out.println("changeDirection");
    }
    
    public void avoidSnake(Snakes snake) {
        if (posX <= snake.getPosX() - BLOCK_SIZE * 3 && 
                posY <= snake.getPosY() - BLOCK_SIZE * 3 &&
                (downDirection || rightDirection)) {
            changeDirection();
        } else if (posX >= snake.getPosX() - BLOCK_SIZE * 3 && 
                posY <= snake.getPosY() - BLOCK_SIZE * 3 &&
                (downDirection || leftDirection)) {
            changeDirection();
        } else if (posX >= snake.getPosX() - BLOCK_SIZE * 3 && 
                posY >= snake.getPosY() - BLOCK_SIZE * 3 &&
                (upDirection || leftDirection)) {
            changeDirection();
        } else if (posX <= snake.getPosX() - BLOCK_SIZE * 3 && 
                posY >= snake.getPosY() - BLOCK_SIZE * 3 &&
                (upDirection || rightDirection)) {
            changeDirection();
        }
    }
    
    public void avoidBoarder() {
        if (posY >= B_HEIGHT - 1 * BLOCK_SIZE && downDirection) {
            changeDirection();
        }

        if (posY < 1 * BLOCK_SIZE && upDirection) {
            changeDirection();
        }

        if (posX >= B_WIDTH - 1 * BLOCK_SIZE && rightDirection) {
            changeDirection();
        }

        if (posX - 1 * BLOCK_SIZE < 0 && upDirection) {
            changeDirection();
        }
    }
    
    @Override
    public void move() {
        currentTime = System.nanoTime();
        double deltaTime = (currentTime - previousTime) / 1000000000;
        double timepoint = 1/30;
        if (deltaTime > timepoint) {
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
//        System.out.print("left: ");
//        System.out.println(leftDirection);
//        System.out.print("down: ");
//        System.out.println(downDirection);
//        System.out.print("right: ");
//        System.out.println(rightDirection);
//        System.out.print("up: ");
//        System.out.println(upDirection);
//        System.out.println("-------------");
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
    
    public void locateMouse(Snakes snake) {
        boolean isCorrect = false;   
        while (!isCorrect) {
            int checkSnake = 0;
            int r = (int) (Math.random() * RAND_POS_X);
            posX = ((r * BLOCK_SIZE));

            r = (int) (Math.random() * RAND_POS_Y);
            posY = ((r * BLOCK_SIZE));           
            
            for (int z = 0; z < snake.getLength(); z++) {
                if (!(posX == snake.getX(z) && posY == snake.getY(z))) {
                    checkSnake += 1;
                }
            }
                    
            if (checkSnake == snake.getLength()) {
                isCorrect = true;
            }
            
        }       
                
    }
}