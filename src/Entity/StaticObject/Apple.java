/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import Entity.DynamicObject.Snakes;


public class Apple extends StaticObject {
    public Apple() {
        point = 1;
        icon = loadImage(icon, "res\\Items\\apple.png");
    }   
    
    @Override
    public int specialEffect(Snakes snake) {
        return 0;
    }
}
