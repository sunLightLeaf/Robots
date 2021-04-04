package ru.eatTheFrog.Robots.model.Entities;

import ru.eatTheFrog.Robots.model.Game;

import javax.swing.text.html.Option;
import java.awt.*;
import java.util.Optional;

public class RobotGameManipulator {
    Game game;

    public RobotGameManipulator(Game game) {
        this.game = game;
    }
    private void tryToEatFood(IEater eater, Food food) {
        var robotMouth = eater.getRobotMouth().get();
        if (robotMouth.doesCanEat(food)) {
            food.makeEated();
        }
    }
    private void tryToEatRobot(IEater eater, IRobot robot2) {
        var robotMouth = eater.getRobotMouth().get();
        if (robotMouth.doesCanEat(robot2)) {
            robot2.makeEated();
            robot2.doInjured();
        }
    }


    public void tryToEatSomething(IEater robot, Optional<IEdible> edible) {
        if (edible.isPresent() && robot.getRobotMouth().isPresent()) {
            var edibleEssential = edible.get();
            switch (edibleEssential.getEdibleType()) {
                case FOOD -> tryToEatFood(robot, (Food) edibleEssential);
                case VEGGIE -> tryToEatRobot(robot, (IRobot) edibleEssential);
            }
        }
    }
}
