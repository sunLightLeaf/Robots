package ru.eatTheFrog.Robots.gui;

import ru.eatTheFrog.Robots.gui.RMenu.RMenu;
import ru.eatTheFrog.Robots.gui.RMenu.RMenuBar;
import ru.eatTheFrog.Robots.gui.RMenu.RMenuItem;
import ru.eatTheFrog.Robots.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Что требуется сделать:
 * 1. Метод создания меню перегружен функционалом и трудно читается.
 * Следует разделить его на серию более простых методов (или вообще выделить отдельный класс).
 */
public class MainApplicationFrame extends JFrame implements IDisposable {
    private final JDesktopPane desktopPane = new JDesktopPane();


    public MainApplicationFrame() {
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 25;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        setContentPane(desktopPane);

        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(400, 400);
        addWindow(gameWindow);
        setJMenuBar(
                new RMenuBar(
                        new RMenu("Режим отображения", KeyEvent.VK_V, "Управление режимом отображения приложения",
                                new RMenuItem("Системная схема", KeyEvent.VK_S, (event) -> {
                                    setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                    this.invalidate();
                                }),
                                new RMenuItem("Универсальная схема", KeyEvent.VK_S, (event) -> {
                                    setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                                    this.invalidate();
                                })
                        ),
                        new RMenu("Тесты", KeyEvent.VK_T,
                                "Тестовые команды", new RMenuItem("Сообщение в лог", KeyEvent.VK_S, (event) -> {
                            Logger.debug("Новая строка");
                        })),
                        new RMenu("Выход",
                                new RMenuItem("тут", (event) -> {YesNoDialogCaller.internalFrameClosing(event, this);})),
                        new RMenu("Скорость игры",
                                new RMenuItem("1x", (event) -> {gameWindow.setGameSpeed(1);}),
                                new RMenuItem("2x", (event) -> {gameWindow.setGameSpeed(2);}),
                                new RMenuItem("5x", (event) -> {gameWindow.setGameSpeed(5);}),
                                new RMenuItem("20x", (event) -> {gameWindow.setGameSpeed(20);}),
                                new RMenuItem("100x", (event) -> {gameWindow.setGameSpeed(100);})
                                ))
        );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    protected LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10, 10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        System.exit(0);
    }
}
