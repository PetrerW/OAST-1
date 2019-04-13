package com.company;

import java.util.LinkedList;
import java.util.Collections;

// Class with Events (past and new)
public class EventLine{
    //Lowest time in the end of the list
    private LinkedList<TEvent> incomingEvents;
    private LinkedList<TEvent> pastEvents;

    public EventLine(){
        pastEvents = new LinkedList<TEvent>();
        incomingEvents = new LinkedList<TEvent>();
    }


    public EventLine(LinkedList<TEvent> incomingEvents, LinkedList<TEvent> pastEvents){
        this.incomingEvents = incomingEvents;
        sortEvents(incomingEvents);
        this.pastEvents = pastEvents;
        sortEvents(pastEvents);
    }

    /**
     * Removes the element from the incoming events list with the lowest time and adds it to the past events list
     * Updates simulation time
     * @return the element of the incoming events list with the lowest time or null if the list is empty
     **/
    public TEvent get(){
        if(!this.incomingEvents.isEmpty()){
            TEvent next = this.incomingEvents.removeLast();
            this.pastEvents.add(next);
            return next;
        }
        else
            return null;
    }

    /**
     * After initialization the only elements to be added are DEPARTURE_EVENTS
     * Their time is 0,125
     * @param newEvent - an event do be added
     */
    public void put(TEvent newEvent){
        incomingEvents.addLast(newEvent);
        sortEvents(incomingEvents);
    }

    public LinkedList<TEvent> getIncomingEvents() {
        return incomingEvents;
    }

    public void setIncomingEvents(LinkedList<TEvent> incomingEvents){
        this.incomingEvents = incomingEvents;
    }

    public LinkedList<TEvent> getPastEvents(){
        return this.pastEvents;
    }

    public void setPastEvents(LinkedList<TEvent> PastEvents){
        this.pastEvents = PastEvents;
    }

    public void sortEvents(LinkedList<TEvent> eventsList){
        Collections.sort(eventsList, new TEventComparator());
    }
}
