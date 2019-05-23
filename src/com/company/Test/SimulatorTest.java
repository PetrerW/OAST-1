package com.company.Test;

import com.company.EventLine;
import com.company.Simulator;
import com.company.TEvent;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {

    @Test
    public void testSimulate(){
        Simulator simulator = new Simulator(5);
        simulator.simulate();
    }

    @Test
    public void testInitializeEventLine() {
        Simulator simulator = new Simulator(100);
        EventLine e = new EventLine();
        for (int i = 0; i < 100; i++) {
            e = simulator.initializeEventLine(6, false);
            Set<TEvent> noDups = new LinkedHashSet<TEvent>(e.getIncomingEvents());
            Assert.assertTrue(Arrays.equals(e.getIncomingEvents().toArray(),
                    noDups.toArray()));
        }
    }
}