package ru.eatTheFrog.Robots.sugar;

public class FrequencyExecutor {
    private int counter = 0;
    private int frequency;
    private Runnable lambdaz;
    public FrequencyExecutor(Runnable runnable, int frequency) {
        this.frequency = frequency;
        this.lambdaz = runnable;
    }
    public void executeFrequency() {
        counter++;
        if (counter > frequency) {
            counter = 0;
            lambdaz.run();
        }
    }
}
