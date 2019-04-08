package com.company;

import java.util.Random;

/**
 * Generates Poisson and exponential values
 */
public class RandomGenerator {

    private static Random R;

    /**
     * Generates a value from exponential distribution
     * @return
     */
    public static double getExp(double lambda){
        double w = 1 - R.nextDouble();
        return Math.log(w)/lambda;
    }
}
