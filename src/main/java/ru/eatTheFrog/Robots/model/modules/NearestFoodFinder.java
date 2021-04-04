package ru.eatTheFrog.Robots.model.modules;

import ru.eatTheFrog.Robots.model.Entities.EdibleType;
import ru.eatTheFrog.Robots.model.Entities.IEdible;
import ru.eatTheFrog.Robots.model.Entities.Target;
import ru.eatTheFrog.Robots.model.modules.distance.FindDistanceModule;

import java.util.Iterator;
import java.util.Optional;

public class NearestFoodFinder {

    public static <T extends IEdible, R extends IEdible> Optional<R> findNearestTarget(
            T from, Iterator<R> tos, int radius, EdibleType preferType) {
        R nearest = null;
        var minDistance = Double.MAX_VALUE;
        for (; tos.hasNext(); ) {

            var next = tos.next();
            if (next.getEdibleType() != preferType)
                continue;
            if (next == nearest)
                continue;
            var distance = FindDistanceModule.getDistance(from, next);
            if (distance > radius)
                continue;
            if (distance <minDistance) {
                minDistance = distance;
                nearest = next;
            }
        }
        return Optional.ofNullable(nearest);
    }
}
