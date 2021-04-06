package ru.eatTheFrog.Robots.model.RobotSchemes;

import ru.eatTheFrog.Robots.model.Entities.EntityType;
import ru.eatTheFrog.Robots.model.Entities.Food.FoodType;

public class HuntyScheme extends AbstractScheme {
    public HuntyScheme(double x, double y) {
        this.x = x;
        this.y = y;
        this.maxVelocity = 2;
        this.acceleration = 0.02;
        this.angularAcceleration = 0.001;
        this.slipping = -10;
        thickness = 10;
        length = 30;

        solutionKsuperiority = 1;
        solutionKvolumeEstimator = 5;
        solutionKMaxToAttack = 2;
        inclinationToVegetarianism = 0;
        this.edibleType = FoodType.FLESH;
        this.type = EntityType.HUNTY;
        attack = 100;
        this.dangerType = EntityType.NOTHING;
        this.wanderizeIrregularityRate = 100;
    }
}
