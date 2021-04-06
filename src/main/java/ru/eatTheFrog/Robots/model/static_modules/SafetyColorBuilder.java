package ru.eatTheFrog.Robots.model.static_modules;

import ru.eatTheFrog.Robots.log.Logger;
import ru.eatTheFrog.Robots.model.static_modules.Algebra.LimitsApplyer;

import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SafetyColorBuilder {
    public static Color buildColorFromRGB(double red, double green, double blue) {
        var colors = Stream.of(red, green, blue)
                .peek(x -> {if(x < 0 || x > 255) Logger.error(
                        "Цвет вышел за допустимые рамки");})
                .map(x -> LimitsApplyer.applyLimit(x, 0, 255))
                .map(x -> (int)x.doubleValue())
                .collect(Collectors.toList());
        //Метод конечно может упасть с ArrayIndexOutOfBounds, но должно произойти
        // что-то по-настоящему страшное. Хочется верить, что мы живём в правильном мире.
        return new Color(colors.get(0), colors.get(1), colors.get(2));
    }
}
