package com.km.game;

public class Board {
    private final int x;
    private final int y;
    private final boolean[][] mines;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        mines = new boolean[x][y];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean[][] getMines() {
        return mines;
    }
}
