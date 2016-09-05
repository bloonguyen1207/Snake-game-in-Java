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
import static GamePlay.TimeAttack.GameBoardPanel.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
        
public class Mouse extends DynamicObject{
    private Image leftImage;
    private Image upImage;
    private Image downImage;
    private double preTimeDirection;
    private boolean isChanged = false;
    
    public Mouse() {
        leftImage = loadImage(leftImage, "res\\Items\\mouse-left.png");
        downImage = loadImage(downImage, "res\\Items\\mouse.png");
        upImage = loadImage(upImage, "res\\Items\\mouse-up.png");
        icon = loadImage(icon, "res\\Items\\mouse-right.png");
        previousTime = System.currentTimeMillis();
        preTimeDirection = System.currentTimeMillis();
        point = 5;
        speed = 70;
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
        int r = (int) (Math.random() * 6);
        upDirection = false;
        leftDirection = false;
        downDirection = false;
        rightDirection = false;
        switch(r) {
            case 0: upDirection = true; break;
            case 1: downDirection = true; break;
            case 2: rightDirection = true; break;
            case 3: leftDirection = true; break;
            case 4: rightDirection = true; break;
            case 5: leftDirection = true; break;
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
            if (!isChanged && 
                    (posY >= border.getPosY() - 1 * BLOCK_SIZE && 
                        posY <= border.getPosY() + 1 * BLOCK_SIZE) &&
                    (posX >= border.getPosX() - 1 * BLOCK_SIZE &&
                        posX <= border.getPosX() + 1 * BLOCK_SIZE)) {
                //("Border");
                //System.out.println(posX + " " + posY);
                if (upDirection) {
                    //System.out.println("changeDown");
                    specifyDirection(1);
                } else if (downDirection) {
                    //System.out.println("changeUp");
                    specifyDirection(0);
                } else if (leftDirection) {
                    //System.out.println("changeRight");
                    specifyDirection(2);
                } else if (rightDirection) {
                    specifyDirection(3);
                    //System.out.println("changeLeft");
                }
                isChanged = true;
                break;
            }
        }
        
    }
    
    public void avoidOut() {
        if (!isChanged) {
            //System.out.println("out" + posX + " " + posY);
            if ((posY >= B_HEIGHT - 1 * BLOCK_SIZE && posY <= B_HEIGHT + 1) || 
                    (posY >= 0 - 1 * BLOCK_SIZE && posY <= 0 + 1 * BLOCK_SIZE) || 
                    (posX >= B_WIDTH - 1 * BLOCK_SIZE && posX <= B_WIDTH + 1 * BLOCK_SIZE) || 
                    (posX >= 0 - 1 * BLOCK_SIZE && posY <= 0 + 1 * BLOCK_SIZE)) {
                changeDirection();
                //System.out.println("change" + posX + " " + posY);
                if (posY >= B_HEIGHT - 1 * BLOCK_SIZE) {
                    specifyDirection(0);
                    //System.out.println("up" + posX + " " + posY);
                } else if (posY <= 0) {
                    specifyDirection(1);
                    //System.out.println("down" + posX + " " + posY);
                } else if (posX <= 0) {
                    specifyDirection(2);
                    //System.out.println("right" + posX + " " + posY);
                } else if (posX >= B_WIDTH - 1 * BLOCK_SIZE) {
                    specifyDirection(3);
                    //System.out.println("down" + posX + " " + posY);
                }
                isChanged = true;
            }
        }
    }
    
    @Override
    protected void move() {
        double curTimeDirection = System.currentTimeMillis();
        double deltaTime = (curTimeDirection - preTimeDirection) / 10000.0;
        int seed = ((int) (Math.random() * 5) + 2) * 10;
        double timepoint = 1.0 / seed;
        avoidOut();
        if (deltaTime >= timepoint) {
            preTimeDirection = curTimeDirection;
            if (!isChanged) {
                changeDirection();    
                isChanged = true;
            }            
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
        double deltaTime = (currentTime - previousTime) / 10000.0;
        double timepoint = 1.0 / speed;
        if (deltaTime >= timepoint) {
            isChanged = false;
            move();
            previousTime = currentTime;            
        }
    }
    
    public void autoMoveHard(ArrayList<Border> borders){
        currentTime = System.currentTimeMillis();
        double deltaTime = (currentTime - previousTime) / 10000.0;
        double timepoint = 1.0 / speed;
        if (deltaTime >= timepoint) {
            isChanged = false;
            avoidBorder(borders);
            move();
            previousTime = currentTime;            
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
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
                if (!((posY >= border.getPosY() - 2 * BLOCK_SIZE && 
                        posY <= border.getPosY() + 2 * BLOCK_SIZE) &&
                    (posX >= border.getPosX() - 2 * BLOCK_SIZE &&
                        posX <= border.getPosX() + 2 * BLOCK_SIZE))) {
                    checkBorder += 1;
                }
            }
                    
            if (checkSnake == snake.getLength() && checkBorder == borders.size()) {
                isCorrect = true;
            }
            
        }       
                
    }
}
