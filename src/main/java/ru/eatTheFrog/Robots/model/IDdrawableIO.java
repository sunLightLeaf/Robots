package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.Entities.IDrawable;

import java.awt.*;

public interface IDdrawableIO {
    void setTargetPosition(Point p);
    void setWidthHeight(double width, double height);
    Iterable<IDrawable> getRobots();
    int getTargetX();
    int getTargetY();
}