package ru.eatTheFrog.Robots.model.Entities;

import ru.eatTheFrog.Robots.model.Entities.RobotsClasses.FoodFindTask;
import ru.eatTheFrog.Robots.model.Entities.RobotsClasses.Organs.RobotMouth;
import ru.eatTheFrog.Robots.model.Entities.RobotsClasses.RobotFoodSensor;
import ru.eatTheFrog.Robots.model.Tasks.RobotTask;

import java.awt.*;
import java.util.Optional;

public class RobotRaw implements IRobot {
    public RobotGameManipulator robotGameManipulator;
    public Optional<RobotMouth> robotMouth;

    private boolean deadlyInjured = false;
    public volatile double m_maxVelocity = 0.1;
    public volatile double m_maxAngularVelocity = 0.001;
    public volatile double m_robotPositionX = 100;
    public Color color;
    public volatile double m_robotPositionY = 100;
    public volatile double m_robotDirection = 0;
    public EdibleType preferedEdibleType;
    public EdibleType itsEdibleType;
    public RobotTask task;

    public RobotRaw() {

    }
    public void setVelocity(double velocity) {
        this.m_maxVelocity = velocity;
    }
    public Target asTarget() {
        return this;
    }

    public void setX(double x) {
        m_robotPositionX = x;
    }

    public void setY(double y) {
        m_robotPositionY = y;
    }

    public void setDirection(double d) {
        m_robotDirection = d;
    }

    public IDrawableRobot asDrawable(){
        return (IDrawableRobot) this;
    }

    public double getX() {
        return m_robotPositionX;
    }

    public double getY() {
        return m_robotPositionY;
    }

    public double getDirection() {
        return m_robotDirection;
    }

    @Override
    public void onModelUpdate() {
        this.task.onModelUpdate();
    }
    public boolean isInjured() {
        return this.deadlyInjured;
    }
    public void doInjured() {
        this.deadlyInjured = true;
    }

    @Override
    public boolean isDisposed() {
        return this.deadlyInjured;
    }
    public void makeEated() {
        this.deadlyInjured = true;
    }

    @Override
    public Optional<RobotMouth> getRobotMouth() {
        return this.robotMouth;
    }

    @Override
    public double getVelocity() {
        return this.m_maxVelocity;
    }

    @Override
    public double getAngularVelocity() {
        return this.m_maxAngularVelocity;
    }

    @Override
    public RobotGameManipulator getManipulator() {
        return this.robotGameManipulator;
    }
    @Override
    public Color getColor() {
        return this.color;
    }

    public void addFoodSensor(RobotFoodSensor foodSensor) {
        this.task = new FoodFindTask(this, foodSensor);
    }
    @Override
    public EdibleType getEdibleType() {
        return this.itsEdibleType;
    }
    public EdibleType getPreferedEdibleType() { return this.preferedEdibleType;}
}
