package com.km.painter;

import java.awt.*;

import com.km.game.Board;

public class Painter implements GameStatePainter {
    private static final int OFFSET = 50;
    private static final int BLOCK = 20;
    private Canvas canvas;
    private Board board;

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void paint(Board board) {
        this.board = board;
        canvas.repaint();
    }

    public void paint(Graphics g) {
        if (board == null) {
            return;
        }
        paintGameState(g);
    }

    private void paintGameState(Graphics g) {
        paintBackground(g);
    }

    private void paintBackground(Graphics g) {
        g.drawRect(OFFSET, OFFSET, board.getX() * BLOCK, board.getY() * BLOCK);
    }
}

