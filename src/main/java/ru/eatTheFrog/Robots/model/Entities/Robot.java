package ru.eatTheFrog.Robots.model.Entities;

public class Robot implements IDrawable {

    private volatile double m_robotPositionX = 100;
    private volatile double m_robotPositionY = 100;
    private volatile double m_robotDirection = 0;

    public Robot(int initialX, int initialY) {
        m_robotPositionX = initialX;
        m_robotPositionY = initialY;
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


    @Override
    public double getX() {
        return m_robotPositionX;
    }

    @Override
    public double getY() {
        return m_robotPositionY;
    }

    @Override
    public double getDirection() {
        return m_robotDirection;
    }
}
