package com.bowlinggame.test;

import com.bowlinggame.app.models.BowlingGameSet;
import com.bowlinggame.app.models.GameSet;
import com.bowlinggame.app.models.ScoreBoard;
import com.bowlinggame.app.workflows.BowlersWorkflow;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author spandana.k
 */

public class GameTest {
    private  BowlersWorkflow workflow;
    private final HashMap<String, BowlingGameSet> bowlingGameHashMap = new HashMap<>();

    @Before
    public void setUp(){
        workflow = new BowlersWorkflow();
    }

    @Test
    public void testPerfectScoreGame(){
        GameSet gameSet = new GameSet();
        gameSet.setRolls(new int[]{10,10,10,10,10,10,10,10,10,10,10,10});

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setSet(gameSet);
        workflow.roll(gameSet);
        scoreBoard = workflow.GetTotalGameScore(scoreBoard);
        assertEquals(scoreBoard.getTotalScore(),200);
    }

    @Test
    public void testSpareFollowedByThreeAndTwoGame(){
        GameSet gameSet = new GameSet();
        gameSet.setRolls(new int[]{5,5, 3,2, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0 });
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setSet(gameSet);
        workflow.roll(gameSet);
        scoreBoard = workflow.GetTotalGameScore(scoreBoard);
        assertEquals(scoreBoard.getTotalScore(),20);
    }

    @Test
    public void testAllZeroGame(){
        GameSet gameSet = new GameSet();
        gameSet.setRolls(new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0});
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setSet(gameSet);
        workflow.roll(gameSet);
        scoreBoard = workflow.GetTotalGameScore(scoreBoard);
        assertEquals(scoreBoard.getTotalScore(),0);
    }

    @Test
    public void testStrikeAndThreeAndOneGame(){
        GameSet gameSet = new GameSet();
        gameSet.setRolls(new int[]{10, 3,1, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0});
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setSet(gameSet);
        workflow.roll(gameSet);
        scoreBoard = workflow.GetTotalGameScore(scoreBoard);
        scoreBoard = workflow.GetTotalGameScore(scoreBoard);
        assertEquals(scoreBoard.getTotalScore(),24);
    }
}
