package ru.eatTheFrog.Robots.model.modules.distance;

import ru.eatTheFrog.Robots.model.Entities.Target;

public interface IDistanceFind {
    public double getDistance(Target first, Target second);
}
