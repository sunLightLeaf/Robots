package ru.eatTheFrog.Robots.model.modules;

import ru.eatTheFrog.Robots.model.Entities.Robot;

import java.awt.*;
import java.util.function.Function;

import static ru.eatTheFrog.Robots.model.RoboMath.*;

public class RobotModule {

    private double m_maxVelocity;
    private double m_maxAngularVelocity;

    public RobotModule(double maxVelocity, double maxAngularVelocity) {
        m_maxVelocity = maxVelocity;
        m_maxAngularVelocity = maxAngularVelocity;
    }

    private double getAngularVelocity(double angleToTarget, Robot robot) {
        double angularVelocity;

        angularVelocity = compareAngles(robot.getDirection(), angleToTarget) * m_maxAngularVelocity;
        return angularVelocity;
    }

    public void onModelUpdateEvent(Robot robot, Point targetPosition, double width, double height) {
        double distance = distance(targetPosition.x, targetPosition.y,
                robot.getX(), robot.getY());
        if (distance < 0.5) {
            return;
        }
        double velocity = m_maxVelocity;
        double angleToTarget = angleTo(robot.getX(), robot.getY(), targetPosition.x, targetPosition.y);
        double angularVelocity = getAngularVelocity(angleToTarget, robot);

        moveRobot(velocity, angularVelocity, 10, robot, width, height);
    }

    private double calculateDeltaMove(double pos, double velocity, double angularVelocity, double duration, double m_robotDirection,
                                      Function<Double, Double> trFunc1, Function<Double, Double> trFunc2) {
        return (angularVelocity == 0)
                ? pos + velocity * duration * trFunc1.apply(m_robotDirection)

                : pos + velocity / angularVelocity *
                (trFunc2.apply(m_robotDirection + angularVelocity * duration) -
                        trFunc2.apply(m_robotDirection));

    }

    void moveRobot(double velocity, double angularVelocity, double duration, Robot robot, double width, double height) {
        velocity = applyLimits(velocity, 0, m_maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -m_maxAngularVelocity, m_maxAngularVelocity);
        double newX = calculateDeltaMove(robot.getX(), velocity, angularVelocity, duration, robot.getDirection(), Math::cos, Math::sin);
        double newY = calculateDeltaMove(robot.getY(), -velocity, angularVelocity, duration, robot.getDirection(), Math::sin, Math::cos);
        int h = (int) height;
        int w = (int) width;
        robot.setX(newX);
        robot.setY(newY);
        robotMod(h, w, robot);
        double newDirection = asNormalizedRadians(robot.getDirection() + angularVelocity * duration);
        robot.setDirection(newDirection);
    }


    void robotMod(int windowHeight, int windowWidth, Robot robot) {
        if (robot.getX() < 0)
            robot.setX(windowWidth);
        if (robot.getX() > windowWidth)
            robot.setX(0);
        if (robot.getY() < 0)
            robot.setY(windowHeight);
        if (robot.getY() > windowHeight)
            robot.setY(0);

    }
}
