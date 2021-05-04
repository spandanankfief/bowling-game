package com.bowlinggame.app.models;

import java.util.List;

/**
 * @author spandana.k
 */
public class StartGameRequest {
    public List<Bowler> bowlers;

    public StartGameRequest(){}
    public StartGameRequest(List<Bowler> bowlers) {
        this.bowlers = bowlers;
    }

    public List<Bowler> getListOfBowlers() {
        return bowlers;
    }

    public void setListOfBowlers(List<Bowler> listOfBowlers) {
        this.bowlers = listOfBowlers;
    }
}
