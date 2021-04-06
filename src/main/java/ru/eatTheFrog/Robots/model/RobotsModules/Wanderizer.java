package ru.eatTheFrog.Robots.model.RobotsModules;

import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IWanderizeRobot;
import ru.eatTheFrog.Robots.model.Entities.Target;
import ru.eatTheFrog.Robots.model.static_modules.MutableVector2D;

import static ru.eatTheFrog.Robots.model.static_modules.Algebra.MutationDistributor.getRandomDistribution;

public class Wanderizer {
    private IWanderizeRobot robotWanderer;
    public Wanderizer(IWanderizeRobot robot) {
        this.robotWanderer = robot;
    }


    public Target getTargetWanderTo() {
        return MutableVector2D.from(robotWanderer.getPosition())
                .plus(MutableVector2D.fromAngle(robotWanderer.getDirection()))
                .rotate(getRandomDistribution(robotWanderer.getWanderizeIrregularityRate()))
                .toTarget();

    }
}
