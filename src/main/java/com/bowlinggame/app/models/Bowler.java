package com.bowlinggame.app.models;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author spandana.k
 */
@Component
public class Bowler {
    private static final AtomicInteger count = new AtomicInteger(0);

    private String name;
    private int id;
    private String allocatedLane;

    public Bowler(){}

    public Bowler(String name,String allocatedLane ) {
        this.name = name;
        this.id = count.incrementAndGet();
        this.allocatedLane = allocatedLane;
    }

    public static AtomicInteger getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAllocatedLane() {
        return allocatedLane;
    }

    public void setAllocatedLane(String allocatedLane) {
        this.allocatedLane = allocatedLane;
    }

}
