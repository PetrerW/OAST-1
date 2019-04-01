package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class EventLineTester {
    private TEvent[] Events;

    /**
     * Generate a fixed array for the test purposes
     * @return array of TEvent objects
     */
    private TEvent[] generate_events(){
        TEvent[] Arr = {
                (new TEvent(0.5, "A")),
                (new TEvent(1, "B")),
                (new TEvent(1.5, "C")),
                (new TEvent(2.0, "A")),
                (new TEvent(2.5, "B"))
        };

        return Arr;
    }

    public boolean put_test(){
        this.Events = generate_events();
        EventLine EL = new EventLine(this.Events, new TEvent[0],0 );
        //TODO finish the test
    }
}
