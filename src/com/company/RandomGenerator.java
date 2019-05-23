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
    public static double getExp(double mean) {
        ExponentialDistribution E = new ExponentialDistribution(mean);
        return E.sample();
    }

    /**
     * Returns a random real number from an exponential distribution
     * with rate &lambda;.
     *
     * @param  lambda the rate of the exponential distribution
     * @return a random real number from an exponential distribution with
     *         rate {@code lambda}
     * @throws IllegalArgumentException unless {@code lambda > 0.0}
     */
    public static double getExponential(double lambda) {
        if (!(lambda > 0.0))
            throw new IllegalArgumentException("lambda must be positive: " + lambda);

        double w = 1 - R.nextDouble();

        return -Math.log(w) / lambda;
    }

    /**
     * @param min
     * @param max
     * @return
     */
    public static double getDouble(double min, double max){
        return ThreadLocalRandom.current().nextDouble(min, max);

    }

    /**
     * @param mean
     * @return Poisson distributed
     */
    public static int getPoisson(double mean) {
        PoissonDistribution P = new PoissonDistribution(mean);
        return P.sample();
    }


    public static int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}