package ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces;

import ru.eatTheFrog.Robots.model.RobotsModules.RobotGameManipulator;
import ru.eatTheFrog.Robots.model.RobotsModules.RobotMouth;
import ru.eatTheFrog.Robots.model.RobotsModules.RobotReproducer;
import ru.eatTheFrog.Robots.model.RobotsModules.RobotEnemySensor;
import ru.eatTheFrog.Robots.model.RobotsModules.RobotFoodsSensor;
import ru.eatTheFrog.Robots.model.Entities.Target;
import ru.eatTheFrog.Robots.model.static_modules.IReadonlyVector2D;
import ru.eatTheFrog.Robots.model.static_modules.MutableVector2D;

import java.util.Optional;

public interface ILivingRobot extends Target {
    long getTime();
    double getX();
    double getY();
    MutableVector2D getVelocity();
    double getRotationAcceleration();
    double getRotationSpeed();
    void applyRotateAcceleration(double acceleration);
    void setX(double newX);
    void setY(double newY);
    void applyShift(IReadonlyVector2D shift);
    void setDirection(double newDirection);
    double getDirection();
    void speedUp();
    public double getInclinationToFleshEating();
    public double getInclinationToVegetarianism();
    void frictionUp();
    double getLength();
    RobotGameManipulator getManipulator();
    Optional<RobotMouth> getRobotMouth();
    Optional<RobotFoodsSensor> getFoodSensor();
    Optional<RobotEnemySensor> getThreatSensor();
    Target getTargetWanderTo();
    public void addEnergy(double worth);
    public double getAttack();
    public double estimateEnemy(IEnemy enemy);

    RobotReproducer getReproducer();

}
