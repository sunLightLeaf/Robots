package ru.eatTheFrog.Robots.model.RobotSchemes;

import ru.eatTheFrog.Robots.model.Entities.EdibleType;

import java.awt.*;

public class VeggieScheme extends RobotScheme implements IRobotScheme {
    public VeggieScheme(double x, double y) {
        super(x, y);
    }

    @Override
    public double getVelocity() {
        return 0.1;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public Color getColor() {
        return Color.green;
    }

    @Override
    public EdibleType getPreferedEdibleType() {
        return EdibleType.FOOD;
    }

    @Override
    public EdibleType getItsEdibleType() {
        return EdibleType.VEGGIE;
    }
}
