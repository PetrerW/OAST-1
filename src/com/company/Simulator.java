package com.company;

import java.util.LinkedList;

public class Simulator{

    private EventLine eventLine;
    private double currentTime;
    private double averageServiceTime;
    private System system;
    private double simulationTime = 5;
    private int numberOfEvents = 20;
    private Report report;

    public Simulator(){
        averageServiceTime = 0.125;
        currentTime = 0;
        eventLine = initializeEventLine();
        system = new System();
        Log.info("Simulation created");
    }

    public void simulate(){

        Log.info("Simulation started");

        while(!eventLine.getIncomingEvents().isEmpty()){
            handleEvent(eventLine.get());
        }

        Log.info("Simulation ended");

        Log.info("Generating report");
        report = new Report();
        report.generateReport(eventLine.getPastEvents());
        Log.info("Report generated");

    }

    /**
     * Initializes eventLine with some random values
     * Sets beginning values to the
     */
    private EventLine initializeEventLine(){
        //TODO: Initialize eventLine with some random values
        //TODO: Remove HARD CODE

        LinkedList<TEvent> incomingEvents = new LinkedList<>();
        LinkedList<TEvent> pastEvents = new LinkedList<>();

        for(int i = 0; i <numberOfEvents; i++){
            incomingEvents.add(new TEvent(RandomGenerator.getDouble(0,simulationTime), EventTypes.Type.EVENT_ARRIVAL));
        }

        Log.info("EventLine initialized\n\tNumber of incoming events: " + incomingEvents.size()
                + "\n\tNumber of past events: " + pastEvents.size());

        return new EventLine(incomingEvents,pastEvents);
    }

    /**
     * Handles incoming event with an appropriate method
     * @param t Next Event taken from an event line
     */
    private void handleEvent(TEvent t){
        currentTime = t.getTime();
        EventTypes.Type type = t.getType();
        switch (type){
            case EVENT_ARRIVAL:
                handleArrival(t);
                break;
            case EVENT_DEPARTURE:
                handleDeparture(t);
                break;
            default:
                handleUnknownEvent(t);
                break;
        }

    }

    private void handleArrival(TEvent t){
        Log.info("Handling arrival\n\tCurrent time: " + currentTime);

        if(system.getNumberOfClientsInSystem() == 0){
            t.setClientId(system.addClient());
            eventLine.put(new TEvent(currentTime+ averageServiceTime,
                    EventTypes.Type.EVENT_DEPARTURE,t.getClientId()));
        }
        else{
            t.setClientId(system.addClient());
        }
    }

    private void handleDeparture(TEvent t){
        Log.info("Handling departure\n\tCurrent time: " + currentTime +
                "\n\tclientId: " + t.getClientId());

        system.removeClient();

        if(system.getNumberOfClientsInSystem() > 0){
            eventLine.put(new TEvent(currentTime+ averageServiceTime, EventTypes.Type.EVENT_DEPARTURE,
                    //+1 because next client waiting for service has id 1 higher
                    (t.getClientId()+1) ));
        }
    }

    private void handleUnknownEvent(TEvent T){
        //TODO
    }

}
