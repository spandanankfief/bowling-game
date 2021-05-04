package com.bowlinggame.app.service;

import com.bowlinggame.app.models.Bowler;
import com.bowlinggame.app.models.GameSet;
import com.bowlinggame.app.models.ScoreBoard;
import com.bowlinggame.app.models.StartGameRequest;
import com.bowlinggame.app.workflows.BowlersWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author spandana.k
 */
@Service
public class BowlerService implements IBowlerControllerService{

    @Autowired
    BowlersWorkflow bowlersWorkflow;

    public List<Bowler> getAllPlayers() {
        return bowlersWorkflow.getAllBowlers();
    }

    public boolean addBowler(Bowler bowler) {
        return bowlersWorkflow.addBowler(bowler);
    }

    public boolean updateBowler(Bowler bowler) {
        return bowlersWorkflow.updateBowler(bowler);
    }

    @Override
    public String startNewGame(StartGameRequest request) {
        return bowlersWorkflow.startNewGame(request);
    }

    @Override
    public boolean submitScoreForPlayer(GameSet set, String gameId, int bowlerId) {
        return  bowlersWorkflow.submitScoreForPlayer(set,gameId,bowlerId);
    }

    @Override
    public ScoreBoard getScoreCardForPlayer(String gameId, int bowlerId) {
        return bowlersWorkflow.getBowlerScore(gameId,bowlerId);
    }


}
