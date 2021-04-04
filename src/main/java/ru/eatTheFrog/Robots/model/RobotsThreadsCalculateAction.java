package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.Entities.IRobot;

import java.util.Spliterator;
import java.util.concurrent.RecursiveAction;

public class RobotsThreadsCalculateAction extends RecursiveAction {
    Spliterator<IRobot> robotSpliterator;
    public RobotsThreadsCalculateAction(Spliterator<IRobot> robotSpliterator) {
        this.robotSpliterator = robotSpliterator;
    }
    @Override
    protected void compute() {
        if(robotSpliterator.estimateSize() < 5) {
            robotSpliterator.forEachRemaining(IRobot::onModelUpdate);
        } else {
            var task1 = new RobotsThreadsCalculateAction(robotSpliterator);
            var task2 = new RobotsThreadsCalculateAction(robotSpliterator.trySplit());
            invokeAll(task1, task2);
            task1.join();
            task2.join();
        }
    }
}
