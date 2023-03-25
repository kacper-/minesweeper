package com.km;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private final com.km.painter.Painter painter;

    public MainFrame(com.km.painter.Painter painter, KeyListener keyListener) {
        this.painter = painter;
        Display display = new Display();
        painter.setCanvas(display);
        add(display);
        addKeyListener(keyListener);
    }

    void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    class Display extends Canvas {
        @Override
        public void paint(Graphics g) {
            painter.paint(g);
        }
    }
}

