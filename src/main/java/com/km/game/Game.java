package com.km.game;

import com.km.painter.GameStatePainter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class Game implements KeyListener {
    private static final int TICK = 50;
    private static final int WIDTH = 12;
    private static final int HEIGHT = 18;
    private static final int BASE_PACE = 26;
    private final GameStatePainter painter;
    private final GameState gameState;
    private final Controller controller;
    private boolean pause = true;
    private int counter = 0;

    public Game(GameStatePainter painter) {
        this.painter = painter;
        gameState = new GameState(WIDTH, HEIGHT);
        controller = new Controller(gameState);
    }

    public void start() {
        setUp();
        while (true) {
            timeTick();
            if (pause)
                continue;
            updateState();
            painter.paint(gameState);
        }
    }

    private void updateState() {
        counter++;
        if (counter % getPace() == 0) {
            if (controller.lowerShape() == 0) {
                // TODO check if row may be removed from the board
                // TODO update score
                // TODO update level
                controller.createShape();
            }
        }
    }

    private void setUp() {
        controller.createShape();
    }

    private void timeTick() {
        try {
            Thread.sleep(TICK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getPace() {
        return BASE_PACE - gameState.getLevel();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case VK_SPACE:
                pause = !pause;
                break;
            case VK_A:
                controller.turnLeft();
                break;
            case VK_S:
                controller.turnRight();
                break;
            case VK_D:
                controller.putDown();
                break;
            case VK_RIGHT:
                controller.moveRight();
                break;
            case VK_LEFT:
                controller.moveLeft();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }
}
