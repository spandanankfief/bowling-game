package com.bowlinggame.app.models;

import java.util.HashMap;

/**
 * @author spandana.k
 */
public class BowlingGameSet {
    private HashMap<Integer, ScoreBoard> bowlersScoreBoard = new HashMap<Integer, ScoreBoard>();
    public BowlingGameSet() {}

    public ScoreBoard getBowlersScoreBoard(int id) {
        return bowlersScoreBoard.get(id);
    }

    public void setBowlersScoreBoard(int id, ScoreBoard scoreBoard) {
        bowlersScoreBoard.put(id,scoreBoard);
        this.bowlersScoreBoard = bowlersScoreBoard;
    }
}
