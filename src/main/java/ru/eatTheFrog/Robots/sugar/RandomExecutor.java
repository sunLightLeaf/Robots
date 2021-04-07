package ru.eatTheFrog.Robots.sugar;

import javax.swing.*;
import java.util.Random;
import java.util.function.Function;

public class RandomExecutor {
    static Random random = new Random();
    public static void executeRandomly(double probability, Runnable runnable) {
        var nextDouble = random.nextDouble();
        if (nextDouble <= probability)
            runnable.run();
    }
}
