package ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces;

import ru.eatTheFrog.Robots.model.static_modules.IReadonlyVector2D;

public interface IWanderizeRobot {
    public double getWanderizeIrregularityRate();
    public IReadonlyVector2D getPosition();
    public double getDirection();
}
