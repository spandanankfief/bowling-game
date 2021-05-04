package com.bowlinggame.app.service;

import com.bowlinggame.app.models.Bowler;
import com.bowlinggame.app.models.GameSet;
import com.bowlinggame.app.models.ScoreBoard;
import com.bowlinggame.app.models.StartGameRequest;

import java.util.List;

/**
 * @author spandana.k
 */
public interface IBowlerControllerService {

    public boolean addBowler(Bowler bowler);

    public boolean updateBowler(Bowler bowler);

    public List<Bowler> getAllPlayers();

    public String startNewGame(StartGameRequest request);

    public boolean submitScoreForPlayer(GameSet set, String gameId, int bowlerId);

    public ScoreBoard getScoreCardForPlayer(String id, int bowlerId);

}
