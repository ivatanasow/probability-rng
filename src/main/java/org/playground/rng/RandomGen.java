package org.playground.rng;

import org.playground.exception.NumberNotFoundException;

import java.util.Random;

public class RandomGen {

    // Values that may be returned by nextNum()
    private int[] randomNums;

    // Probability of the occurence of randomNums
    private float[] probabilities;

    private final Random randomGenerator;

    public RandomGen(int[] randomNums, float[] probabilities) {
        validate(randomNums, probabilities);

        this.randomNums = randomNums;
        this.probabilities = probabilities;
        this.randomGenerator = new Random();
    }

    /**
     * Returns one of the randomNums. When this method is called
     * multiple times over a long period, it should return the
     * numbers roughly with the initialized probabilities.
     */
    public int nextNum() {
        float randomNumber = randomGenerator.nextFloat();
        float sum = 0.0f;
        for (int i = 0; i < probabilities.length; i++) {
            sum += probabilities[i];
            if (randomNumber <= sum) {
                return randomNums[i];
            }
        }

        throw new NumberNotFoundException("Cannot determine random number");
    }

    private void validate(int[] randomNums, float[] probabilities) {
        if (randomNums == null || randomNums.length == 0) {
            throw new IllegalArgumentException("RandomNums should not be null or empty");
        }

        if (probabilities == null || probabilities.length == 0) {
            throw new IllegalArgumentException("Probabilities should not be null or empty");
        }

        if (randomNums.length != probabilities.length) {
            throw new IllegalArgumentException("Parameters size does not match");
        }

        for (float p : probabilities) {
            if (Math.signum(p) < 0) {
                throw new IllegalArgumentException("Probability value cannot be less than zero");
            }
        }
    }
}
