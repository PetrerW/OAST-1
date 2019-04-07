package com.company;

import java.util.HashMap;

public class Simulator{

    private EventLine eventLine;
    private double currentTime;
    private HashMap<String, Double> serviceTimes;
    private boolean interrupted;

    public Simulator(){
        serviceTimes = new HashMap<>();
        serviceTimes.put(EventTypes.chunk, 0.25);
        serviceTimes.put(EventTypes.ping, 0.1);
        serviceTimes.put(EventTypes.sessionStart, 1.0);
        serviceTimes.put(EventTypes.sessionEnd, 0.5);

        currentTime = 0;
        eventLine = new EventLine();
        interrupted = false;
    }

    //TODO: Add a thread that will listen for finishing the

    public void simulate(){
        initializeEventLine();
        interrupted = false;

        while(!interrupted){
            TEvent Next = eventLine.get();
            currentTime = Next.getTime();
            handleEvent(Next);
        }

    }

    /**
     * Initializes eventLine with some random values
     * Sets beginning values to the
     */
    private void initializeEventLine(){
        //TODO: Initialize eventLine with some random values
    }

    /**
     * Handles incoming event with an appropriate method
     * @param T Next Event taken from an event line
     */
    private void handleEvent(TEvent T){
        String type = T.getType();
        if(type.equals(EventTypes.chunk)){
            //TODO: Handle Chunk event
        }
        else if(type.equals(EventTypes.sessionStart)) {
            //TODO: Handle session start
        }
        else if(type.equals(EventTypes.sessionEnd)) {
            //TODO: Handle here session end
        }
        else if(type.equals(EventTypes.ping)){
            //TODO: Handle here ping
        }
        else{
            //TODO: Handle unknkown event type
        }
    }

    private void handleChunk(TEvent T){
        //TODO
    }

    private void handleSessionStart(TEvent T){
        //TODO: Generate a random number of Chunk events (Poisson)
        //TODO: Generate one sessionEnd event
    }

    private void handleSessionEnd(TEvent T){
        //TODO: Generate 1 or 2 SessionStart events (Poisson)
    }

    private void handlePing(TEvent T){
        //TODO
    }

    private void handleUnknownEvent(TEvent T){
        //TODO
    }
}
