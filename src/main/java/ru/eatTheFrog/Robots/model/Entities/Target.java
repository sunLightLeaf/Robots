package ru.eatTheFrog.Robots.model.Entities;

public interface Target {
    public double getX();
    public double getY();
    public void setX(double x);
    public void setY(double y);
    public boolean isDisposed();
}
