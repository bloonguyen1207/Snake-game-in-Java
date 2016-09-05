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
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.DynamicObject.Snakes;
import GamePlay.Classic.ClassicLevel;
import Menu.GameStateManager;
import Score.OperationAdd;
import Score.Score;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;



public class GameBoardPanel extends JPanel implements ActionListener {

    public static final int B_WIDTH = 1000;
    public static final int B_HEIGHT = 600;
    public static final int DOT_SIZE = 20;
    public static final int ALL_DOTS = B_WIDTH * B_HEIGHT / DOT_SIZE / DOT_SIZE;
    public static final int BLOCK_SIZE = 20;
//    private final int RAND_POS_X = 49;
//    private final int RAND_POS_Y = 29;
    Score time_score = new Score(new OperationAdd());
    public JFrame Game;
    

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
    private static AudioInputStream bgMusic;

    private Timer timer;
    // private long start = System.currentTimeMillis();
    //Clock clock; 
    private GameStateManager gsm;
    
    
//fixed
    Snakes snake = Snakes.getInstance();
    
    public GameBoardPanel(JFrame Game){
        this.Game = Game;
        addKeyListener(new TAdapter());
        setBackground(new Color(7, 123, 83));
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        initGame();
        try {
            playBackgroundMusic();
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(GameBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public JFrame getContainer() {
        return this.Game;
    }

    private void initGame() {

        snake.initSnake();
        initMultiFood();
        timer = new Timer(20, this);
        timer.start();
        gsm = new GameStateManager();
        
        //setAllBorders();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        gsm.paintComponent(g);
    }
    
    public void doDrawing(Graphics g) {
        gsm.doDrawing(g);
    }

    public void gameOver(Graphics g) {

    }
    
    public void ReplayButtonActionPerformed(ActionEvent evt) {                                                      
    }
    
    public void MenuButtonActionPerformed(ActionEvent evt) {                                            
    }

    public void checkFood() {                
    }
    
    public void checkCollision() {
      
    }
                     
    public void actionPerformed(ActionEvent e) {
        gsm.actionPerformed(e);
        repaint();
    }
    
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            gsm.keyPressed(e);
        }
    }

    public void initMultiFood() {
       
    }
    
    public void locateMice() {
      
    }
    
    public void locateMultiFood() {
    }
    
    public static void playBackgroundMusic() throws UnsupportedAudioFileException, IOException {
        bgMusic = AudioSystem.getAudioInputStream(new File("res/sound/background.wav").getAbsoluteFile());
        Clip clip;
        try {
            clip = AudioSystem.getClip();
            clip.open(bgMusic);
            clip.start();
            clip.loop(100);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ClassicLevel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

