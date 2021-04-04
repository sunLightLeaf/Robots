package ru.eatTheFrog.Robots.model.Entities.RobotsClasses.Organs;

import ru.eatTheFrog.Robots.model.Entities.*;
import ru.eatTheFrog.Robots.model.modules.distance.FindDistanceModule;
import ru.eatTheFrog.Robots.model.modules.distance.SimpleDistanceFinder;

public class RobotMouth {
    private IEater eater;
    private double eatRange = 10;
    private EdibleType eatTargetType = EdibleType.NOTHING;

    public RobotMouth(RobotRaw robot, EdibleType edibleType) {
        this.eater = robot;
        this.eatTargetType = edibleType;
    }

    public boolean doesCanEat(IEdible b) {
        if (b.getEdibleType() != eatTargetType)
            return false;
        return FindDistanceModule.getDistance(eater, b) < eatRange;
    }
}
