package ru.eatTheFrog.Robots.model.static_modules;

import ru.eatTheFrog.Robots.model.Entities.Target;

import java.util.Vector;

public class TargetRotator {

    public static Target rotateTarget(Target targetToRotate, Target axis, double angle) {
        return Vector2D.fromTarget(targetToRotate)
                .minus(Vector2D.fromTarget(axis))
                .rotate(angle)
                .plus(Vector2D.fromTarget(axis))
                .asTarget();
    }
}
