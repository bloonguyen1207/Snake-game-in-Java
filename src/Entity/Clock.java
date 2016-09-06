/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


public class Clock extends Entity {    
    private int sec;
    
    public Clock() {
        previousTime = System.currentTimeMillis();
    }
    
    public int getSec(){
        return sec;
    }
    
    public void getTime(){
        currentTime = System.currentTimeMillis();
        long duration = currentTime - previousTime;
        double second = duration/1000.0;
        // System.out.println(second);
        if (second >= 1) {
            sec += 1;
            //System.out.println("Duration: " + sec);
            previousTime = currentTime;
        }
    }
}
