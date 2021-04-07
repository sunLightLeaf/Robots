package ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces;

import ru.eatTheFrog.Robots.model.Entities.Target;
import ru.eatTheFrog.Robots.model.RobotSchemes.AbstractScheme;

public interface IReproduceRobot extends Target {
    double getEnergyPercently();
    AbstractScheme getScheme();
    void setEnergyPercently(double value);
}
