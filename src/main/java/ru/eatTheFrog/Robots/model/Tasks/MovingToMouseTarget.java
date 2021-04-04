package ru.eatTheFrog.Robots.model.Tasks;

import ru.eatTheFrog.Robots.model.Entities.IRobot;
import ru.eatTheFrog.Robots.model.Entities.RobotRaw;
import ru.eatTheFrog.Robots.model.Entities.Target;

import java.util.Optional;
import java.util.function.Function;

import static ru.eatTheFrog.Robots.model.RoboMath.*;

public class MovingToMouseTarget implements RobotTask {
    protected IRobot robot;
    public void selectTargetIfDisposed() {

    }
    protected Optional<Target> getTarget() {
        return Optional.empty();
    }
    public MovingToMouseTarget(IRobot robot) {
        this.robot = robot;
    }
    private double getAngularVelocity(double angleToTarget, IRobot robot) {
        double angularVelocity;

        angularVelocity = compareAngles(robot.getDirection(), angleToTarget) * robot.getAngularVelocity();
        return angularVelocity;
    }
    private double calculateDeltaMove(double pos, double velocity, double angularVelocity, double duration, double m_robotDirection,
                                      Function<Double, Double> trFunc1, Function<Double, Double> trFunc2) {
        return (angularVelocity == 0)
                ? pos + velocity * duration * trFunc1.apply(m_robotDirection)

                : pos + velocity / angularVelocity *
                (trFunc2.apply(m_robotDirection + angularVelocity * duration) -
                        trFunc2.apply(m_robotDirection));

    }


    void moveRobot(double velocity, double angularVelocity, double duration, IRobot robot) {
        velocity = applyLimits(velocity, 0, robot.getVelocity());
        angularVelocity = applyLimits(angularVelocity, -robot.getAngularVelocity(), robot.getAngularVelocity());
        double newX = calculateDeltaMove(robot.getX(), velocity, angularVelocity, duration, robot.getDirection(), Math::cos, Math::sin);
        double newY = calculateDeltaMove(robot.getY(), -velocity, angularVelocity, duration, robot.getDirection(), Math::sin, Math::cos);
        robot.setX(newX);
        robot.setY(newY);
        double newDirection = asNormalizedRadians(robot.getDirection() + angularVelocity * duration);
        robot.setDirection(newDirection);
    }
    public void customAction() {

    }
    public void onModelUpdate() {
        this.selectTargetIfDisposed();
        this.customAction();
        Target target = null;
        if (this.getTarget().isPresent())
            target = getTarget().get();
        else
            return;

        double distance = distance(target.getX(), target.getY(),
                robot.getX(), robot.getY());
        if (distance < 0.5) {
            return;
        }
        double velocity = robot.getVelocity();
        double angleToTarget = angleTo(robot.getX(), robot.getY(), target.getX(), target.getY());
        double angularVelocity = getAngularVelocity(angleToTarget, robot);

        moveRobot(velocity, angularVelocity, 10, robot);
    }

}
