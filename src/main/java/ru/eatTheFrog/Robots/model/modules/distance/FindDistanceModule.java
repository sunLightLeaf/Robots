package ru.eatTheFrog.Robots.model.modules.distance;

import ru.eatTheFrog.Robots.model.Entities.Target;

public class FindDistanceModule {
    static IDistanceFind distanceFinder = new SimpleDistanceFinder();
    public static double getDistance(Target a, Target b) {
       return distanceFinder.getDistance(a, b);
    }
}
