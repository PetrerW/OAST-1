package com.company;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;

/**
 * Generates Poisson and exponential values
 */
public class RandomGenerator {

    private static Random R = new Random();

    /**
     * Generates a value from exponential distribution
     *
     * @return
     */
    public static double getExp(double lambda) {
//        double w = 1 - R.nextDouble();
        ExponentialDistribution E = new ExponentialDistribution(40);
        return E.sample();
//        return (-1) * (Math.log(w));
    }

    /**
     * nextInt is normally exclusive of the top value,
     * so add 1 to make it inclusive
     *
     * @param min
     * @param max
     * @return
     */
    public static double getDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /**
     * @param lambda
     * @return Poisson distributed
     */
    public static double getPoisson(double lambda) {
        PoissonDistribution P = new PoissonDistribution(lambda*1000);
        return (double)(P.sample())/1000;
    }
}