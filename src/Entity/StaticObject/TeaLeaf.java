/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

/**
 *
 * @author Hanh
 */
public class TeaLeaf extends StaticObject {
    public TeaLeaf() {
        icon = loadImage(icon, "res\\Items\\leaf.png");
    }
    
    @Override
    public void specialEffect() {
        
    }
}
