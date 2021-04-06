package ru.eatTheFrog.Robots.model.Tasks;

import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.ILivingRobot;
import ru.eatTheFrog.Robots.model.Entities.Target;

import static ru.eatTheFrog.Robots.model.RoboMath.*;

public class MovingToMouseTarget {
    protected ILivingRobot robot;
    public void selectTargetIfDisposed() {

    }
    public MovingToMouseTarget(ILivingRobot robot) {
        this.robot = robot;
    }
    private double getAngularVelocity(double angleToTarget, ILivingRobot robot) {
        double angularAcceleration;

        angularAcceleration = compareAngles(robot.getDirection(), angleToTarget) * robot.getRotationAcceleration();
        if (Math.abs(robot.getDirection() - angleToTarget) < 0.1)
            return 0;
        return angularAcceleration;
    }


    void moveRobot(double angularVelocity, double duration, ILivingRobot robot) {
        angularVelocity = applyLimits(angularVelocity, -robot.getRotationAcceleration(), robot.getRotationAcceleration());
        robot.speedUp();
        robot.applyRotateAcceleration(angularVelocity);
        if (angularVelocity == 0)
            robot.applyRotateAcceleration(-robot.getRotationSpeed());
        robot.setDirection(robot.getDirection() + robot.getRotationSpeed());
        double newDirection = asNormalizedRadians(robot.getDirection() + angularVelocity * duration);
        robot.setDirection(newDirection);
    }
    public void customAction() {

    }
    public void moveTo(Target target) {

        this.selectTargetIfDisposed();
        this.customAction();


        double distance = distance(target.getX(), target.getY(),
                robot.getX(), robot.getY());
        if (distance < 0.5) {
            return;
        }
        double angleToTarget = angleTo(robot.getX(), robot.getY(), target.getX(), target.getY());
        double angularVelocity = getAngularVelocity(angleToTarget, robot);

        moveRobot(angularVelocity, 10, robot);
        return;
    }

}
