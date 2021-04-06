package ru.eatTheFrog.Robots.model.RobotSchemes;

import ru.eatTheFrog.Robots.model.Entities.EntityType;
import ru.eatTheFrog.Robots.model.Entities.Food.FoodType;

public class EmptyScheme extends AbstractScheme {
    public EmptyScheme() {
        this.x = 0;
        this.y = 0;
        this.maxVelocity = 0;
        this.acceleration = 0;
        this.angularAcceleration = 0;
        this.slipping = 0;
        this.edibleType = FoodType.FLESH;
        attack = 10;
        this.type = EntityType.NOTHING;
        this.dangerType = EntityType.NOTHING;
    }
}
