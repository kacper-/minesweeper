package com.km.painter;

import java.awt.*;
import com.km.game.Board;

public class Painter implements GameStatePainter {
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
        paintForeground(g);
    }

    private void paintForeground(Graphics g) {
        int mines[][] = board.getMines();
        for(int x=0;x<mines.length;x++) {
            for(int y=0;y<mines[0].length;y++)
                paintCell(g, mines, x, y);
        }
    }

    private void paintCell(Graphics g, int mines[][], int x, int y) {
        int tlX = Board.OFFSET + (Board.BLOCK * x);
        int tlY = Board.OFFSET + (Board.BLOCK * y);
        g.drawRect(tlX, tlY, Board.BLOCK, Board.BLOCK);   
        if (mines[x][y]==Board.UNCOVERED_BOMB)
            g.fillOval(tlX, tlY, Board.BLOCK, Board.BLOCK);             
        if (mines[x][y]<Board.BOMB_MAX)
            g.drawString(String.valueOf(mines[x][y]), tlX + Board.BLOCK/3, tlY + Board.BLOCK);   
        if (mines[x][y]==Board.MARK)
            g.drawString("?", tlX + Board.BLOCK/3, tlY + Board.BLOCK);              
    }

    private void paintBackground(Graphics g) {
        g.drawRect(Board.OFFSET, Board.OFFSET, board.getX() * Board.BLOCK, board.getY() * Board.BLOCK);
    }
}

