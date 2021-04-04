package ru.eatTheFrog.Robots.model.Entities.RobotsClasses;

import ru.eatTheFrog.Robots.model.Entities.*;
import ru.eatTheFrog.Robots.model.Entities.RobotsClasses.RobotFoodSensor;
import ru.eatTheFrog.Robots.model.Tasks.MovingToMouseTarget;

import java.util.Optional;

public class FoodFindTask extends MovingToMouseTarget {
    private RobotFoodSensor foodSensor;
    private Optional<IEdible> foodOptional = Optional.empty();
    private IRobot robot;
    public FoodFindTask(IRobot robot, RobotFoodSensor foodSensor) {
        super(robot);
        this.robot = robot;
        this.foodSensor = foodSensor;
    }

    @Override
    public void customAction() {
        this.robot.getManipulator().tryToEatSomething(this.robot, this.foodOptional);
    }

    @Override
    protected Optional<Target> getTarget() {
        return this.foodOptional.map(x -> (Target)x);
    }

    @Override
    public void selectTargetIfDisposed() {
        var foodOptionalFound = this.foodSensor.getNearestFood();
        if (foodOptionalFound.isPresent() || foodOptional.filter(x -> !x.isDisposed()).isEmpty())
            this.foodOptional = foodOptionalFound;
    }

}
