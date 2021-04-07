package ru.eatTheFrog.Robots.model.GameAndArbitration;

import ru.eatTheFrog.Robots.model.static_modules.REFLECTION_OnModelExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractOnModelUpdatingCore {
    // Этот класс идеален - трогать его запрещено! Можно только наследоваться.
    List<AbstractOnModelUpdatingCore> innerGoals;

    public AbstractOnModelUpdatingCore() {
        this.innerGoals = new ArrayList<>();
    }
    protected final void onModelUpdate() {
        REFLECTION_OnModelExecutor.executeOnModels(this);
        innerGoals.stream().filter(Objects::nonNull).forEach(AbstractOnModelUpdatingCore::onModelUpdate);
    }
}
