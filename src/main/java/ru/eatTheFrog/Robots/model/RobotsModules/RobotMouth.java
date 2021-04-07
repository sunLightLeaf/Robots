package ru.eatTheFrog.Robots.model.RobotsModules;

import ru.eatTheFrog.Robots.model.Configuration.RobotsConfig;
import ru.eatTheFrog.Robots.model.Entities.Food.Food;
import ru.eatTheFrog.Robots.model.Entities.Food.FoodType;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IEater;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IEnemy;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IRobot;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.RobotRaw;
import ru.eatTheFrog.Robots.model.static_modules.MutableVector2D;
import ru.eatTheFrog.Robots.model.static_modules.distance.FindDistanceModule;

public class RobotMouth {
    private final IEater eater;
    private double eatRange;
    private FoodType eatTargetType = FoodType.ANTIMATTER;
    private double cooldownMs = RobotsConfig.ATTACK_COOLDOWN_TICKS;
    private long lastTimeUsed = 0;

    public RobotMouth(RobotRaw robot, FoodType edibleType) {
        this.eater = robot;
        this.eatTargetType = edibleType;
    }

    public boolean isReady() {
        var rdy = eater.getTime() - this.lastTimeUsed > this.cooldownMs;
        return rdy;
    }
    public void makeCooldown() {
        this.lastTimeUsed = eater.getTime();
    }
    public boolean doesCanAttack(IEnemy enemy) {
        if(!isReady())
            return false;
        return FindDistanceModule.getDistance(eater, enemy) < (eatRange+enemy.getSize());
    }
    public double calculateEnergyFromFood(Food food) {
        return switch (food.foodType()) {
            case FLESH -> food.getWorth() * eater.getInclinationToFleshEating();
            case GRASS -> food.getWorth() * eater.getInclinationToVegetarianism();
            case ANTIMATTER -> "this will never happen".hashCode();
        };
    }
    public boolean doesCanEat(Food b) {
        if(!isReady())
            return false;
        if (b.foodType() != eatTargetType)
            return false;
        return FindDistanceModule.getDistance(
                MutableVector2D.fromTarget(eater)
                .plus(MutableVector2D.fromAngle(eater.getDirection()).multiply(eater.getLength()/2))
                .toTarget()
                , b
        ) <  10*eater.getThickness()/RobotsConfig.FOOD_EATING_DIFFICULTY;
    }

    public FoodType getEatTargetType() {
        return eatTargetType;
    }
}
