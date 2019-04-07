package com.company;

import java.util.LinkedList;

public class EventLineTester {
    private TEvent[] Events;

    /**
     * Generate a fixed array for the test purposes
     * @return array of TEvent objects
     */
    private TEvent[] generateEvents(){
        TEvent[] Arr = {
                (new TEvent(0.5, "A")),
                (new TEvent(1, "B")),
                (new TEvent(1.5, "C")),
                (new TEvent(2.0, "A")),
                (new TEvent(2.5, "B"))
        };

        return Arr;
    }

    public boolean constructorTest(){
        this.Events = generateEvents();
        EventLine EL = new EventLine(this.Events, new TEvent[1],0 );
        final LinkedList<TEvent> incoming = EL.getIncomingEvents();
        for (int i = 0; i < this.Events.length; i++) {
            System.out.println(this.Events[i]);
            //Check if copied in a good way
            if(! incoming.get(i).getType().equals(Events[i]))
                continue;
            else
                return false;
        }
        EL = new EventLine(new TEvent[1], this.Events, 0);
        final LinkedList<TEvent> past = EL.getPastEvents();
        for (int i = 0; i < this.Events.length; i++) {
            System.out.println(this.Events[i]);
            //Check if copied in a good way
            if(! past.get(i).getType().equals(Events[i]))
                continue;
            else
                return false;
        }
        return true;
    }

    public boolean putTest(){
        this.Events = generateEvents();
        EventLine EL = new EventLine(this.Events, new TEvent[0],0 );
        System.out.println("Before adding an Event:");
        for (int i = 0; i < this.Events.length; i++) {
            System.out.println("[" + i + "]" + this.Events[i]);
        }
        TEvent E = new TEvent(1.75, "B");
        EL.put(E);
        System.out.println("After adding an Event:");
        for (int i = 0; i < EL.getIncomingEvents().size(); i++) {
            System.out.println("[" + i + "]" + EL.getIncomingEvents().get(i));
        }
        if (EL.getIncomingEvents().get(2).toString().contentEquals(E.toString())) {
            System.out.println("putTest succeeded");
            return true;
        }
        else{
            System.out.println("putTest failed");
            return false;
        }
    }

    public boolean getTest(){
        this.Events = generateEvents();
        EventLine EL = new EventLine(this.Events, new TEvent[0],0 );
        System.out.println("Before removing an Event:");
        System.out.println("Incoming list:");
        for (int i = 0; i < EL.getIncomingEvents().size(); i++) {
            System.out.println("[" + i + "]" + EL.getIncomingEvents().get(i));
        }
        System.out.print("Past list:");
        for (int i = 0; i < EL.getPastEvents().size(); i++) {
            System.out.println("[" + i + "]" + EL.getPastEvents().get(i));
        }

        System.out.println("Current time: " + EL.getCurrentTime());
        //Remove the event
        EL.get();

        System.out.println("After removing an Event:");
        System.out.println("Incoming list:");
        for (int i = 0; i < EL.getIncomingEvents().size(); i++) {
            System.out.println("[" + i + "]" + EL.getIncomingEvents().get(i));
        }
        System.out.println("Past list:");
        for (int i = 0; i < EL.getPastEvents().size(); i++) {
            System.out.println("[" + i + "]" + EL.getPastEvents().get(i));
        }
        System.out.println("Current time: " + EL.getCurrentTime());

        if(EL.getPastEvents().get(0).equals(this.Events[0]) && EL.getCurrentTime() == this.Events[1].getTime())
            return true;
        else
            return false;
    }
}
