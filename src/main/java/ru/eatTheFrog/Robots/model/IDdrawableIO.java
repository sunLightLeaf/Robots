package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.Entities.IDrawableRobot;

import java.awt.*;
import java.util.Iterator;

public interface IDdrawableIO {
    void setTargetPosition(Point p);
    void setWidthHeight(double width, double height);
    Iterator<IDrawableRobot> getRobots();
    int getTargetX();
    int getTargetY();
}