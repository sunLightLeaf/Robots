package ru.eatTheFrog.Robots.model.Entities.Food;

import ru.eatTheFrog.Robots.model.Entities.Target;

public class Food implements Target {
    private double x;
    private double y;
    private long createTime;
    private boolean wasEaten = false;
    private FoodType type;
    private double worth;

    public Food(double x, double y, FoodType type, double worth, long time) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.worth = worth;
        createTime = time;
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
    public FoodType foodType() {
        return this.type;
    }
    public double getWorth() {
        return this.worth;
    }


    @Override
    public boolean isDisposed() {
        return this.wasEaten;
    }
    public void makeEated() {
        this.wasEaten = true;
    }

    public long getCreateTime() {
        return this.createTime;
    }

}
