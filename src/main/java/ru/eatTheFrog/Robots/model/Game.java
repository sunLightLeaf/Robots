package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.Entities.IDrawable;
import ru.eatTheFrog.Robots.model.Entities.Robot;
import ru.eatTheFrog.Robots.model.modules.RobotModule;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements IDdrawableIO {
    //private volatile int m_targetPositionX = 150;
    //private volatile int m_targetPositionY = 100;

    private volatile Point m_targetPosition = new Point(150, 100);

    private final Timer m_timer = initTimer();

    private double m_width;
    private double m_height;

    private ArrayList<Robot> m_robots;

    private RobotModule m_robotModule;

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public Game() {
        m_robotModule = new RobotModule(0.1, 0.001);
        m_robots = new ArrayList<>();
        m_robots.add(new Robot(100, 100));
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onModel();
            }
        }, 0, 5);
    }

    void onModel() {
        for (Robot r:
             m_robots) {
            m_robotModule.onModelUpdateEvent(r, m_targetPosition, m_width, m_height);
        }
    }

    @Override
    public void setTargetPosition(Point p) {
        m_targetPosition = p;
    }

    @Override
    public void setWidthHeight(double width, double height) {
        m_width = width;
        m_height = height;
    }


    @Override
    public ArrayList<IDrawable> getRobots() {
        ArrayList<IDrawable> tobor = new ArrayList<>();
        for (Robot r: m_robots)
            tobor.add(r);
        return tobor;
    }

    @Override
    public int getTargetX() {
        return m_targetPosition.x;
    }

    @Override
    public int getTargetY() {
        return m_targetPosition.y;
    }
}
