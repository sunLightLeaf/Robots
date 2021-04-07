package ru.eatTheFrog.Robots.model.static_modules.Algebra;

public class LimitsApplyer {
    public static double applyLimit(double value, double minValue, double maxValue) {
        if (value > maxValue)
            return maxValue;
        if (value < minValue)
            return minValue;
        return value;
    }
}
