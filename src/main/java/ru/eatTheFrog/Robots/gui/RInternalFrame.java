package ru.eatTheFrog.Robots.gui;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class RInternalFrame extends JInternalFrame {
    RInternalFrame(){
        super();
        closing();
    }

    public RInternalFrame(String str, boolean b, boolean b1, boolean b2, boolean b3) {
        super(str, b, b1, b2, b3);
        closing();
    }

    private void closing(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                YesNoDialogCaller.internalFrameClosing(e);
            }
        });
    }
}
