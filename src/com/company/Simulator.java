package com.company;

import java.util.*;

public class Simulator{

    private EventLine eventLine;
    private double currentTime;
    private double averageServiceTime;
    private System system;
    private float simulationTime;
    private double avgNumberOfEvents;
    private boolean uniformDistribution;
    private double minServiceTime;
    private double maxServiceTime;

    public Simulator(int simulationTime){
        this.simulationTime = simulationTime;
        averageServiceTime = 0.125;
        minServiceTime = 0.1;
        maxServiceTime = 0.15;
        currentTime = 0;
        system = new System();
        eventLine = initializeEventLine(0, false);
        uniformDistribution = false;
        Log.info("Simulation created");
    }

    public Simulator(float lambda, int simulationTime){
        this.simulationTime = simulationTime;
        averageServiceTime = 0.125;
        minServiceTime = 0.1;
        maxServiceTime = 0.15;
        currentTime = 0;
        system = new System();
        avgNumberOfEvents = lambda * simulationTime;
        eventLine = initializeEventLine(lambda, false);
        uniformDistribution = false;

        Log.info("Simulation created");
    }

    public Simulator(float lambda, boolean onoff, int simulationTime){
        this.simulationTime = simulationTime;
        averageServiceTime = 0.125;
        minServiceTime = 0.1;
        maxServiceTime = 0.15;
        currentTime = 0;
        system = new System();
        avgNumberOfEvents = lambda * simulationTime;
        eventLine = initializeEventLine(lambda, onoff);
        uniformDistribution = false;

        Log.info("Simulation created");
    }

    public Simulator(float lambda, boolean onoff, int simulationTime, boolean uniformDistribution){
        this.simulationTime = simulationTime;
        averageServiceTime = 0.125;
        minServiceTime = 0.1;
        maxServiceTime = 0.15;
        currentTime = 0;
        system = new System();
        avgNumberOfEvents = lambda * simulationTime;
        eventLine = initializeEventLine(lambda, onoff);
        this.uniformDistribution = uniformDistribution;

        Log.info("Simulation created");
    }

    public LinkedList<TEvent> simulate(){

        Log.info("Simulation started");

        while(!eventLine.getIncomingEvents().isEmpty()){
            handleEvent(eventLine.get());
        }

        Log.info("Simulation ended");

        return eventLine.getPastEvents();
    }

    /**
     * Initializes eventLine with some random values
     * Sets beginning values to the
     * @param lambda arrivals intensity
     * @param onoff True if the system should be switched off and on again
     */
    public EventLine initializeEventLine(double lambda, boolean onoff){
        //TODO: Remove HARD CODE
        if(lambda > 0){
            LinkedList<TEvent> incomingEvents = new LinkedList<>();
            LinkedList<TEvent> pastEvents = new LinkedList<>();

            // Number of events is a Poisson distribution
            int numberOfEvents  = RandomGenerator.getPoisson(avgNumberOfEvents);

            double tempArrivalTime = 0;
            for(int i = 0; i <numberOfEvents; i++){
                //double arrivalTime = RandomGenerator.getPoissonRandom(lambda*simulationTime);
                double arrivalTime = tempArrivalTime + RandomGenerator.getExponential(lambda);
                tempArrivalTime = arrivalTime;
                if(incomingEvents.stream().anyMatch(event -> event.getTime() == arrivalTime)){
                    //We need one more iteration
                    i--;
                }
                else
                    incomingEvents.add(new TEvent(arrivalTime,
                            EventTypes.Type.EVENT_ARRIVAL));
            }

            //Start turning the system off and on, if set so
            if(simulationTime > system.getOnAverageTime() && onoff)
                incomingEvents.add(new TEvent(RandomGenerator.getExp(
                        system.getOnAverageTime()), EventTypes.Type.SYSTEM_OFF));

            Collections.sort(incomingEvents, new TEventComparator());

            Log.info("EventLine initialized\n\tNumber of incoming events: "
                    + incomingEvents.size()
                    + "\n\tNumber of past events: " + pastEvents.size());

            return new EventLine(incomingEvents,pastEvents);
        }else
            return new EventLine();
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
            case SYSTEM_OFF:
                handleSystemOff(t);
                break;
            case SYSTEM_ON:
                handleSystemOn(t);
                break;
            default:
                handleUnknownEvent(t);
                break;
        }
    }

    private void handleArrival(TEvent t){
        Log.info("Handling arrival\n\tCurrent time: " + currentTime);

        double serviceTime;

        if(uniformDistribution){
            serviceTime = RandomGenerator.getDouble(minServiceTime,maxServiceTime);

        }
        else {
            serviceTime = averageServiceTime;

        }

        if(system.getNumberOfClientsInSystem() == 0 //||
                //0 or 1 clients in the system mean that it is not busy.
                //No. 1 means that already is 1 event in system and the event being handled is second one
                //system.getNumberOfClientsInSystem() == 1
                ){

            t.setClientId(system.addClient());
            eventLine.put(new TEvent(currentTime + serviceTime,
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
            eventLine.put(new TEvent(currentTime +
                    averageServiceTime, EventTypes.Type.EVENT_DEPARTURE,
                    //+1 because next client waiting for service has id 1 higher
                    (t.getClientId()+1) ));
        }
    }

    private void handleSystemOn(TEvent t){
        Log.info("System ON");
        eventLine.put(new TEvent(RandomGenerator.getExp(system.getOffAverageTime()) + t.getTime(),
                EventTypes.Type.SYSTEM_OFF));
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

//        //Add system ON event
//        if(eventLine.getIncomingEvents().stream().anyMatch(event -> event.getTime()
//                == windowEnd)){
//            int index;
//            index = eventLine.getIncomingEvents().indexOf(eventLine.getIncomingEvents().
//                    stream().filter(event -> event.getTime() == windowEnd));
//            eventLine.put();
//        }
        if (!eventLine.getIncomingEvents().isEmpty()) {
            eventLine.put(new TEvent(windowEnd, EventTypes.Type.SYSTEM_ON));

            //Move departures from system-off window
            for (TEvent e : eventLine.getIncomingEvents()) {
                if (e.getType().equals(EventTypes.Type.EVENT_DEPARTURE)
                        && e.getTime() < windowEnd && e.getTime() >= windowStart) {
                    e.setTime(e.getTime() + windowDuration);
                }
            }

            eventLine.sortEvents();
        }
    }

    private void handleUnknownEvent(TEvent T){
        Log.info("Received an unknown Event");
    }
}
