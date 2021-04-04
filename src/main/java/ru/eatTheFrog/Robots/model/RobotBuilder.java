package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.Entities.EdibleType;
import ru.eatTheFrog.Robots.model.Entities.IRobot;
import ru.eatTheFrog.Robots.model.Entities.RobotsClasses.Organs.RobotMouth;
import ru.eatTheFrog.Robots.model.Entities.RobotRaw;
import ru.eatTheFrog.Robots.model.Entities.RobotsClasses.RobotFoodSensor;
import ru.eatTheFrog.Robots.model.RobotSchemes.IRobotScheme;
import ru.eatTheFrog.Robots.model.RobotSchemes.RobotScheme;

import java.awt.*;
import java.util.Optional;

public class RobotBuilder {
    static IRobot buildFromScheme(Game game, IRobotScheme scheme) {
        var robot = new RobotRaw();
        robot.setX(scheme.getX());
        robot.setY(scheme.getY());
        robot.setVelocity(scheme.getVelocity());
        robot.color = scheme.getColor();
        robot.preferedEdibleType = scheme.getPreferedEdibleType();
        robot.itsEdibleType = scheme.getItsEdibleType();
        robot.addFoodSensor(new RobotFoodSensor(
                robot,
                game,
                robot.preferedEdibleType));
        addMouth(robot, robot.preferedEdibleType);
        addGameManipulator(robot, game);
        return robot;
    }
    static IRobot buildVeggie(Game game, RobotScheme scheme) {
        var veggie = new RobotRaw();
        veggie.setX(scheme.x);
        veggie.setY(scheme.y);
        veggie.setVelocity(0.1);
        veggie.color = Color.green;
        veggie.preferedEdibleType = EdibleType.FOOD;
        veggie.itsEdibleType = EdibleType.VEGGIE;
        veggie.addFoodSensor(new RobotFoodSensor(veggie,game,EdibleType.FOOD));
        addMouth(veggie, EdibleType.FOOD);
        addGameManipulator(veggie, game);
        return veggie;
    }
    static IRobot buildHunty(Game game, RobotScheme scheme) {
        var hunty = new RobotRaw();
        hunty.setX(scheme.x);
        hunty.setY(scheme.y);
        hunty.color = Color.red;
        hunty.preferedEdibleType = EdibleType.VEGGIE;
        hunty.itsEdibleType = EdibleType.HUNTY;
        hunty.addFoodSensor(new RobotFoodSensor(hunty, game, EdibleType.VEGGIE));
        hunty.setVelocity(0.2);
        addMouth(hunty, EdibleType.VEGGIE);
        addGameManipulator(hunty, game);
        return hunty;
    }
    static private void addMouth(RobotRaw robotRaw, EdibleType edibleType) {
        robotRaw.robotMouth = Optional.of(new RobotMouth(robotRaw, edibleType));
    }
    static private void addGameManipulator(RobotRaw robotRaw, Game game) {
        robotRaw.robotGameManipulator = game.robotGameManipulator;
    }
}
