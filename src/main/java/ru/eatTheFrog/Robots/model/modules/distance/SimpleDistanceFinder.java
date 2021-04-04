package ru.eatTheFrog.Robots.model.modules.distance;

import ru.eatTheFrog.Robots.model.Entities.Target;

public class SimpleDistanceFinder implements IDistanceFind {
    public double getDistance(Target first, Target second) {
        return Math.abs(first.getX()-second.getX()) + Math.abs(first.getY()-second.getY());
    }
}
