package com.km;

import com.km.game.Game;
import com.km.painter.BoardPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private static final int SPACING = 50;
    private final Display display = new Display();
    private final BoardPainter painter = new BoardPainter();
    private final Game game = new Game(painter);
    JSlider difficulty = new JSlider(JSlider.HORIZONTAL, 2, 32, 8);

    public MainFrame() {
        setLayout(null);
        JButton start = new JButton("Start");
        JLabel description = new JLabel("Difficulty");
        description.setFocusable(false);
        difficulty.setFocusable(false);
        difficulty.setMajorTickSpacing(8);
        difficulty.setMinorTickSpacing(2);
        difficulty.setPaintTicks(true);
        difficulty.setPaintLabels(true);
        start.addActionListener(this::start);
        start.setFocusable(false);
        display.addMouseListener(game);
        painter.setCanvas(display);
        add(display);
        add(start);
        add(difficulty);
        add(description);
        display.setBounds(0, 0, HEIGHT, HEIGHT);
        start.setBounds(HEIGHT + SPACING, SPACING, SPACING * 2, SPACING / 2);
        description.setBounds(HEIGHT + SPACING + SPACING / 2, 2 * SPACING + SPACING / 2, SPACING * 2, SPACING / 2);
        difficulty.setBounds(HEIGHT, SPACING * 3, SPACING * 4, SPACING);
        defineBindings();
        game.start(difficulty.getValue());
    }

    private void defineBindings() {
        createKeyBinding(KeyEvent.VK_SPACE, "uncover", game::actionUncover);
        createKeyBinding(KeyEvent.VK_ENTER, "mark", game::actionMark);
        createKeyBinding(KeyEvent.VK_UP, "up", game::actionUp);
        createKeyBinding(KeyEvent.VK_DOWN, "down", game::actionDown);
        createKeyBinding(KeyEvent.VK_RIGHT, "right", game::actionRight);
        createKeyBinding(KeyEvent.VK_LEFT, "left", game::actionLeft);
    }

    private void createKeyBinding(int key, String name, Runnable action) {
        display.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, 0), name);
        display.getActionMap().put(name, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }

    void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    void start(ActionEvent e) {
        game.start(difficulty.getValue());
    }

    class Display extends JPanel {
        @Override
        public void paint(Graphics g) {
            painter.paint(g);
        }
    }
}

