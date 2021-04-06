package ru.eatTheFrog.Robots.model.RobotsModules;

import org.jetbrains.annotations.NotNull;
import ru.eatTheFrog.Robots.model.Configuration.RobotsConfig;
import ru.eatTheFrog.Robots.model.Entities.Food.Food;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IEnemy;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.ILivingRobot;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IRobot;
import ru.eatTheFrog.Robots.model.GameAndArbitration.Game;

public class RobotGameManipulator {
    Game game;
    private ILivingRobot robot;

    public RobotGameManipulator(Game game, ILivingRobot robot) {
        this.game = game;
        this.robot = robot;
    }


    public boolean tryToEatSomething(@NotNull Food food) {
        var robotMouth = robot.getRobotMouth().get();

        if (robotMouth.doesCanEat(food)) {

            food.makeEated();
            robot.addEnergy(robotMouth.calculateEnergyFromFood(food));

            return true;
        }
        return false;
    }
    public void tryToAttack(@NotNull IEnemy enemy) {
        robot.getRobotMouth().map((x) -> {
            if (x.doesCanAttack(enemy)) {
                enemy.harm(robot.getAttack());
                robot.addEnergy(-robot.getAttack()* RobotsConfig.ATTACK_PENALTY);
                robot.getRobotMouth().get().makeCooldown();
                return true;
            }
            return false;
        });
    }
}
