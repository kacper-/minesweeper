package com.km;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private final com.km.painter.Painter painter;

    public MainFrame(com.km.painter.Painter painter, MouseListener mouseListener, KeyListener keyListener) {
        this.painter = painter;
        Display display = new Display();
        display.setFocusable(true);
        display.addMouseListener(mouseListener);
        display.addKeyListener(keyListener);
        painter.setCanvas(display);
        add(display);
    }

    void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    class Display extends JPanel {
        @Override
        public void paint(Graphics g) {
            painter.paint(g);
        }

        @Override
        protected void paintComponent(Graphics g) {
            painter.paint(g);
        }
    }
}

