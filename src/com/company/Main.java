package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        partOne();
//        partTwo();
//        partThree();
    }

    /**
     * Test the queue without turing off and on, lambda between 0.5 and 6
     */
    public static void partOne(){

        /*
            Tzw. rozbieg. Index of event that is the first to be considered while preparing report
         */
        int warmUpIndex = 4;
        int numberOfReplicationsForLambda = 40;
        double lambda = 0.5;

        String fileName = "Report_" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + ".txt";
        String fileName2 = "Report_Warm_Up_" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + ".txt";
        Report report = new Report(fileName);
        Report report2 = new Report(fileName2);

        // LinkedList - wynik pojedynczej symulacji
        // ArrayList - wyniki wielu symulacji
        ArrayList< LinkedList<TEvent>> pastEvents = new ArrayList<>();

        while(lambda < 6){

            // LinkedList - wynik pojedynczej symulacji
            // ArrayList - wyniki wielu symulacji
            pastEvents = new ArrayList<>();

            for(int i = 0; i < numberOfReplicationsForLambda; i++){
                Simulator simulator = new Simulator(lambda);
                pastEvents.add(simulator.simulate());
            }
            report.generateReport(lambda, pastEvents);
            report2.generateReport(lambda, pastEvents, warmUpIndex);

            lambda = lambda+0.1;
        }


    }

    public static void partTwo(){
        double lambda = 0.5;
        while(lambda < 4){
            Simulator simulator = new Simulator(lambda);
            simulator.simulate();
            //TODO: Write it to one file to easily generate the plots
            lambda = lambda+0.5;
        }
    }

    public static void partThree(){

    }






}
