package ru.eatTheFrog.Robots.gui.visualizers;

import ru.eatTheFrog.Robots.model.Entities.Target;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class CommonVisualizer {

    public static void drawFood(Graphics2D g, Target food) {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        var xint = (int)food.getX();
        var yint = (int)food.getY();
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, xint, yint, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, xint, yint, 5, 5);
    }


    public static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    public static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

}
