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
import Entity.StaticObject.Border;
import static Entity.StaticObject.StaticObject.RAND_POS_X;
import static Entity.StaticObject.StaticObject.RAND_POS_Y;
import static GamePlay.Classic.Board.LENGTH;
import static GamePlay.Classic.Board.BLOCK_SIZE;
import static GamePlay.Classic.Board.DELAY;
import static GamePlay.TimeAttack.HardBoard.B_HEIGHT;
import static GamePlay.TimeAttack.HardBoard.B_WIDTH;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
        
public class Mouse extends DynamicObject{
    private Image leftImage;
    private Image upImage;
    private Image downImage;
    private double preTimeDirection;
    
    public Mouse() {
        leftImage = loadImage(leftImage, "res\\Items\\mouse-left.png");
        downImage = loadImage(downImage, "res\\Items\\mouse.png");
        upImage = loadImage(upImage, "res\\Items\\mouse-up.png");
        icon = loadImage(icon, "res\\Items\\mouse-right.png");
        previousTime = System.currentTimeMillis();
        preTimeDirection = System.currentTimeMillis();
        point = 5;
        speed = 20;
    }
    
    public double getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(double speed) {
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
    
    private void specifyDirection(int r) {
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
    
    public void avoidBorder(ArrayList<Border> borders) {
        for (Border border: borders) {
            if ((posY >= border.getPosY() - 1 * BLOCK_SIZE && 
                    posY <= border.getPosY() + 1 * BLOCK_SIZE) &&
                    (posX >= border.getPosX() - 1 * BLOCK_SIZE &&
                    posX <= border.getPosX() + 1 * BLOCK_SIZE)) {
                System.out.println("Border");
                changeDirection();
                break;
            }
        }
        
    }
    
    public void avoidOut() {
        if (posY == B_HEIGHT - 1 * BLOCK_SIZE || posY == 0 
                || posX == B_WIDTH - 1 * BLOCK_SIZE || posX == 0) {
            changeDirection();
        }
        if (posY > B_HEIGHT - 1 * BLOCK_SIZE) {
            specifyDirection(0);
        }
        if (posY < 0) {
            specifyDirection(1);
        }
        if (posX < 0) {
            specifyDirection(2);
        }
        if (posX > B_WIDTH - 1 * BLOCK_SIZE) {
            specifyDirection(3);
        }
    }
    
    @Override
    protected void move() {
        double curTimeDirection = System.currentTimeMillis();
        double deltaTime = curTimeDirection - preTimeDirection;
        double timepoint = 1/1000;
        if (deltaTime >= timepoint) {
            preTimeDirection = curTimeDirection;
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
    public void autoMove(){
        currentTime = System.currentTimeMillis();
        double deltaTime = (currentTime - previousTime) / 100;
        double timepoint = 1.0 / speed;
        if (deltaTime >= timepoint) {
            move();
            previousTime = currentTime;
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
    
    public void locateMouse(Snakes snake, ArrayList<Border> borders) {
        boolean isCorrect = false;   
        while (!isCorrect) {
            int checkSnake = 0;
            int checkBorder = 0;
            int r = (int) (Math.random() * RAND_POS_X);
            posX = ((r * BLOCK_SIZE));

            r = (int) (Math.random() * RAND_POS_Y);
            posY = ((r * BLOCK_SIZE));           
            
            for (int z = 0; z < snake.getLength(); z++) {
                if (!(posX == snake.getX(z) && posY == snake.getY(z))) {
                    checkSnake += 1;
                }
            }
            
            for (Border border: borders) {
                if (!(posX == border.getPosX() && posY == border.getPosY())) {
                    checkBorder += 1;
                }
            }
                    
            if (checkSnake == snake.getLength() && checkBorder == borders.size()) {
                isCorrect = true;
            }
            
        }       
                
    }
}
