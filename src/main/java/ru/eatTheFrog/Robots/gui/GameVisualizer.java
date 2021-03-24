package ru.eatTheFrog.Robots.gui;

import ru.eatTheFrog.Robots.model.Entities.IDrawable;
import ru.eatTheFrog.Robots.model.IDdrawableIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import static ru.eatTheFrog.Robots.gui.visualizers.CommonVisualizer.drawTarget;
import static ru.eatTheFrog.Robots.gui.visualizers.RobotVisualizer.drawRobot;

public class GameVisualizer extends JPanel {
    private final Timer m_timer = initTimer();

    private IDdrawableIO m_game;
    private Iterable<IDrawable> robots;

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public GameVisualizer(IDdrawableIO game) {
        m_game = game;
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 25);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setTargetPosition(e.getPoint());
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    protected void setTargetPosition(Point p) {
        m_game.setTargetPosition(p);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        robots = m_game.getRobots();
        m_game.setWidthHeight(this.getWidth(), this.getHeight());
        if (robots != null) {
            for (IDrawable robot :
                    robots) {
                drawRobot(g2d, robot);
            }
            drawTarget(g2d, m_game.getTargetX(), m_game.getTargetY());
        }
    }

}