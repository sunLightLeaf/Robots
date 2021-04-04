package ru.eatTheFrog.Robots.model.Entities;

import ru.eatTheFrog.Robots.model.Entities.RobotsClasses.Organs.RobotMouth;

import java.util.Optional;

public interface IRobot extends IDrawableRobot, Target, IEater, IEdible {
    public void onModelUpdate();
    public Optional<RobotMouth> getRobotMouth();
    public void setX(double x);
    public void setY(double y);
    public void doInjured();
    public boolean isInjured();
    public double getVelocity();
    public double getAngularVelocity();
    public void setDirection(double direction);
    public RobotGameManipulator getManipulator();

}
