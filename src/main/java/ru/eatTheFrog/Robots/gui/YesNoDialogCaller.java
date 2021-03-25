package ru.eatTheFrog.Robots.gui;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

public class YesNoDialogCaller {
    public static void internalFrameClosing(EventObject e, IClosable cont) {
        final JOptionPane pane = new JOptionPane("Closing this window will lead to it being closed.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        final JDialog dialog = new JDialog();
        dialog.setSize(400, 150);
        dialog.setContentPane(pane);
        pane.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getNewValue().equals(JOptionPane.YES_OPTION))
                    if (e.getNewValue().equals(JOptionPane.YES_OPTION)){
                        cont.onClosed();
                        cont.dispose();
                    }
                dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
            }
        });
        dialog.pack();
        dialog.setVisible(true);
    }
    public static void signOnJInternalFrame(RInternalFrame internalFrame) {
        internalFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        internalFrame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                YesNoDialogCaller.internalFrameClosing(e, internalFrame);
            }
        });
    }
    public static void internalFrameClosing(EventObject e, JInternalFrame cont, Action closeProperly) {
        final JOptionPane pane = new JOptionPane("Closing this window will lead to it being closed. You sure want to leave?",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        final JDialog dialog = new JDialog();
        dialog.setSize(400, 150);
        dialog.setContentPane(pane);
        pane.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getNewValue().equals(JOptionPane.YES_OPTION))
                    cont.dispose();
                dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));

            }
        });
        dialog.pack();
        dialog.setVisible(true);
    }
}
