package com.km.game;

import com.km.painter.GameStatePainter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class Game implements MouseListener {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private final GameStatePainter painter;
    private final Board board;

    public Game(GameStatePainter painter) {
        this.painter = painter;
        board = new Board(WIDTH, HEIGHT, true);
        painter.setBoard(board);
    }

    public void start(int difficulty) {
        board.seed(difficulty);
        updateState();
    }

    private void updateState() {
        board.checkResult();
        int[][] mines = board.getMines();
        int num = 0;
        if (!board.isGoing()) {
            for (int x = 0; x < mines.length; x++) {
                for (int y = 0; y < mines[0].length; y++) {
                    if ((mines[x][y] & Board.UNCOVERED_BOMB) == Board.UNCOVERED_BOMB)
                        num++;
                }
            }
            board.setMessage(board.isWin() ? "WON" : "LOST");
        } else {
            for (int x = 0; x < mines.length; x++) {
                for (int y = 0; y < mines[0].length; y++) {
                    if ((mines[x][y] & Board.MARK) == Board.MARK)
                        num++;
                }
            }
            board.setMessage("Marked " + num + " out of " + board.getBombCount() + " bombs");
        }
        painter.paint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Cell c = toCell(e);
        board.setPos(c.x, c.y);
        mark(c, e.getButton() == MouseEvent.BUTTON3);
    }

    private void calcCellValue(Cell cell) {
        if ((board.getMines()[cell.x][cell.y] & Board.COVERED_EMPTY) != Board.COVERED_EMPTY)
            return;
        Set<Cell> cells = getArea(cell);
        long num = cells.stream().filter(c -> (board.getMines()[c.x][c.y] & Board.COVERED_BOMB) == Board.COVERED_BOMB).count();
        board.setCell(cell.x, cell.y, (int) num);
        if (num == 0)
            cells.forEach(this::calcCellValue);
    }

    private Set<Cell> getArea(Cell c) {
        Set<Cell> area = new HashSet<>();
        Cell c1 = new Cell(c.x - 1, c.y - 1);
        if (!outside(c1))
            area.add(c1);
        Cell c2 = new Cell(c.x + 1, c.y);
        if (!outside(c2))
            area.add(c2);
        Cell c3 = new Cell(c.x + 1, c.y + 1);
        if (!outside(c3))
            area.add(c3);
        Cell c4 = new Cell(c.x + 1, c.y - 1);
        if (!outside(c4))
            area.add(c4);
        Cell c5 = new Cell(c.x - 1, c.y + 1);
        if (!outside(c5))
            area.add(c5);
        Cell c6 = new Cell(c.x, c.y - 1);
        if (!outside(c6))
            area.add(c6);
        Cell c7 = new Cell(c.x, c.y + 1);
        if (!outside(c7))
            area.add(c7);
        Cell c8 = new Cell(c.x - 1, c.y);
        if (!outside(c8))
            area.add(c8);

        return area;
    }

    private boolean outside(Cell c) {
        return c.x < 0 || c.y < 0 || c.x >= WIDTH || c.y >= HEIGHT;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private Cell toCell(MouseEvent e) {
        Cell c = new Cell();
        c.x = (e.getX() - Board.OFFSET) / Board.BLOCK;
        c.y = (e.getY() - Board.OFFSET) / Board.BLOCK;
        return c;
    }

    public void actionDown() {
        board.cursorDown();
        painter.paint();
    }

    public void actionUp() {
        board.cursorUp();
        painter.paint();
    }

    public void actionRight() {
        board.cursorRight();
        painter.paint();
    }

    public void actionLeft() {
        board.cursorLeft();
        painter.paint();
    }

    public void actionUncover() {
        mark(new Cell(board.getPosX(), board.getPosY()), false);
    }

    public void actionMark() {
        mark(new Cell(board.getPosX(), board.getPosY()), true);
    }

    private class Cell {
        int x;
        int y;

        Cell() {
        }

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void mark(Cell c, boolean mark) {
        if (board.isGoing()) {
            if (outside(c))
                return;
            int[][] mines = board.getMines();
            if (mark) {
                if ((mines[c.x][c.y] & Board.MARK) == Board.MARK)
                    board.setCell(c.x, c.y, mines[c.x][c.y] - Board.MARK);
                else
                    board.setCell(c.x, c.y, mines[c.x][c.y] + Board.MARK);
            } else {
                if (mines[c.x][c.y] == Board.COVERED_BOMB)
                    board.finish(false);
                if (mines[c.x][c.y] == Board.COVERED_EMPTY)
                    calcCellValue(c);
            }
            updateState();
        }
    }
}
