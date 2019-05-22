package com.company;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class RandomGeneratorTest extends TestCase {

    public void testGetPoisson() {
        try{
            double lambda = 6;

            for(int i=0; i<100; i++) {
                double theNumber = RandomGenerator.getPoisson(lambda);
                java.lang.System.out.println(theNumber);
                Assert.assertTrue(theNumber >= 0);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetExp() {
        try{
            double lambda = 40;

            for(int i=0; i<100; i++) {
                double theNumber = RandomGenerator.getExp(lambda);
                java.lang.System.out.println(theNumber);
                Assert.assertTrue(theNumber >= 0);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}