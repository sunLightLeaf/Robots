package ru.eatTheFrog.Robots.model.GameAndArbitration.GameManagers;

import ru.eatTheFrog.Robots.model.GameAndArbitration.AbstractOnModelUpdatingCore;
import ru.eatTheFrog.Robots.model.static_modules.REFLECTION_OnModelExecutor.OnModelAction;

public class TheClocks extends AbstractOnModelUpdatingCore implements IClock {
    long time = 0;
    @OnModelAction
    public void affectWithPureTime() {
        time++;
    }
    public long getTime() {
        return this.time;
    }
}
