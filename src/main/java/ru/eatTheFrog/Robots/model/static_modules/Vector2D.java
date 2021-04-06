package ru.eatTheFrog.Robots.model.static_modules;

import ru.eatTheFrog.Robots.model.Entities.FictitiousTarget;
import ru.eatTheFrog.Robots.model.Entities.Target;

public class Vector2D {
    private final double x;
    private final double y;
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public static Vector2D fromTarget(Target target) {
        return new Vector2D(target.getX(), target.getY());
    }
    public Target asTarget() {
        return new FictitiousTarget(x, y);
    }
    public Vector2D minus(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }
    public Vector2D plus(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    public Vector2D rotate(double angle) {
        var xnew =x*Math.cos(angle) - y*Math.sin(angle);
        var ynew = x* Math.sin(angle)+y*Math.cos(angle);
        return new Vector2D(xnew, ynew);
    }
}
