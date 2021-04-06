package ru.eatTheFrog.Robots.model.static_modules;

import ru.eatTheFrog.Robots.model.Entities.FictitiousTarget;
import ru.eatTheFrog.Robots.model.Entities.Target;

public class MutableVector2D implements IReadonlyVector2D {
    public double x;
    public double y;
    public MutableVector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public static MutableVector2D fromTarget(Target target) {
        return new MutableVector2D(target.getX(), target.getY());
    }
    public Target asTarget() {
        return new FictitiousTarget(x, y);
    }
    public void minus(MutableVector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }
    public MutableVector2D plus(IReadonlyVector2D other) {
        this.x += other.getX();
        this.y += other.getY();
        return this;
    }
    public MutableVector2D multiply(double k) {
        this.x *= k;
        this.y *= k;
        return this;
    }
    public static MutableVector2D zero() {
        return new MutableVector2D(0, 0);
    }
    public static MutableVector2D fromAngle(double angle) {
        return new MutableVector2D(Math.cos(angle), Math.sin(angle));
    }
    public MutableVector2D copy() {
        return new MutableVector2D(this.x, this.y);
    }
    public static MutableVector2D getNormal() {
        return new MutableVector2D(1, 0);
    }
    public double length() {
        return Math.sqrt(this.x*this.x+this.y*this.y);
    }

    public MutableVector2D normalize() {
        this.multiply(1/this.length());
        return this;
    }
    public MutableVector2D rotate(double angle) {
        var xnew =x*Math.cos(angle) - y*Math.sin(angle);
        var ynew = x* Math.sin(angle)+y*Math.cos(angle);
        this.x = xnew;
        this.y = ynew;
        return this;
    }
    public MutableVector2D positivy() {
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
        return this;
    }
    public MutableVector2D demolish(double value) {
        this.minus(this.multiply(value));
        return this;
    }
    public static MutableVector2D from(IReadonlyVector2D vector) {
        return new MutableVector2D(vector.getX(), vector.getY());
    }
    @Override
    public double getX() {
        return this.x;
    }
    public Target toTarget() {
        return new FictitiousTarget(this.x, this.y);
    }

    @Override
    public double getY() {
        return this.y;
    }
}
