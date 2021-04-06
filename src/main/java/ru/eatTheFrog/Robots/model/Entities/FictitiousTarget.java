package ru.eatTheFrog.Robots.model.Entities;

public class FictitiousTarget implements Target {
    private double x;
    private double y;

    public FictitiousTarget(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean isDisposed() {
        return false;
    }
}
