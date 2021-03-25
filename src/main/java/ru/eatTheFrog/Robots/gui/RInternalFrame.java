package ru.eatTheFrog.Robots.gui;

import javax.swing.*;

public abstract class RInternalFrame extends JInternalFrame implements IDisposable {
    public RInternalFrame(String name, boolean b, boolean b1, boolean b2, boolean b3) {
        super(name, b, b1, b2, b3);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
