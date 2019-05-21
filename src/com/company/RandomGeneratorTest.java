package com.company;

import junit.framework.TestCase;

import java.util.Scanner;

public class RandomGeneratorTest extends TestCase {

    public void testGetPoisson() {
        try{
            double lambda = 6.0;

            for(int i=0; i<100; i++) {
                double theNumber = RandomGenerator.getPoisson(lambda);
                java.lang.System.out.println(theNumber);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}