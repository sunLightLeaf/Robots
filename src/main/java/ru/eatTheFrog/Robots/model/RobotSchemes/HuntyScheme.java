package ru.eatTheFrog.Robots.model.RobotSchemes;

import ru.eatTheFrog.Robots.model.Entities.EdibleType;

import java.awt.*;

public class HuntyScheme extends RobotScheme implements IRobotScheme {
    public HuntyScheme(double x, double y) {
        super(x, y);
    }

    @Override
    public double getVelocity() {
        return 0.2;
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
        return Color.red;
    }

    @Override
    public EdibleType getPreferedEdibleType() {
        return EdibleType.VEGGIE;
    }

    @Override
    public EdibleType getItsEdibleType() {
        return EdibleType.HUNTY;
    }
}
