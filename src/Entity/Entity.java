/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hanh
 */
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import static GamePlay.Classic.Board.BLOCK_SIZE;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public abstract class Entity extends JPanel{
    public String name;
    public Image icon;
    public int delay;
    public int point;
    public long currentTime;
    public long previousTime;
    public int posX;
    public int posY;

    public int getPoint() {
        return point;
    }
    
    public void setPoint(int point) {
        this.point = point;
    }
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public Image getIcon() {
        return icon;
    }
    
    public Image loadImage(Image icon, String path){
        ImageIcon iconImage = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(BLOCK_SIZE, BLOCK_SIZE, Image.SCALE_DEFAULT));
        icon = iconImage.getImage();
        return icon;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(icon, posX, posY, this);
    }
}
