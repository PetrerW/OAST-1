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
    public static double get_Exp(double lambda){
        double w = R.nextDouble();
        return (1/lambda)*Math.log(w);
    }
}
