package ru.eatTheFrog.Robots.model.RobotsModules;

import ru.eatTheFrog.Robots.model.Configuration.RobotsConfig;
import ru.eatTheFrog.Robots.model.Entities.Food.Food;
import ru.eatTheFrog.Robots.model.Entities.Food.FoodType;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.RobotRaw;
import ru.eatTheFrog.Robots.model.GameAndArbitration.GameManagers.FoodManager;
import ru.eatTheFrog.Robots.model.static_modules.distance.FindDistanceModule;

import java.util.stream.Stream;

public class RobotFoodsSensor {
    private RobotRaw robot;
    private FoodManager foodsManager;
    private int seeRadius = RobotsConfig.FOODS_SCANNER_RADIUS;
    private FoodType preferedFood;

    public RobotFoodsSensor(RobotRaw robot, FoodManager foodsManager, FoodType preferedFood) {
        this.preferedFood = preferedFood;
        this.robot = robot;
        this.foodsManager = foodsManager;
    }
    public FoodType getPreferedFood() {
        return this.preferedFood;
    }

    public Stream<Food> getNearestFoods() {
        return foodsManager.getFood()
                .filter(x -> FindDistanceModule.getDistance(x, robot) < seeRadius)
                .filter(food -> robot.getRobotMouth().map(m -> m.calculateEnergyFromFood(food) > 0).orElse(false))
                .sorted((x, y) -> (int) (FindDistanceModule.getDistance(robot, x) - FindDistanceModule.getDistance(robot, y)));
    }
}
