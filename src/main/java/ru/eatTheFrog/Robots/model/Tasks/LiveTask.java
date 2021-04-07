package ru.eatTheFrog.Robots.model.Tasks;

import ru.eatTheFrog.Robots.model.Entities.*;
import ru.eatTheFrog.Robots.model.Entities.Food.Food;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IEnemy;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.ILivingRobot;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IRobot;
import ru.eatTheFrog.Robots.model.RobotsModules.RobotEnemySensor;
import ru.eatTheFrog.Robots.model.RobotsModules.RobotFoodsSensor;
import ru.eatTheFrog.Robots.model.static_modules.MutableVector2D;
import ru.eatTheFrog.Robots.model.static_modules.TargetRotator;
import ru.eatTheFrog.Robots.model.static_modules.distance.FindDistanceModule;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class LiveTask {
    private Random random = new Random();
    private final ILivingRobot robot;
    private final MovingToMouseTarget mover;
    public LiveTask(ILivingRobot robot) {
        this.robot = robot;
        this.mover = new MovingToMouseTarget(robot);
    }
    public void runAway(Target target) {
        if (FindDistanceModule.getDistance(target, robot) < 10) {
            mover.moveTo(MutableVector2D.fromTarget(target).plus(MutableVector2D.fromAngle(10*random.nextDouble())).toTarget());
        } else {
            mover.moveTo(TargetRotator.rotateTarget(target, robot, Math.PI));
        }
    }
    public void attack(IEnemy enemy) {
        mover.moveTo(enemy);

        robot.getManipulator().tryToAttack(enemy);
    }
    public boolean tryToEat(Food food) {
        mover.moveTo(food);
        return robot.getManipulator().tryToEatSomething(food);
    }
    public Optional<IEnemy> getNearRobot() {
        return robot.getThreatSensor()
                .map(RobotEnemySensor::getNearestRobots)
                .map(y -> y.filter(x -> x != robot))
                .flatMap(Stream::findFirst);
    }
    public Optional<Food> getNearFood() {
        return robot.getFoodSensor()
                .map(RobotFoodsSensor::getNearestFoods)
                .flatMap(Stream::findFirst);
    }
    public boolean tryToInteract() {
        return getNearRobot().map((x) -> {
            var estimating = robot.estimateEnemy(x);
            if (estimating > 0) {
                attack(x);

                return true;
            } else if (estimating < 0) {
                runAway(x);

                return true;
            }
            return false;
        }).orElse(false);
    }

    public boolean tryToEatFood() {
        return getNearFood().map(this::tryToEat).orElse(false);
    }
    public void doWander() {
        mover.moveTo(this.robot.getTargetWanderTo());
    }
    public void onModelUpdate() {
        if (robot.getReproducer().requestToReproducing()) return;
        if (tryToEatFood()) return;

        var x = tryToInteract();
        if (x) return;


        doWander();
    }
}
