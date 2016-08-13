/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

/**
 *
 * @author Hanh
 */
public class Score {
    private Strategy strategy;
    private int score = 0;

    public int getScore() {
        return score;
    }
    
    public Score(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public int executeStrategy(int num2) {
        score = strategy.doOperation(score, num2);
        return score;
    }
}
