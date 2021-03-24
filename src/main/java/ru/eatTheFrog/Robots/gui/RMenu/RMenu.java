package ru.eatTheFrog.Robots.gui.RMenu;

import javax.swing.*;

public class RMenu extends JMenu {
    static private JMenu m_getMenu(RMenu menu, int keyMnemonic, String contextDescription, JMenuItem... items) {
        if (keyMnemonic != -1)
            menu.setMnemonic(keyMnemonic);
        if (contextDescription != null)
            menu.getAccessibleContext().setAccessibleDescription(contextDescription);
        for (JMenuItem i : items)
            menu.add(i);
        return menu;
    }

    public RMenu(String name, JMenuItem... items) {
        super(name);
        m_getMenu(this, -1, null, items);
    }

    public RMenu(String name, String contextDescription, JMenuItem... items) {
        super(name);
        m_getMenu(this, -1, contextDescription, items);

    }

    public RMenu(String name, int keyMnemonic, JMenuItem... items) {
        super(name);
        m_getMenu(this, keyMnemonic, null, items);
    }

    public RMenu(String name, int keyMnemonic, String contextDescription, JMenuItem... items) {
        super(name);
        m_getMenu(this, keyMnemonic, contextDescription, items);

    }
}
