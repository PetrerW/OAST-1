package com.company;

import java.util.LinkedList;

// Class with Events (past and new)
public class EventLine{
    //Najmniejszy czas na końcu
    public LinkedList<TEvent> incomingEvents;
    public LinkedList<TEvent> pastEvents;

    /**
    @return the element of the incoming events list with the lowest time or null if the list is empty
     **/
    public TEvent get(){
        if(!this.incomingEvents.isEmpty()){
            TEvent Next = this.incomingEvents.removeLast();
            this.pastEvents.add(Next);
        }
        else
            return null;
    }
}
