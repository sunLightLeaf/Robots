package ru.eatTheFrog.Robots.model.Configuration;

public class RobotsConfig {
    public static double HEALTH_FROM_SIZE_BONUS = 0.2;
    public static double ATTACK_PENALTY = 0;
    public static double DEFAULT_HEALTH = 50;
    public static double ATTACK_COOLDOWN_TICKS = 1;
    public static int FOODS_SCANNER_RADIUS = 100;
    public static int ROBOTS_SCANNER_RADIUS = 100;
    public static double MINIMUM_ENERGY_PERCENT_TO_REPRODUCE =0.9;
    public static int MIN_LENGTH = 5;
    public static int MIN_THICKNESS = 5;
    public static int MAX_LENGTH = 50;
    public static int MAX_THICKNESS = 50;
    public static int ENERGY_PER_THICKNESS = 2;
    public static int ROBOTS_FORCE_SPAWNING_COUNT = 0;
    public static double FATIQUE_RATE = 0.5;
    public static int MAX_ROBOTS_IN_GAME = 40;
    public static int MAXIMUM_AGE = 100; // of 10 ticks
    public static int BASE_ENERGY = 70;
    public static double FOOD_EATING_DIFFICULTY = 10; // ширина рта по сути
}
