package ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces;

import ru.eatTheFrog.Robots.model.Entities.Target;

public interface IEnemy extends Target {
    double getHealth();

    double getThickness();

    double getAttack();

    double getLength();

    void harm(double attack);

    double getSize();
}
