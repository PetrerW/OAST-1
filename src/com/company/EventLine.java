package com.company;

import java.time.temporal.Temporal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.*;

// Class with Events (past and new)
public class EventLine{
    //Lowest time in the end of the list
    private LinkedList<TEvent> incomingEvents;
    private LinkedList<TEvent> pastEvents;
    private double currentTime;

    public EventLine(TEvent[] incoming, TEvent[] past, double _currentTime){
        this.incomingEvents = new LinkedList<TEvent>();
        //TODO: Fix this. Add correctly the array to the list
        this.incomingEvents.addAll(incoming);
    }

    /**
     * Removes the element from the incoming events list with the lowest time and adds it to the past events list
     * Updates simulation time
     * @return the element of the incoming events list with the lowest time or null if the list is empty
     **/
    public TEvent get(){
        if(!this.incomingEvents.isEmpty()){
            TEvent Next = this.incomingEvents.removeLast();
            this.pastEvents.add(Next);
            this.currentTime = this.incomingEvents.getLast().get_time();
            return Next;
        }
        else
            return null;
    }

    /**
     * @param NewEvent - an event do be added
     * @return true if succeeded
     */
    public boolean put(TEvent NewEvent){
        double newTime = NewEvent.get_time();
        if(newTime>=this.currentTime){
            // add() moves to the right all subsequent elements
            //TODO: Check if correctly added to the list
            int index = findFirstGreaterThan(newTime) + 1;
            this.incomingEvents.add(index, NewEvent);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @return Index of the first event with greater time of arrival than the _time
     */
    private int findFirstGreaterThan(double _time){
        TEvent E = this.incomingEvents.stream()
                .filter(x -> x.get_time() > _time)
                .findFirst()
                .orElse(null);

        return this.incomingEvents.indexOf(E);
    }
}
