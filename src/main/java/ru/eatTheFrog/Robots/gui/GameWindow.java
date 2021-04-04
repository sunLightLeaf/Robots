package ru.eatTheFrog.Robots.gui;

import ru.eatTheFrog.Robots.gui.visualizers.GameVisualizer;
import ru.eatTheFrog.Robots.model.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends RInternalFrame implements IDisposable {
    private final GameVisualizer m_visualizer;

    public GameWindow() {
        super("Игровое поле", true, true, true, true);
        m_visualizer = new GameVisualizer(new Game());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        YesNoDialogCaller.signOnJInternalFrame(this);
        m_visualizer.startTimers();
    }
}
