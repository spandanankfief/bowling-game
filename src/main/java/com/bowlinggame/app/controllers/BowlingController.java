package com.bowlinggame.app.controllers;

import com.bowlinggame.app.models.*;
import com.bowlinggame.app.service.IBowlerControllerService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author spandana.k
 */
@RestController
@RequestMapping("/bowling")
public class BowlingController  {

    @Autowired
    private IBowlerControllerService bowlerService;

    public BowlingController(IBowlerControllerService bowlerService){
        this.bowlerService = bowlerService;
    }

    @GetMapping("/getAllBowlers")
    public ResponseEntity<RestResponse>  GetAllBowlersDetails(){
        List<Bowler> bowlers = bowlerService.getAllPlayers();
        if (bowlers.size() == 0){
            return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(true,"There is no game with given id."));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(true,bowlers));
        }
    }

    @PostMapping("/addBowler")
    public ResponseEntity<RestResponse>  CreateBowlerDetails(@RequestBody Bowler bowler){
        if(bowlerService.addBowler(bowler)){
            return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(true,"Bowler details added successfully."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(false,"The lane if fully occupied. Please select different lane"));
    }

    @PutMapping("/updateBowler")
    public ResponseEntity<RestResponse>  UpdateBowlerDetails(@RequestBody Bowler bowler){

        if (bowlerService.updateBowler(bowler)){
            return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(true,"Bowler details updates successfully."));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestResponse(false,"Error in updating bowlers to the game"));
    }

    @PostMapping("/startNewGame")
    public ResponseEntity<RestResponse> StartGame(@RequestBody StartGameRequest request){
        String gameID = bowlerService.startNewGame(request);
        if(!Strings.isEmpty(gameID)){
            return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(true,gameID));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestResponse(false,"Error in starting the game"));
    }

    @PostMapping("/submitScore/{gameId}/{bowlerId}")
    public ResponseEntity<RestResponse> SubmitScore(@RequestBody GameSet gameSet, @PathVariable(value = "gameId") String gameId,@PathVariable(value = "bowlerId") int bowlerId){
        if (bowlerService.submitScoreForPlayer(gameSet,gameId,bowlerId)){
            return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(true,"Score submitted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestResponse(false,"Error in submitting bowlers to the game"));
    }

    @GetMapping("/getScore/{gameId}/{bowlerId}")
    public ResponseEntity<RestResponse> GetScoreForBowler(@PathVariable(value = "gameId") String gameId, @PathVariable(value = "bowlerId") int bowlerId){
        ScoreBoard scoreBoard =  bowlerService.getScoreCardForPlayer(gameId,bowlerId);
        if (scoreBoard!=null){
            return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(true,scoreBoard));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestResponse(false,"Error in fetching the scores"));
    }
}
