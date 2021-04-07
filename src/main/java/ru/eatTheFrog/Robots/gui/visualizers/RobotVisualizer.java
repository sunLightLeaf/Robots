package ru.eatTheFrog.Robots.gui.visualizers;

import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IDrawableRobot;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static ru.eatTheFrog.Robots.gui.visualizers.CommonVisualizer.drawOval;
import static ru.eatTheFrog.Robots.gui.visualizers.CommonVisualizer.fillOval;
import static ru.eatTheFrog.Robots.model.RoboMath.round;

public class RobotVisualizer {

    public static void drawRobot(Graphics2D g, IDrawableRobot robot) {
        int robotCenterX = round(robot.getX());
        int robotCenterY = round(robot.getY());
        AffineTransform t = AffineTransform.getRotateInstance(robot.getDirection(), robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(robot.getColor());
        fillOval(g, robotCenterX, robotCenterY, (int)robot.getLength(), (int)robot.getThickness());
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, (int)robot.getLength(), (int)robot.getThickness());
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX + 10, robotCenterY, 5, 5);
    }
}
