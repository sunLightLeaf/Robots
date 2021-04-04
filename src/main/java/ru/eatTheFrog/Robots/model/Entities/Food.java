package ru.eatTheFrog.Robots.model.Entities;

public class Food implements IEdible {
    private double x;
    private double y;
    private boolean wasEaten = false;

    public Food(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Target asTarget() {
        return this;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
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
    public EdibleType getEdibleType() {
        return EdibleType.FOOD;
    }

    @Override
    public boolean isDisposed() {
        return this.wasEaten;
    }
    public void makeEated() {
        this.wasEaten = true;
    }
}
