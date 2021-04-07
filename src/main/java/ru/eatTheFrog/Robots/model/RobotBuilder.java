package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.Entities.Food.FoodType;
import ru.eatTheFrog.Robots.model.RobotsModules.*;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IRobot;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.RobotRaw;
import ru.eatTheFrog.Robots.model.GameAndArbitration.Game;
import ru.eatTheFrog.Robots.model.RobotSchemes.AbstractScheme;
import ru.eatTheFrog.Robots.model.Tasks.LiveTask;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class RobotBuilder {
    public static IRobot buildFromScheme(Game game, AbstractScheme scheme) {
        var robot = new RobotRaw();
        robot.task = new LiveTask(robot);
        REFLECTION_copyDoublesFromSchemeToRobot(robot, scheme);
        robot.setX(scheme.x);
        robot.setY(scheme.y);
        robot.schemeCreatedFrom = scheme;
        robot.addGameManupulator(new RobotGameManipulator(game, robot));
        robot.setVelocity(scheme.maxVelocity);
        robot.clock = game.getClocks();
        robot.robotReproducer = new RobotReproducer(game.robotsManager, robot);
        addEnemyEstimator(robot, scheme);
        robot.addFoodSensor(new RobotFoodsSensor(
                robot,
                game.foodManager, scheme.edibleType));
        robot.addDangerSensor(new RobotEnemySensor(
                robot,
                game.robotsManager));
        addMouth(robot, scheme.edibleType);

//        robot.inclinationToVegetarianism = scheme.inclinationToVegetarianism;
//        robot.acceleration = scheme.acceleration;
//        robot.attack = scheme.attack;
//        robot.slipping = scheme.slipping;
//        robot.type = scheme.type;
//        robot.wanderizeIrregularityRate = scheme.wanderizeIrregularityRate;
//        robot.thickness = scheme.thickness;
//        robot.length = scheme.length;

        robot.initAfterBuilding();
        return robot;

    }
    static private void addEnemyEstimator(RobotRaw robot, AbstractScheme scheme) {
        var estimator = new RobotEnemyEstimator(robot);
        estimator.solutionKMaxToAttack = scheme.solutionKMaxToAttack;
        estimator.solutionKsuperiority = scheme.solutionKsuperiority;
        estimator.solutionKvolumeEstimator = scheme.solutionKvolumeEstimator;
        robot.robotEnemyEstimator = estimator;
    }
    static private void addMouth(RobotRaw robotRaw, FoodType edibleType) {
        robotRaw.robotMouth = new RobotMouth(robotRaw, edibleType);
    }
    static void REFLECTION_copyDoublesFromSchemeToRobot(RobotRaw robotRaw, AbstractScheme scheme) {
        Arrays.stream(scheme.getClass().getFields())
                .filter(x -> x.getType().equals(double.class))
                .forEach(field -> {
                    var optionalField = getFieldOptional(robotRaw, field.getName());
                    optionalField.ifPresent(
                            realField -> {
                                if (realField.getType().equals(double.class)) {
                                    try {
                                        realField.set(robotRaw, field.get(scheme));
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                    );
                });
    }
    static Optional<Field> getFieldOptional(Object object, String fieldName) {
        try {
            var field = object.getClass().getField(fieldName);
            return Optional.of(field);
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

}














