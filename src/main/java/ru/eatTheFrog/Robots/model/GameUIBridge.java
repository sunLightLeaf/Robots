package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.Entities.Food;
import ru.eatTheFrog.Robots.model.Entities.IDrawableRobot;
import ru.eatTheFrog.Robots.model.Entities.IEdible;
import ru.eatTheFrog.Robots.model.Entities.Target;
import ru.eatTheFrog.Robots.model.RobotSchemes.IRobotScheme;
import ru.eatTheFrog.Robots.model.RobotSchemes.RobotScheme;

import java.awt.*;
import java.util.Iterator;

public interface GameUIBridge {
    void setTargetPosition(Point p);
    void setWidthHeight(double width, double height);
    Iterator<IDrawableRobot> getRobotsDrawable();
    double getTargetX();
    double getTargetY();
    public void onModelEvent();
    public void addRobotFromScheme(IRobotScheme robotScheme);
    public Iterator<Target> getFood();

}