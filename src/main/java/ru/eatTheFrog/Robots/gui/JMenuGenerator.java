package ru.eatTheFrog.Robots.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JMenuGenerator {
    public static JMenuBar getJMenuBar(JMenu... menus) {
        JMenuBar bar = new JMenuBar();
        for (JMenu m : menus)
            bar.add(m);
        return bar;
    }


    static private JMenuItem m_getMenuItem(String name, int keyMnemonic, String contextDescription, ActionListener actionListener) {
        JMenuItem item = (keyMnemonic == -1) ? new JMenuItem(name) : new JMenuItem(name, keyMnemonic);
        if (contextDescription != null)
            item.getAccessibleContext().setAccessibleDescription(contextDescription);
        item.addActionListener(actionListener);
        return item;
    }

    static private JMenu m_getMenu(String name, int keyMnemonic, String contextDescription, JMenuItem... items) {
        JMenu menu = new JMenu(name);
        if (keyMnemonic != -1)
            menu.setMnemonic(keyMnemonic);
        if (contextDescription != null)
            menu.getAccessibleContext().setAccessibleDescription(contextDescription);
        for (JMenuItem i : items)
            menu.add(i);
        return menu;
    }

    static public JMenuItem getMenuItem(String name, ActionListener actionListener) {
        return m_getMenuItem(name, -1, null, actionListener);
    }

    static public JMenu getJMenu(String name, JMenuItem... items) {
        return m_getMenu(name, -1, null, items);
    }

    public JMenuItem getMenuItem(String name, String contextDescription, ActionListener actionListener) {
        return m_getMenuItem(name, -1, contextDescription, actionListener);
    }

    static public JMenu getJMenu(String name, String contextDescription, JMenuItem... items) {
        return m_getMenu(name, -1, contextDescription, items);

    }

    static public JMenuItem getMenuItem(String name, int keyMnemonic, ActionListener actionListener) {
        return m_getMenuItem(name, keyMnemonic, null, actionListener);
    }

    static public JMenu getJMenu(String name, int keyMnemonic, JMenuItem... items) {
        return m_getMenu(name, keyMnemonic, null, items);
    }

    static public JMenuItem getMenuItem(String name, int keyMnemonic, String contextDescription, ActionListener actionListener) {
        return m_getMenuItem(name, keyMnemonic, contextDescription, actionListener);
    }

    static public JMenu getJMenu(String name, int keyMnemonic, String contextDescription, JMenuItem... items) {
        return m_getMenu(name, keyMnemonic, contextDescription, items);

    }
}
