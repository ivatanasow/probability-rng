package org.playground.rng;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomGenTest {

    @Test
    public void invalidInputTest(){
        int[] randomNums = null;
        float[] probabilities = new float[]{0.01f, 0.3f, 0.58f, 0.1f, 0.01f};

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            RandomGen randomGen = new RandomGen(randomNums, probabilities);
        });

        String expectedMessage = "RandomNums should not be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void invalidInputTest_1(){
        int[] randomNums = new int[]{-1, 0, 1, 2, 3};;
        float[] probabilities = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            RandomGen randomGen = new RandomGen(randomNums, probabilities);
        });

        String expectedMessage = "Probabilities should not be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void invalidInputTest_2(){
        int[] randomNums = new int[]{-1, 0};
        float[] probabilities = new float[]{0.01f, 0.3f, 0.58f, 0.1f, 0.01f};

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            RandomGen randomGen = new RandomGen(randomNums, probabilities);
        });

        String expectedMessage = "Parameters size does not match";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void invalidInputTest_3(){
        int[] randomNums = new int[]{-1, 0, 1, 2, 3};
        float[] probabilities = new float[]{0.01f, 0.3f, -0.58f, 0.1f, 0.01f};

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            RandomGen randomGen = new RandomGen(randomNums, probabilities);
        });

        String expectedMessage = "Probability value cannot be less than zero";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

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
