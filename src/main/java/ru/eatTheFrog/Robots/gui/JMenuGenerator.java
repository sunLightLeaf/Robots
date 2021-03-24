package ru.eatTheFrog.Robots.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JMenuGenerator {
    public JMenuBar getJMenuBar(JMenu... menus) {
        JMenuBar bar = new JMenuBar();
        for (JMenu m : menus)
            bar.add(m);
        return bar;
    }


    private JMenuItem m_getMenuItem(String name, int keyMnemonic, String contextDescription, ActionListener actionListener) {
        JMenuItem item = (keyMnemonic == -1) ? new JMenuItem(name) : new JMenuItem(name, keyMnemonic);
        if (contextDescription != null)
            item.getAccessibleContext().setAccessibleDescription(contextDescription);
        item.addActionListener(actionListener);
        return item;
    }

    private JMenu m_getMenu(String name, int keyMnemonic, String contextDescription, JMenuItem... items) {
        JMenu menu = (JMenu) m_getMenuItem(name, keyMnemonic, contextDescription, (event) -> {
        });
        for (JMenuItem i : items)
            menu.add(i);
        return menu;
    }

    public JMenuItem getMenuItem(String name, ActionListener actionListener) {
        return m_getMenuItem(name, -1, null, actionListener);
    }

    public JMenu getJMenu(String name, JMenuItem... items) {
        return m_getMenu(name, -1, null, items);
    }

    public JMenuItem getMenuItem(String name, String contextDescription, ActionListener actionListener) {
        return m_getMenuItem(name, -1, contextDescription, actionListener);
    }

    public JMenu getJMenu(String name, String contextDescription, JMenuItem... items) {
        return m_getMenu(name, -1, contextDescription, items);

    }

    public JMenuItem getMenuItem(String name, int keyMnemonic, ActionListener actionListener) {
        return m_getMenuItem(name, keyMnemonic, null, actionListener);
    }

    public JMenu getJMenu(String name, int keyMnemonic, JMenuItem... items) {
        return m_getMenu(name, keyMnemonic, null, items);
    }

    public JMenuItem getMenuItem(String name, int keyMnemonic, String contextDescription, ActionListener actionListener) {
        return m_getMenuItem(name, keyMnemonic, contextDescription, actionListener);
    }

    public JMenu getJMenu(String name, int keyMnemonic, String contextDescription, JMenuItem... items) {
        return m_getMenu(name, keyMnemonic, contextDescription, items);

    }
}
