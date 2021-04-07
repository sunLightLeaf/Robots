package ru.eatTheFrog.Robots.model.static_modules.Algebra;

import java.util.Random;

public class MutationDistributor {
    public static double getRandomDistribution(double mediocrityRate) {
        var random = new Random();
        var randomNumber = random.nextDouble();
        if (randomNumber == 0)
            return 1;
        var randomDistribution = 1/(mediocrityRate*randomNumber)*(random.nextBoolean() ? 1 : -1);
        if (randomDistribution > 1)
            return 1;
        return randomDistribution;
    }
}
