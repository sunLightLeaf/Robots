package ru.eatTheFrog.Robots.model.Entities.RobotsClasses;

import ru.eatTheFrog.Robots.model.Entities.EdibleType;
import ru.eatTheFrog.Robots.model.Entities.IEdible;
import ru.eatTheFrog.Robots.model.Entities.RobotRaw;
import ru.eatTheFrog.Robots.model.Game;
import ru.eatTheFrog.Robots.model.modules.NearestFoodFinder;

import java.util.Optional;

public class RobotFoodSensor {
    private RobotRaw robot;
    private Game game;
    private int seeRadius = 100;
    private final EdibleType preferedEdibleType;

    public RobotFoodSensor(RobotRaw robot, Game game, EdibleType preferedEdibleType) {
        this.preferedEdibleType = preferedEdibleType;
        this.robot = robot;
        this.game = game;
    }

    public Optional<IEdible> getNearestFood() {
        var foodIter = game.getEdibleOfType(preferedEdibleType);
        return NearestFoodFinder.<RobotRaw, IEdible>findNearestTarget(robot, foodIter, seeRadius, preferedEdibleType);
    }
}
