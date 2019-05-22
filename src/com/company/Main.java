package com.company;

public class Main {

    public static void main(String[] args) {
        partOne(); //TODO: Why is only one report generated?
        //TODO: Maybe it writes to the same file in every iteration
        // and only the last is saved?
//        partTwo();
//        partThree();
    }

    /**
     * Test the queue without turing off and on, lambda between 0.5 and 6
     */
    public static void partOne(){
        double lambda = 0.5;
        while(lambda < 6){
            Simulator simulator = new Simulator(lambda);
            simulator.simulate();
            //TODO: Write it to one file to easily generate the plots
            lambda = lambda+0.5;
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
