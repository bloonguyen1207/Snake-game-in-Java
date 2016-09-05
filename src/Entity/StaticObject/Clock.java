/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.StaticObject;

import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author binguyen.com
 */
public class Clock {    
    long start = System.currentTimeMillis();
    long stop;
    int sec;
    public void getStartTime(long start){
        this.start = start;
    }
    public long StartTime(){
        return this.start;
    }
    public void getStopTime(long stop){
        this.stop = stop;
    }
    
    public long StopTime (){
        return this.stop;
    }
    public int getSec(){
        return sec;
    }
    public void getTime(){
        stop = System.currentTimeMillis();
        long duration = stop - start;
        double second = duration/1000.0;
        // System.out.println(second);
        if (second >= 1) {
            sec += 1;
            //System.out.println("Duration: " + sec);
            start = stop;
        }
    }
}
