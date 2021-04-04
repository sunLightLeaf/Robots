package ru.eatTheFrog.Robots.model.Entities;

import ru.eatTheFrog.Robots.model.Entities.RobotsClasses.Organs.RobotMouth;

import java.util.Optional;

public interface IEater extends Target{
    public Optional<RobotMouth> getRobotMouth();
}
