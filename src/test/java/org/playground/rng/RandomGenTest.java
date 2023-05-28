package org.playground.rng;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RandomGenTest {

    @Test
    public void statisticalTest() {
        int[] randomNums = new int[]{-1, 0, 1, 2, 3};
        float[] probabilities = new float[]{0.01f, 0.3f, 0.58f, 0.1f, 0.01f};
        int runs = 2_000_000;

        Map<Integer, Integer> hits = new HashMap<>();
        for (int randomNum : randomNums) {
            hits.put(randomNum, 0);
        }

        RandomGen randomGen = new RandomGen(randomNums, probabilities);

        for (int i = 0; i < runs; i++) {
            int num = randomGen.nextNum();
            hits.put(num, hits.get(num) + 1);
        }

        Map<Integer, Float> hitPercentage = new HashMap<>();
        hits.forEach((num, count) -> hitPercentage.put(num, (float) ((count * 100) / runs) / 100));

        System.out.print("Hits: ");
        System.out.println(hits);

        System.out.print("Actual probabilities: ");
        System.out.println(hitPercentage);

    }
}
