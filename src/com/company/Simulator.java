package com.company;

import java.util.HashMap;

public class Simulator{

    private EventLine eventLine;
    private double currentTime;
    private HashMap<EventTypes.Type, Double> serviceTimes;
    private boolean interrupted;
    private double avarageServiceTime;

    public Simulator(){
        serviceTimes = new HashMap<>();
        serviceTimes.put(EventTypes.Type.CHUNK, 0.25);
        serviceTimes.put(EventTypes.Type.PING, 0.1);
        serviceTimes.put(EventTypes.Type.SESSION_START, 1.0);
        serviceTimes.put(EventTypes.Type.SESSION_END, 0.5);

        avarageServiceTime = 0.125;
        currentTime = 0;
        eventLine = initializeEventLine();
        interrupted = false;
    }

    //TODO: Add a thread that will listen for finishing the

    public void simulate(){

        while(!interrupted){
            TEvent next = eventLine.get();
            handleEvent(next);
        }

    }

    /**
     * Initializes eventLine with some random values
     * Sets beginning values to the
     */
    private EventLine initializeEventLine(){
        //TODO: Initialize eventLine with some random values
        return null;
    }

    /**
     * Handles incoming event with an appropriate method
     * @param t Next Event taken from an event line
     */
    private void handleEvent(TEvent t){
        currentTime = t.getTime();
        EventTypes.Type type = t.getType();
        switch (type){
            case CHUNK:
                handleChunk(t);
                break;
            case SESSION_START:
                handleSessionStart(t);
                break;
            case SESSION_END:
                handleSessionEnd(t);
                break;
            case PING:
                handlePing(t);
                break;
            default:
                handlePing(t);
                break;
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
