package ru.eatTheFrog.Robots.gui;

import javax.swing.*;

public class RInternalFrame extends JInternalFrame implements IClosable {
    public RInternalFrame(String name, boolean b, boolean b1, boolean b2, boolean b3) {
        super(name, b, b1, b2, b3);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void onClosed() {

    }
}
