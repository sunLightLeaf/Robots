package ru.eatTheFrog.Robots.model.Entities;

public interface IEdible extends Target {
    public EdibleType getEdibleType();
    public void makeEated();
}

