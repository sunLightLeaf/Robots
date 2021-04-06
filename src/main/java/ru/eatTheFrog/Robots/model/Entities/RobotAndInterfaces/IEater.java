package ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces;

import ru.eatTheFrog.Robots.model.Entities.Target;

public interface IEater extends Target {
    double getLength();
    long getTime();
    double getDirection();
    double getInclinationToFleshEating();
    double getInclinationToVegetarianism();

    double getThickness();
}
