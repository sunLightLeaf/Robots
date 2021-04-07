package ru.eatTheFrog.Robots.model.static_modules.Algebra;

public class MathFunctions {
    public static double relu(double value) {
        if (value < 0)
            return 0;
        return value;
    }
    public static double relu2(double value) {
        if (value < 0)
            return 0;
        if (value > 1)
            return 1;
        return value;
    }
}
