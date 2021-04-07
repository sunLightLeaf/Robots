package ru.eatTheFrog.Robots.model.RobotSchemes;

import ru.eatTheFrog.Robots.model.Entities.EntityType;
import ru.eatTheFrog.Robots.model.Entities.Food.FoodType;

public class VeggieScheme extends AbstractScheme {
    public VeggieScheme(double x, double y) {
        this.x = x;
        this.y = y;
        this.maxVelocity = 1;
        this.acceleration = 0.01;
        this.angularAcceleration = 0.002;
        this.slipping = 10;
        thickness = 15;
        length = 45;
        solutionKsuperiority = 1;
        solutionKvolumeEstimator = 1;
        solutionKMaxToAttack = 2;
        inclinationToVegetarianism = 1;
        this.edibleType = FoodType.GRASS;
        this.type = EntityType.VEGGIE;
        attack = 0;
        this.dangerType = EntityType.HUNTY;
        this.wanderizeIrregularityRate = 100;
    }
}
