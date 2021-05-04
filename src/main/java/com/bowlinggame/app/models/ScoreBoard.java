package com.bowlinggame.app.models;

import org.springframework.stereotype.Component;

/**
 * @author spandana.k
 */
@Component
public class ScoreBoard {
    private Bowler bowler;
    private int currScore;// will same as total strikes as the we are sending all scores at once
    private int totalScore;
    private int totalStrikes;
    private int currStrikes;
    private int missedStrikes;
    private int atSet;
    private GameSet set;
    int[] result = new int[10];

    public int[] getResult() {
        return result;
    }

    public void setResult(int[] result) {
        this.result = result;
    }

    public GameSet getSet() {
        return set;
    }

    public void setSet(GameSet set) {
        this.set = set;
    }

    public Bowler getBowler() {
        return bowler;
    }

    public void setBowler(Bowler bowler) {
        this.bowler = bowler;
    }

    public int getCurrScore() {
        return currScore;
    }

    public void setCurrScore(int currScore) {
        this.currScore = currScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalStrikes() {
        return totalStrikes;
    }

    public void setTotalStrikes(int totalStrikes) {
        this.totalStrikes = totalStrikes;
    }

    public int getCurrStrikes() {
        return currStrikes;
    }

    public void setCurrStrikes(int currStrikes) {
        this.currStrikes = currStrikes;
    }

    public int getMissedStrikes() {
        return missedStrikes;
    }

    public void setMissedStrikes(int missedStrikes) {
        this.missedStrikes = missedStrikes;
    }

    public int getAtSet() {
        return atSet;
    }

    public void setAtSet(int atSet) {
        this.atSet = atSet;
    }
}
