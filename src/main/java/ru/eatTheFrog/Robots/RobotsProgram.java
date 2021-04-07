package ru.eatTheFrog.Robots;

import ru.eatTheFrog.Robots.gui.MainApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.lang.module.Configuration;

public class RobotsProgram
{
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        try {
        //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      SwingUtilities.invokeLater(() -> {
        MainApplicationFrame frame = new MainApplicationFrame();
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }}
