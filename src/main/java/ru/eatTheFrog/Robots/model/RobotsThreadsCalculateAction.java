package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IRobot;
import ru.eatTheFrog.Robots.model.GameAndArbitration.ISizedGame;

import java.util.Spliterator;
import java.util.concurrent.RecursiveAction;

public class RobotsThreadsCalculateAction extends RecursiveAction {
    Spliterator<IRobot> robotSpliterator;
    private ISizedGame sizedGame;
    public RobotsThreadsCalculateAction(Spliterator<IRobot> robotSpliterator,
                                        ISizedGame sizedGame) {
        this.robotSpliterator = robotSpliterator;
        this.sizedGame = sizedGame;
    }

    private void applySpeed(IRobot robot) {
        robot.applyShift(robot.getVelocity());
    }

    public void verifyBorders(IRobot robot) {
        if (robot.getX() < 0)
            robot.setX(sizedGame.getWindowWidth());
        if (robot.getX() > sizedGame.getWindowWidth())
            robot.setX(0);
        if (robot.getY() < 0)
            robot.setY(sizedGame.getWindowHeight());
        if (robot.getY() > sizedGame.getWindowHeight())
            robot.setY(0);
    }
    private void applyAge(IRobot robot) {
        robot.applyAging();
    }
    @Override
    protected void compute() {
        if(robotSpliterator.estimateSize() < 5) {
            robotSpliterator.forEachRemaining((robot) -> {
                verifyBorders(robot);
                applySpeed(robot);
                robot.applyPhysics();
                robot.onModelUpdate();
                applyAge(robot);
            });
        } else {
            var task1 = new RobotsThreadsCalculateAction(robotSpliterator, sizedGame);
            var task2 = new RobotsThreadsCalculateAction(robotSpliterator.trySplit(), sizedGame);
            invokeAll(task1, task2);
            task1.join();
            task2.join();
        }
    }
}
