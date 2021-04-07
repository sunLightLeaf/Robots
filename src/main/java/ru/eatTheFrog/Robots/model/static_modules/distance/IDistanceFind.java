package ru.eatTheFrog.Robots.model.static_modules.distance;

import ru.eatTheFrog.Robots.model.Entities.Target;

public interface IDistanceFind {
    public double getDistance(Target first, Target second);
}
