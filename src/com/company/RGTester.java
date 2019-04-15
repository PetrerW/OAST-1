package com.company;

import java.lang.System;
import java.util.Scanner;

/**
 * A RandomGenerator class tester
 */
public class RGTester {

    public static void testPoisson(){
        try{
            Scanner in = new Scanner(System.in);
            String L = in.nextLine();
            double lambda = Double.parseDouble(L);

            for(int i=0; i<100; i++) {
                int theNumber = RandomGenerator.getPoisson(lambda);
                java.lang.System.out.println(theNumber);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
