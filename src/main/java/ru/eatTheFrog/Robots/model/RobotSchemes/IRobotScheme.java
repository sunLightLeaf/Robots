package ru.eatTheFrog.Robots.model.RobotSchemes;

import ru.eatTheFrog.Robots.model.Entities.EdibleType;

import java.awt.*;

public interface IRobotScheme {
    double getVelocity();

    double getX();

    double getY();

    Color getColor();

    EdibleType getPreferedEdibleType();

    EdibleType getItsEdibleType();
}
