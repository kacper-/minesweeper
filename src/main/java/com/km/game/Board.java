package com.km.game;

import java.util.Random;

public class Board {
    public static final int OFFSET = 25;
    public static final int BLOCK = 25;
    public static final int COVERED_BOMB = 64;
    public static final int COVERED_EMPTY = 16;
    public static final int UNCOVERED_BOMB = 128;
    public static final int MARK = 32;
    public static final int BOMB_MAX = COVERED_EMPTY;
    private boolean goes;
    private boolean win;
    private final int x;
    private final int y;
    private final int[][] mines;
    private int bombCount = 0;
    private int posX;
    private int posY;
    private String message = "";

    public Board(int x, int y, boolean goes) {
        this.x = x;
        this.y = y;
        this.goes = goes;
        this.win = false;
        mines = new int[x][y];
        posX = x / 2;
        posY = y / 2;
    }

    public int getBombCount() {
        return bombCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void seed(int difficulty) {
        goes = true;
        bombCount = 0;
        for (int x = 0; x < mines.length; x++) {
            for (int y = 0; y < mines[0].length; y++) {
                mines[x][y] = new Random().nextInt(difficulty) == 0 ? COVERED_BOMB : COVERED_EMPTY;
                if (mines[x][y] == COVERED_BOMB)
                    bombCount++;
            }
        }
        setMessage("Created " + bombCount + " bombs");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMines() {
        return mines;
    }

    public boolean isGoing() {
        return goes;
    }

    public boolean isWin() {
        return win;
    }

    public void finish(boolean win) {
        goes = false;
        this.win = win;
        for (int x = 0; x < mines.length; x++) {
            for (int y = 0; y < mines[0].length; y++) {
                if (mines[x][y] == COVERED_BOMB)
                    mines[x][y] = UNCOVERED_BOMB;
                if (mines[x][y] == COVERED_EMPTY)
                    mines[x][y] = 0;
            }
        }
    }

    public void checkResult() {
        int num = 0;
        for (int x = 0; x < mines.length; x++) {
            for (int y = 0; y < mines[0].length; y++) {
                if ((mines[x][y] & MARK) == MARK && (mines[x][y] & COVERED_BOMB) == COVERED_BOMB)
                    num++;
            }
        }
        if (num == bombCount)
            finish(true);
    }

    public void setCell(int x, int y, int val) {
        mines[x][y] = val;
    }

    public void cursorUp() {
        posY--;
        if (posY < 0)
            posY = y - 1;
    }

    public void cursorDown() {
        posY++;
        if (posY >= y)
            posY = 0;
    }

    public void cursorRight() {
        posX++;
        if (posX >= x)
            posX = 0;
    }

    public void cursorLeft() {
        posX--;
        if (posX < 0)
            posX = x - 1;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPos(int x, int y) {
        posX = x;
        posY = y;
    }
}
