package com.km.painter;

import com.km.game.GameState;

import java.awt.*;

public class Painter implements GameStatePainter {
    private static final int OFFSET = 50;
    private static final int BLOCK = 20;
    private Canvas canvas;
    private GameState gameState;

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void paint(GameState gameState) {
        this.gameState = gameState;
        canvas.repaint();
    }

    public void paint(Graphics g) {
        if (gameState == null || gameState.getLevel() == 0) {
            return;
        }
        paintGameState(g);
    }

    private void paintGameState(Graphics g) {
        paintBackground(g);
        paintShape(g);
    }

    private void paintBackground(Graphics g) {
        g.drawRect(OFFSET, OFFSET, gameState.getMaxX() * BLOCK, gameState.getMaxY() * BLOCK);
    }

    private void paintShape(Graphics g) {
        int[] data = gameState.getShape().getData();
        int posX, posY;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (data[x + y * 4] == 1) {
                    posX = (x + gameState.getShapeX()) * BLOCK;
                    posY = (y + gameState.getShapeY()) * BLOCK;
                    g.drawRect(OFFSET + posX, OFFSET + posY, BLOCK, BLOCK);
                }
            }
        }
    }
}

