package com.km.painter;

import com.km.game.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPainter implements GameStatePainter {
    private static final int B_OFF_X = Board.BLOCK / 3;
    private static final int B_OFF_Y = (3 * Board.BLOCK) / 4;
    private JPanel canvas;
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setCanvas(JPanel canvas) {
        this.canvas = canvas;
    }

    public void paint() {
        canvas.repaint();
    }

    public void paint(Graphics g) {
        if (board == null) {
            return;
        }
        paintGameState(g);
    }

    private void paintGameState(Graphics g) {
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        paintBackground(g);
        paintForeground(g);
    }

    private void paintCursor(Graphics g, int posX, int posY) {
        g.setColor(Color.BLUE);
        g.drawRect(Board.OFFSET + (board.getPosX() * Board.BLOCK), Board.OFFSET + (board.getPosY() * Board.BLOCK), Board.BLOCK, Board.BLOCK);
        g.drawRect(Board.OFFSET + (board.getPosX() * Board.BLOCK) + 1, Board.OFFSET + (board.getPosY() * Board.BLOCK) + 1, Board.BLOCK - 2, Board.BLOCK - 2);
        g.drawRect(Board.OFFSET + (board.getPosX() * Board.BLOCK) + 2, Board.OFFSET + (board.getPosY() * Board.BLOCK) + 2, Board.BLOCK - 4, Board.BLOCK - 4);
        g.drawRect(Board.OFFSET + (board.getPosX() * Board.BLOCK) + 3, Board.OFFSET + (board.getPosY() * Board.BLOCK) + 3, Board.BLOCK - 6, Board.BLOCK - 6);
    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(Board.OFFSET, Board.OFFSET, board.getX() * Board.BLOCK, board.getY() * Board.BLOCK);
    }

    private void paintForeground(Graphics g) {
        int mines[][] = board.getMines();
        for (int x = 0; x < mines.length; x++) {
            for (int y = 0; y < mines[0].length; y++)
                paintCell(g, mines, x, y);
        }
        paintCursor(g, board.getPosX(), board.getPosY());
        paintMessage(g, board.getMessage());
    }

    private void paintMessage(Graphics g, String message) {
        g.setColor(Color.RED);
        g.drawString(message, 4 * Board.OFFSET, (board.getY() * Board.BLOCK) + 2 * Board.OFFSET);
    }

    private void paintCell(Graphics g, int mines[][], int x, int y) {
        int tlX = Board.OFFSET + (Board.BLOCK * x);
        int tlY = Board.OFFSET + (Board.BLOCK * y);
        paintCellFrame(g, tlX, tlY);
        if ((mines[x][y] & Board.UNCOVERED_BOMB) == Board.UNCOVERED_BOMB)
            paintCellBomb(g, tlX, tlY);
        if (mines[x][y] < Board.BOMB_MAX)
            paintCellNumber(g, tlX, tlY, mines[x][y]);
        if ((mines[x][y] & Board.MARK) == Board.MARK)
            paintCellMark(g, tlX, tlY);
    }

    private void paintCellFrame(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, Board.BLOCK, Board.BLOCK);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x + 1, y + 1, Board.BLOCK - 2, Board.BLOCK - 2);
    }

    private void paintCellBomb(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(x + 1, y + 1, Board.BLOCK - 2, Board.BLOCK - 2);
        g.setColor(Color.BLACK);
        g.fillOval(x + 4, y + 4, Board.BLOCK - 8, Board.BLOCK - 8);
    }

    private void paintCellNumber(Graphics g, int x, int y, int state) {
        g.setColor(Color.WHITE);
        g.fillRect(x + 1, y + 1, Board.BLOCK - 2, Board.BLOCK - 2);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(state), x + B_OFF_X, y + B_OFF_Y);
    }

    private void paintCellMark(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.drawString("?", x + B_OFF_X, y + B_OFF_Y);
    }
}

