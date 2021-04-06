package ru.eatTheFrog.Robots.model.static_modules;

import static ru.eatTheFrog.Robots.model.static_modules.Algebra.MutationDistributor.getRandomDistribution;

import java.awt.*;

public class ColorShifter {
    public static int colorLimit(double color) {
        if (color >255)
            return 255;
        if (color < 0)
            return 0;
        return (int)color;
    }
    public static Color shiftColor(Color color, double mediocrityRate) {
        var red = color.getRed() + getRandomDistribution(mediocrityRate)*1000;
        var green = color.getGreen() + getRandomDistribution(mediocrityRate)*1000;
        var blue = color.getBlue() + getRandomDistribution(mediocrityRate)*1000;
        return new Color(colorLimit(red), colorLimit(green), colorLimit(blue));
    }
}
