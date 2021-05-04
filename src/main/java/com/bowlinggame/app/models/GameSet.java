package com.bowlinggame.app.models;

/**
 * @author spandana.k
 */
public class GameSet {

    private int[] rolls;

    public GameSet() {
        rolls = new int[21];// Maximum of 21 games will be there
    }

    public int[] getRolls() {
        return rolls;
    }

    public void setRolls(int[] rolls) {
        this.rolls = rolls;
    }
}
