package com.km.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.km.painter.GameStatePainter;

public class Game implements MouseListener {
    private static final int WIDTH = 12;
    private static final int HEIGHT = 18;
    private final GameStatePainter painter;
    private final Board board;

    public Game(GameStatePainter painter) {
        this.painter = painter;
        board = new Board(WIDTH, HEIGHT);
    }

    public void start() {
        painter.paint(board);
    }

    private void updateState() {
        painter.paint(board);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO update the state of the board after uncovering a field
        System.out.println("Clicked at ("+e.getX()+";"+e.getY()+")");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }



}
