package ru.eatTheFrog.Robots.model;

import ru.eatTheFrog.Robots.model.RobotSchemes.IRobotScheme;
import ru.eatTheFrog.Robots.sugar.FrequencyExecutor;
import ru.eatTheFrog.Robots.model.Entities.*;
import ru.eatTheFrog.Robots.model.RobotSchemes.RobotScheme;
import ru.eatTheFrog.Robots.model.modules.RobotModule;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Game implements GameUIBridge {
    //private volatile int m_targetPositionX = 150;
    //private volatile int m_targetPositionY = 100;
    ForkJoinPool pool = new ForkJoinPool();

    private Random random = new Random();
    private FrequencyExecutor foodRecounter = new FrequencyExecutor(this::recountFood, 20);
    private FrequencyExecutor foodSpawner = new FrequencyExecutor(this::spawnFoodRandom, 5);
    private volatile Food mainFood = new Food(150, 100);

    public RobotGameManipulator robotGameManipulator = new RobotGameManipulator(this);
    private double m_width;
    private double m_height;

    private List<IRobot> m_robots;
    private ArrayList<Food> foods;
    private double foodCount = 0;

    private RobotModule m_robotModule;


    final int FOOD_MAX = 33;
    public Game() {
        
        m_robotModule = new RobotModule(0.1, 0.001);
        m_robots = Collections.synchronizedList(new ArrayList<>());
        foods = new ArrayList<>();

    }
    public void addRobotFromScheme(IRobotScheme robotScheme) {
        m_robots.add(RobotBuilder.buildFromScheme(this, robotScheme));
    }

    @Override
    public Iterator<Target> getFood() {
        return this.foods.parallelStream().map(x -> (Target)x).iterator();
    }

    private void recountFood() {
        this.foodCount = foods.size();

    }
    public void verifyBorders(IRobot robot) {
        if (robot.getX() < 0)
            robot.setX(m_width);
        if (robot.getX() > m_width)
            robot.setX(0);
        if (robot.getY() < 0)
            robot.setY(m_height);
        if (robot.getY() > m_height)
            robot.setY(0);
    }
    void spawnFoodRandom() {
        if (foodCount >= FOOD_MAX)
            return;
        var randomFood = new Food((random.nextDouble() * Double.MAX_VALUE) % m_width,
                (random.nextDouble() * Double.MAX_VALUE) % m_height);
        this.foods.add(randomFood);
        this.foodCount += 1;
    }
    private boolean isWindowNullSized() {
        return this.m_width == 0 || this.m_height == 0;
    }
    public void onModelEvent() {
        removeInjuredBots();
        removeEatenFood();
        foodRecounter.executeFrequency();
        foodSpawner.executeFrequency();
        if (isWindowNullSized()) return;
        var task = new RobotsThreadsCalculateAction(this.m_robots.spliterator());
        pool.invoke(task);
        task.join();
    }
    public void removeInjuredBots() {
        this.m_robots.removeIf(IRobot::isInjured);
    }
    public void removeEatenFood() {
        this.foods.removeIf(Food::isDisposed);
    }

    @Override
    public void setTargetPosition(Point p) {
        this.mainFood.setX(p.x);
        this.mainFood.setY(p.y);
    }

    @Override
    public void setWidthHeight(double width, double height) {
        m_width = width;
        m_height = height;
    }


    @Override
    public Iterator<IDrawableRobot> getRobotsDrawable() {
        return m_robots.stream().map(x -> (IDrawableRobot)x).parallel().iterator();
    }

    public Iterator<IEdible> getEdibleOfType(EdibleType edibleType) {
        Supplier<Stream<IEdible>> getFoodStream = () -> this.foods.parallelStream().map(x -> (IEdible)x);
        Supplier<Stream<IEdible>> getRobotStream = () -> this.m_robots.parallelStream().map(x -> (IEdible)x);

        return switch (edibleType) {
            case VEGGIE -> getRobotStream.get().filter(x -> x.getEdibleType() == EdibleType.VEGGIE).iterator();
            case HUNTY -> getRobotStream.get().filter(x -> x.getEdibleType() == EdibleType.HUNTY).iterator();
            case FOOD -> getFoodStream.get().iterator();
            case EVERYTHING -> Stream.concat(getRobotStream.get(), getFoodStream.get()).parallel().iterator();
            case NOTHING -> null;
        };
    }

    @Override
    public double getTargetX() {
        return mainFood.getX();
    }
    public void removeFood(Food food) {
        foods.remove(food);
    }
    public void removeRobot(IRobot robot) {
        m_robots.remove(robot);
    }


    @Override
    public double getTargetY() {
        return mainFood.getY();
    }
}
