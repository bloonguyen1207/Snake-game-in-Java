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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.DynamicObject.Snakes;
import Entity.StaticObject.ClassicFood;
import GamePlay.ClassicGame;
import Menu.Menu;
import Player.Player;
import Score.OperationAdd;
import Score.Score;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Board extends JPanel implements ActionListener {

    private static final int B_WIDTH = 1000;
    private static final int B_HEIGHT = 600;
    private static File highscores;
    private static Scanner readFiles;
    private static FileWriter output;
    private static BufferedWriter writeFiles;
    public static final int BLOCK_SIZE = 20;
    public static final int LENGTH = B_WIDTH * B_HEIGHT / BLOCK_SIZE / BLOCK_SIZE;
//    private final int RAND_POS_X = 49;
//    private final int RAND_POS_Y = 29;
    public int DELAY = 100;
    Score classic_score = new Score(new OperationAdd());
    public JFrame Game;

//    private final int x[] = new int[LENGTH];
//    private final int y[] = new int[LENGTH];

//    private int dots;
//    private int food_x;
//    private int food_y;

//    private boolean leftDirection = false;
//    private boolean rightDirection = true;
//    private boolean upDirection = false;
//    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;


//fixed
    Snakes snake = Snakes.getInstance();
    static ClassicFood food = new ClassicFood();
    
    public Board(JFrame Game) {
        this.Game = Game;
        addKeyListener(new TAdapter());
        setBackground(new Color(7, 123, 83));
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //loadImages();
        initGame();
    }
    
    public JFrame getContainer() {
        return this.Game;
    }

    private void initGame() {
        
        snake.initSnake();
        snake.speed = 100;
        DELAY = snake.speed;
        food.locateFood(snake);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            doDrawing(g);
        } catch (Exception ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void doDrawing(Graphics g) throws Exception {
        
        if (inGame) {
            g.drawImage(food.getIcon(), food.getPosX(), food.getPosY(), this);

            for (int z = 0; z < snake.getLength(); z++) {
                if (z == 0) {
                    g.drawImage(snake.getHead(), snake.getX(z), snake.getY(z), this);
                } else {
                    g.drawImage(snake.getIcon(), snake.getX(z), snake.getY(z), this);
                }
            }
            
            String score = "Score: " + Integer.toString(classic_score.getScore());
            Font small = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
            FontMetrics metr = getFontMetrics(small);
            g.setColor(Color.black);
            g.setFont(small);
            g.drawString(score, 10, 30);
            
            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) throws Exception {
        
        String msg = "Game Over";
        Font text = new Font("Berlin Sans FB Demi", Font.BOLD, 30);
        Font buttons = new Font("Berlin Sans FB Demi", 1, 24);
        FontMetrics metr = getFontMetrics(text);

        g.setColor(Color.orange);
        g.setFont(text);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 150);
        
        String score = "Score: " + Integer.toString(classic_score.getScore());

        g.setColor(Color.black);
        g.setFont(text);
        g.drawString(score, (B_WIDTH - metr.stringWidth(msg)) / 2 + 20, B_HEIGHT / 2 - 90);
        
        if (newHighScore() > -1) {
            JTextField name = new JTextField(10);
            name.setBackground(new Color(255, 204, 0));
            name.setFont(text);
            name.setText("AAA");
            name.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 - 70, 150, 50);
            add(name);

            //TODO: Delete when done
            String newHighScore = "NEW HIGHSCORE!!!";
    
            g.setColor(Color.yellow);
            g.setFont(text);
            g.drawString(newHighScore, (B_WIDTH - metr.stringWidth(msg)) / 2 - 50, B_HEIGHT / 2 - 200);
            // END

            // Submit
            JButton SubmitButton = new JButton();

            SubmitButton.setBackground(new Color(255, 204, 0));
            SubmitButton.setFont(buttons);
            SubmitButton.setForeground(new Color(204, 51, 0));
            SubmitButton.setIcon(new ImageIcon(new ImageIcon("res\\Menu\\submit.png").getImage().getScaledInstance(BLOCK_SIZE, BLOCK_SIZE, Image.SCALE_DEFAULT)));
            SubmitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt){
                    try {
                        StringBuilder data = new StringBuilder();
                        int index = newHighScore();
                        ArrayList<String> infos = readFile();
                        for (int i = 9; i > index; i--) {
                            infos.set(i, infos.get(i - 1));
                        }
                        infos.set(index, name.getText() + " " + Integer.toString(classic_score.getScore()));
                        try {
                            output = new FileWriter(highscores, false);
                            writeFiles = new BufferedWriter(output);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        for (int i = 0; i < 10; i++) {
                            data.append(infos.get(i));

                            writeFiles.write(data.toString());
                            writeFiles.newLine();
                            writeFiles.flush();
                            data.delete(0, data.length());
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    SubmitButton.setIcon(new ImageIcon(new ImageIcon("res\\Menu\\check.png").getImage().getScaledInstance(BLOCK_SIZE, BLOCK_SIZE, Image.SCALE_DEFAULT)));
                    SubmitButton.setEnabled(false);
                }   
            });
            add(SubmitButton);
            SubmitButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 + 130, B_HEIGHT / 2 - 70, 50, 50);
        }
        // Replay
        JButton ReplayButton = new JButton();
        
        ReplayButton.setBackground(new Color(255, 204, 0));
        ReplayButton.setFont(buttons);
        ReplayButton.setForeground(new Color(204, 51, 0));
        ReplayButton.setText("Replay");
        ReplayButton.addActionListener(this::ReplayButtonActionPerformed);
        add(ReplayButton);
        ReplayButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 + 20, 200, 59);
        
        // Back to menu - In Progess
        JButton MenuButton = new JButton();
        
        MenuButton.setBackground(new Color(255, 204, 0));
        MenuButton.setFont(buttons);
        MenuButton.setForeground(new Color(204, 51, 0));
        MenuButton.setText("Back to menu");
        MenuButton.addActionListener(this::MenuButtonActionPerformed);
        add(MenuButton);
        MenuButton.setBounds((B_WIDTH - metr.stringWidth(msg)) / 2 - 20, B_HEIGHT / 2 + 100, 200, 59);
    }
    
    private void ReplayButtonActionPerformed(ActionEvent evt) {                                            
        // TODO add your handling code here:
        ClassicGame newGame = new ClassicGame();
        newGame.setVisible(true);
        this.getContainer().setVisible(false);
                      
    }
    
    private void MenuButtonActionPerformed(ActionEvent evt) {                                            
        // TODO add your handling code here:
        Menu mainMenu = new Menu();
        mainMenu.setVisible(true);
        this.getContainer().setVisible(false);
    }

    private void checkFood() {
        if ((snake.getX(0) == food.posX) && (snake.getY(0) == food.posY)) {
            snake.setLength(snake.getLength() + 1);
            classic_score.executeStrategy(food.point);
            timer.setDelay(timer.getDelay() - 1);
            food.locateFood(snake);
        }
    }
    
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
    
    // Don't touch this
    public int newHighScore() throws Exception {
        String info;
        int counter = -1;
//        ArrayList<Player> players = new ArrayList<Player>(10);
        if (!inGame) {
            try {
                highscores = new File(System.getProperty("user.dir") + ("/classic.txt"));
                readFiles = new Scanner(highscores);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            while (readFiles.hasNext()) {
                counter++;
                info = readFiles.nextLine();
                String[] piece = info.split(" ");
                if (classic_score.getScore() >= Integer.parseInt(piece[1])) {
                    return counter;
                }
            }
        }
        return counter;
    }
    
    public ArrayList readFile() throws Exception {
        ArrayList<String> infos = new ArrayList<>(10);
        if (!inGame) {
            try {
                highscores = new File(System.getProperty("user.dir") + ("/classic.txt"));
                readFiles = new Scanner(highscores);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            while (readFiles.hasNext()) {
                infos.add(readFiles.nextLine());
            }
        }
        return infos;
    }
}
