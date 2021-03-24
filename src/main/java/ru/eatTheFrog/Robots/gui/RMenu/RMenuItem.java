package ru.eatTheFrog.Robots.gui.RMenu;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RMenuItem extends JMenuItem {
    static private JMenuItem m_getMenuItem(RMenuItem item, int keyMnemonic, String contextDescription, ActionListener actionListener) {
        if (keyMnemonic != -1)
            item.setMnemonic(keyMnemonic);
        if (contextDescription != null)
            item.getAccessibleContext().setAccessibleDescription(contextDescription);
        item.addActionListener(actionListener);
        return item;
    }

    public RMenuItem(String name, ActionListener actionListener) {
        super(name);
        m_getMenuItem(this, -1, null, actionListener);
    }


    public RMenuItem(String name, String contextDescription, ActionListener actionListener) {
        super(name);
        m_getMenuItem(this, -1, contextDescription, actionListener);
    }


    public RMenuItem(String name, int keyMnemonic, ActionListener actionListener) {
        super(name);
        m_getMenuItem(this, keyMnemonic, null, actionListener);
    }


    public RMenuItem(String name, int keyMnemonic, String contextDescription, ActionListener actionListener) {
        super(name);
        m_getMenuItem(this, keyMnemonic, contextDescription, actionListener);
    }

}
