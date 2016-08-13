/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

public class ItemFactory {
    public StaticObject getItem(String item) {
        if (item == null) {
            return null;
        }
        if (item.equalsIgnoreCase("Apple")) {
            return new Apple();
        } else if (item.equalsIgnoreCase("Coffee")) {
            return new Coffee();
        } else if (item.equalsIgnoreCase("Heal")) {
            return new Heal();
        } else if (item.equalsIgnoreCase("Revert")) {
            return new Revert();
        } else if (item.equalsIgnoreCase("TeaLeaf")) {
            return new TeaLeaf();
        }
        return null;
    }
}
