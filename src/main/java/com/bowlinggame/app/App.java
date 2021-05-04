package com.bowlinggame.app;

import com.bowlinggame.app.models.Bowler;
import com.bowlinggame.app.workflows.BowlersWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * @author spandana.k
 */
@SpringBootApplication
@ComponentScan("com.bowlinggame")
public class App implements CommandLineRunner {
    @Autowired
    BowlersWorkflow bowlerRepo;

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Service started successfully..");

        Bowler bowler1 = new Bowler("Player1","A");
        Bowler bowler2 = new Bowler("Player2","A");
        bowlerRepo.bowlers.addAll(Arrays.asList(bowler1,bowler2));
    }
}
