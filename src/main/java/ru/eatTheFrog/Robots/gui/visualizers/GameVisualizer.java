package ru.eatTheFrog.Robots.gui.visualizers;

import ru.eatTheFrog.Robots.model.Entities.IDrawableRobot;
import ru.eatTheFrog.Robots.model.GameUIBridge;
import ru.eatTheFrog.Robots.model.RobotSchemes.HuntyScheme;
import ru.eatTheFrog.Robots.model.RobotSchemes.RobotScheme;
import ru.eatTheFrog.Robots.model.RobotSchemes.VeggieScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import static ru.eatTheFrog.Robots.gui.visualizers.CommonVisualizer.drawFood;
import static ru.eatTheFrog.Robots.gui.visualizers.RobotVisualizer.drawRobot;

public class GameVisualizer extends JPanel {
    private final Timer m_timer = initTimer();

    private GameUIBridge m_game;
    private Iterable<IDrawableRobot> robots;

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public GameVisualizer(GameUIBridge game) {

        m_game = game;

        setDoubleBuffered(true);
    }
    public void startTimers() {
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onTimerEvent();
            }
        }, 0, 25);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getModifiersEx() == 0)
                    EventQueue.invokeLater(() -> addVeggie(e.getPoint()));
                else
                    EventQueue.invokeLater(() -> addHunty(e.getPoint()));

                repaint();
            }
        });
    }
    private void onTimerEvent() {
        onRedrawEvent();
        m_game.onModelEvent();
    }
    protected void addVeggie(Point p) {
        m_game.addRobotFromScheme(new VeggieScheme(p.x, p.y));
    }
    protected void addHunty(Point p) {
        m_game.addRobotFromScheme(new HuntyScheme(p.x, p.y));
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        m_game.setWidthHeight(this.getWidth(), this.getHeight());
        m_game.getRobotsDrawable().forEachRemaining(r -> drawRobot(g2d, r));
        m_game.getFood().forEachRemaining(r -> drawFood(g2d, r));

    }

}