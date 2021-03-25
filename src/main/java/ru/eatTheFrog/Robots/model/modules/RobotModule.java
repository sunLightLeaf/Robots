package ru.eatTheFrog.Robots.model.modules;

import ru.eatTheFrog.Robots.model.Entities.Robot;

import java.awt.*;

import static ru.eatTheFrog.Robots.model.RoboMath.*;

public class RobotModule {

    private double m_maxVelocity;
    private double m_maxAngularVelocity;

    public RobotModule(double maxVelocity, double maxAngularVelocity) {
        m_maxVelocity = maxVelocity;
        m_maxAngularVelocity = maxAngularVelocity;
    }

    public void onModelUpdateEvent(Robot robot, Point targetPosition, double width, double height) {
        double distance = distance(targetPosition.x, targetPosition.y,
                robot.getX(), robot.getY());
        if (distance < 0.5) {
            return;
        }
        double velocity = m_maxVelocity;
        double angleToTarget = angleTo(robot.getX(), robot.getY(), targetPosition.x, targetPosition.y);
        double angularVelocity = 0;
        if (angleToTarget > robot.getDirection()) {
            angularVelocity = m_maxAngularVelocity;
        }
        if (angleToTarget < robot.getDirection()) {
            angularVelocity = -m_maxAngularVelocity;
        }

        moveRobot(velocity, angularVelocity, 10, robot, width, height);
    }

    void moveRobot(double velocity, double angularVelocity, double duration, Robot robot, double width, double height) {
        velocity = applyLimits(velocity, 0, m_maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -m_maxAngularVelocity, m_maxAngularVelocity);
        double newX = robot.getX() + velocity / angularVelocity *
                (Math.sin(robot.getDirection() + angularVelocity * duration) -
                        Math.sin(robot.getDirection()));
        if (!Double.isFinite(newX)) {
            newX = robot.getX() + velocity * duration * Math.cos(robot.getDirection());
        }
        double newY = robot.getY() - velocity / angularVelocity *
                (Math.cos(robot.getDirection() + angularVelocity * duration) -
                        Math.cos(robot.getDirection()));
        if (!Double.isFinite(newY)) {
            newY = robot.getY() + velocity * duration * Math.sin(robot.getDirection());
        }
        int h = (int) height;
        int w = (int) width;
        robot.setX((w == 0) ? newX : newX % width);
        robot.setY((h == 0) ? newY : newY % height);
        double newDirection = asNormalizedRadians(robot.getDirection() + angularVelocity * duration);
        robot.setDirection(newDirection);
    }
}
