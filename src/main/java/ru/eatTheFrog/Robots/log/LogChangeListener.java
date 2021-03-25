package ru.eatTheFrog.Robots.log;

public interface LogChangeListener
{
    public void onLogChanged();
    public boolean isListenerClosed();
}
