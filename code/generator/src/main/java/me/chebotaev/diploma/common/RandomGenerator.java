package me.chebotaev.diploma.common;

import java.util.Random;

public class RandomGenerator {

    static Random randomGenerator = new Random();

    static {
        randomGenerator.setSeed(System.currentTimeMillis());
    }

    public static long get() {
        return randomGenerator.nextLong();
    }

    public static int get(int max) {
        return randomGenerator.nextInt(max);
    }

    public static int get(int min, int max) {
        if (min == max) {
            return min;
        } else if (max < min) {
            throw new IllegalArgumentException("Wrong interval");
        } else {
            return min + randomGenerator.nextInt(max - min);
        }
    }
}
