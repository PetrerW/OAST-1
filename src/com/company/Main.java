package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Main {

    static String fileName1 = "Report" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + "_1.txt";
    static String fileName2 = "Report" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + "_2.txt";
    static String fileName3 = "Report" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + "_3.txt";

    public static void main(String[] args) {

        partOne();
        partTwo();
        partThree();
    }

    /**
     * Test the queue without turing off and on, lambda between 0.5 and 6
     */
    private static void partOne() {

        /*
            Tzw. rozbieg. Index of event that is the first to be considered
            while preparing report
         */
        int warmUpIndex = 4;
        int numberOfReplicationsForLambda = 40;
        float lambda = 0.5f;
        int simulationTime = 100;

        //String fileName = "Report_" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + ".txt";
        //Report report = new Report(fileName);
        Report report2 = new Report(fileName1);

        // LinkedList - wynik pojedynczej symulacji
        // ArrayList - wyniki wielu symulacji
        ArrayList<LinkedList<TEvent>> pastEvents = new ArrayList<>();

        while (lambda < 6) {

            // LinkedList - wynik pojedynczej symulacji
            // ArrayList - wyniki wielu symulacji
            pastEvents = new ArrayList<>();

            for (int i = 0; i < numberOfReplicationsForLambda; i++) {
                Simulator simulator = new Simulator(lambda,simulationTime);
                pastEvents.add(simulator.simulate());
            }
            //report.generateReport(lambda, pastEvents);
            report2.generateReport(lambda, pastEvents, warmUpIndex);

            lambda = lambda + 0.1f;
        }


    }

    private static void partTwo() {

        /*
            Tzw. rozbieg. Index of event that is the first to be considered while preparing report
         */
        int warmUpIndex = 4;
        int numberOfReplicationsForLambda = 40;
        float lambda = 0.5f;
        int simulationTime = 100;



        Report report2 = new Report(fileName2);

        // LinkedList - wynik pojedynczej symulacji
        // ArrayList - wyniki wielu symulacji
        ArrayList<LinkedList<TEvent>> pastEvents = new ArrayList<>();

        while (lambda < 4) {

            // LinkedList - wynik pojedynczej symulacji
            // ArrayList - wyniki wielu symulacji
            pastEvents = new ArrayList<>();

            for (int i = 0; i < numberOfReplicationsForLambda; i++) {
                Simulator simulator = new Simulator(lambda,true, simulationTime);
                pastEvents.add(simulator.simulate());
            }
            report2.generateReport(lambda, pastEvents, warmUpIndex);

            lambda = lambda + 0.1f;
        }

    }

    private static void partThree() {

        /*
            Tzw. rozbieg. Index of event that is the first to be considered while preparing report
         */
        int warmUpIndex = 4;
        int numberOfReplicationsForLambda = 40;
        float lambda = 0.5f;
        int simulationTime = 100;


        //String fileName = "Report_on_off_" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + ".txt";
        //Report report = new Report(fileName);
        Report report3 = new Report(fileName3);

        // LinkedList - wynik pojedynczej symulacji
        // ArrayList - wyniki wielu symulacji
        ArrayList<LinkedList<TEvent>> pastEvents = new ArrayList<>();

        while (lambda < 4) {

            // LinkedList - wynik pojedynczej symulacji
            // ArrayList - wyniki wielu symulacji
            pastEvents = new ArrayList<>();

            for (int i = 0; i < numberOfReplicationsForLambda; i++) {
                Simulator simulator = new Simulator(lambda,true,simulationTime,true);
                pastEvents.add(simulator.simulate());
            }
            //report.generateReport(lambda, pastEvents);
            report3.generateReport(lambda, pastEvents, warmUpIndex);

            lambda = lambda + 0.1f;
        }
    }


}
