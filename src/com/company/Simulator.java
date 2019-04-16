package com.company;

import java.util.Collections;
import java.util.LinkedList;

public class Simulator{

    private EventLine eventLine;
    private double currentTime;
    private double averageServiceTime;
    private System system;
    private double simulationTime = 1000;
    private int numberOfEvents = 20;
    private Report report;

    public Simulator(){
        averageServiceTime = 0.125;
        currentTime = 0;
        system = new System();
        eventLine = initializeEventLine(0);
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
     * @param lambda arrivals intensity
     */
    private EventLine initializeEventLine(double lambda){
        //TODO: Initialize eventLine with some Poisson distributed values
        //TODO: Remove HARD CODE

        LinkedList<TEvent> incomingEvents = new LinkedList<>();
        LinkedList<TEvent> pastEvents = new LinkedList<>();

        for(int i = 0; i <numberOfEvents; i++){
            incomingEvents.add(new TEvent(RandomGenerator.getDouble(0,simulationTime), EventTypes.Type.EVENT_ARRIVAL));
        }

        //Start turning the system off and on
        if(simulationTime > system.getOnAverageTime())
            incomingEvents.add(new TEvent(RandomGenerator.getExp(system.getOnAverageTime()), EventTypes.Type.SYSTEM_OFF));

        Collections.sort(incomingEvents, new TEventComparator());

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

        if(system.getNumberOfClientsInSystem() == 0 ||
                //0 or 1 clients in the system mean that it is not busy
                system.getNumberOfClientsInSystem() == 1){

            t.setClientId(system.addClient());
            eventLine.put(new TEvent(currentTime + RandomGenerator.getExp(averageServiceTime),
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
            eventLine.put(new TEvent(currentTime + RandomGenerator.getExp(averageServiceTime), EventTypes.Type.EVENT_DEPARTURE,
                    //+1 because next client waiting for service has id 1 higher
                    (t.getClientId()+1) ));
        }
    }

    private void handleSystemOn(TEvent t){
        Log.info("System ON");
        eventLine.put(new TEvent(RandomGenerator.getExp(system.getOffAverageTime()), EventTypes.Type.SYSTEM_OFF));
        eventLine.sortEvents();
    }

    private void handleSystemOff(TEvent t){
        //Add SYSTEM_ON Event after SYSTEM_OFF time
        //Find all the next incoming departure event that will happen in the system-off window
        //move them after the next SYSTEM_ON (add to their time system-off window duration)

        Log.info("Handling system OFF");
        //Determine System-off window
        double windowStart = t.getTime();
        double windowDuration = RandomGenerator.getExp(system.getOnAverageTime());
        double windowEnd = t.getTime() + windowDuration;

        //Add system ON event
        eventLine.put(new TEvent(windowEnd, EventTypes.Type.SYSTEM_ON));

        //Move departures from system-off window
        for (TEvent e: eventLine.getIncomingEvents()) {
            if(e.getType().equals(EventTypes.Type.EVENT_DEPARTURE) && e.getTime() < windowEnd && e.getTime() >= windowStart){
                e.setTime(e.getTime() + windowDuration);
            }
        }

        eventLine.sortEvents();
    }

    private void handleUnknownEvent(TEvent T){
        //TODO
    }
}
