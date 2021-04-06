package ru.eatTheFrog.Robots.model.GameAndArbitration.GameInterfaces;

import ru.eatTheFrog.Robots.model.Entities.Food.Food;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IDrawableRobot;
import ru.eatTheFrog.Robots.model.RobotSchemes.AbstractScheme;

import java.util.Iterator;

public interface GameUIBridge {
    void setWidthHeight(double width, double height);
    Iterator<IDrawableRobot> getRobotsDrawable();
    public void nextTick();
    public void addRobotFromScheme(AbstractScheme robotScheme);
    public Iterator<Food> getFoodDrawable();
}