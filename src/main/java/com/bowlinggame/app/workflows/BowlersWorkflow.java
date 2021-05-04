package com.bowlinggame.app.workflows;

import com.bowlinggame.app.models.*;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author spandana.k
 */
@Repository
public class BowlersWorkflow {

    public List<Bowler> bowlers = new ArrayList<Bowler>();
    private HashMap<String, BowlingGameSet> bowlingGameHashMap = new HashMap<>();
    public List<Bowler> getAllBowlers() {
        return bowlers;
    }

    private static final int STRIKESCORE = 10;

    public boolean addBowler(Bowler bowler) {
        if(!checkLaneOccupancy(bowlers,bowler))
        {
            int id = bowlers.stream().max(Comparator.comparingInt(Bowler::getId)).get().getId();
            bowler.setId(++id); // to increment new bowler id
            bowlers.add(bowler);
            return true;
        }
        return false;
    }

    private boolean checkLaneOccupancy(List<Bowler> bowlerList, Bowler bowler) {
        long laneOccupancy = bowlerList.stream()
                .filter(c -> bowler.getAllocatedLane().equals(c.getAllocatedLane()))
                .count();
        return laneOccupancy == 3;
    }

    public boolean updateBowler(Bowler bowler) {

        boolean isBowlerIdPresent =  bowlers.stream().
                anyMatch(bowler1 -> bowler1.getId() == bowler.getId());
        long noOfPlayersInRequestedLane =  bowlers.stream().
                filter(a -> a.getAllocatedLane().equals(bowler.getAllocatedLane()))
                .count();
        //Check if player's id is already present
        //lane can not be updated if 3 players are already assigned
        if (isBowlerIdPresent && noOfPlayersInRequestedLane < 3){
            bowlers.stream().filter(e -> e.getId() == bowler.getId()).forEach(b -> {
                b.setName(bowler.getName());
                b.setAllocatedLane(bowler.getAllocatedLane());
            });
            return true;
        }
        return false;
    }

    public  String UUIDWrapper(){
        return UUID.randomUUID().toString();
    }

    public String startNewGame(StartGameRequest request) {
        //Check to avoid assigning same id in diff games
        String gameId = UUIDWrapper();
        if(bowlingGameHashMap.containsKey(gameId)){
            gameId = UUIDWrapper();
        }
        BowlingGameSet sets = new BowlingGameSet();
        for (Bowler bowler: request.getListOfBowlers()) {
            ScoreBoard scoreBoard = new ScoreBoard();
            scoreBoard.setBowler(bowler);
            sets.setBowlersScoreBoard(bowler.getId(),scoreBoard);
        }
        bowlingGameHashMap.put(gameId,sets);

        return gameId;
    }

    public boolean submitScoreForPlayer(GameSet set, String gameId, int bowlerId) {
        if (!bowlingGameHashMap.containsKey(gameId)) {
            return false;
        }
        BowlingGameSet gameSet = bowlingGameHashMap.get(gameId);
        ScoreBoard scoreBoard = gameSet.getBowlersScoreBoard(bowlerId);

        scoreBoard.setSet(set);
        gameSet.setBowlersScoreBoard(bowlerId,scoreBoard);
        return true;
    }

    public ScoreBoard getBowlerScore(String gameId, int bowlerId){
        if (!bowlingGameHashMap.containsKey(gameId)) {
            return null;
        }
        BowlingGameSet gameSet = bowlingGameHashMap.get(gameId);
        ScoreBoard scoreBoard = gameSet.getBowlersScoreBoard(bowlerId);
        gameSet.setBowlersScoreBoard(bowlerId, getBowlerScores(gameId, bowlerId));
        return scoreBoard;
    }

    private int[] rolls = new int[21];
    public void roll(GameSet sets){
        rolls = sets.getRolls();
    }

    public ScoreBoard getBowlerScores(String gameId, int bowlerId){
        if (!bowlingGameHashMap.containsKey(gameId)) {
            return null;
        }
        BowlingGameSet gameSet = bowlingGameHashMap.get(gameId);
        ScoreBoard scoreBoard = gameSet.getBowlersScoreBoard(bowlerId);

        if(scoreBoard.getSet() !=null) {
            roll(scoreBoard.getSet());
            scoreBoard = GetTotalGameScore(scoreBoard);
            gameSet.setBowlersScoreBoard(bowlerId, scoreBoard);
        }
        return scoreBoard;
    }

    public boolean isStrike(int currRoll){
        return rolls[currRoll] == STRIKESCORE;
    }

    public boolean isSpare(int currRoll){
        return rolls[currRoll] + rolls[currRoll + 1] == STRIKESCORE;
    }

    public ScoreBoard GetTotalGameScore(ScoreBoard scoreBoard){
        int score = 0;
        int previousScore = 0;
        int strikeCount = 0;
        int currentRoll = 0;
        int[] result = new int[10];

        for (int set = 0; set < 10; set++){
            if(isStrike(currentRoll)){
                strikeCount++;
                score =  previousScore + 10 + 10; //10 + 10 bonus
                result[set] = score;
                currentRoll++;
            }
            else if (isSpare(currentRoll)){
                score =  previousScore + 10 + 5;  //10 + 5 bonus
                result[set] = score;
                currentRoll+=2;
            }
            else {
                score=  previousScore + rolls[currentRoll] + rolls[currentRoll +1] ;
                result[set] = score;
                currentRoll +=2;
            }
            previousScore = score;
        }
        scoreBoard.setTotalStrikes(strikeCount);
        scoreBoard.setCurrScore(previousScore);
        scoreBoard.setMissedStrikes(11 - strikeCount);
        scoreBoard.setTotalScore(score);
        scoreBoard.setResult(result);
        return scoreBoard;
    }
}
