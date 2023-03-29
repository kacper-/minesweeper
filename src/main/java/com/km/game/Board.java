package com.km.game;

import java.util.Random;

public class Board {
    public static final int OFFSET = 50;
    public static final int BLOCK = 20;
    public static final int BOMB_MAX = 99;
    public static final int COVERED_BOMB = 100;
    public static final int COVERED_EMPTY = 101;
    public static final int UNCOVERED_BOMB = 102;
    public static final int MARK = 103;
    private boolean goes;
    private boolean win;
    private final int x;
    private final int y;
    private final int[][] mines;

    public Board(int x, int y, boolean goes) {
        this.x = x;
        this.y = y;
        this.goes = goes;
        this.win = false;
        mines = new int[x][y];
    }

    public void seed() {
        for(int x=0;x<mines.length;x++) {
            for(int y=0;y<mines[0].length;y++) 
                mines[x][y] = new Random().nextInt(8) == 0 ? COVERED_BOMB : COVERED_EMPTY;
        }
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
        for(int x=0;x<mines.length;x++) {
            for(int y=0;y<mines[0].length;y++) {
                if(mines[x][y]==COVERED_BOMB)
                    mines[x][y]=UNCOVERED_BOMB;
                if(mines[x][y]==COVERED_EMPTY)
                    mines[x][y]=0;                    
            }
        }
    }

    public void checkResult() {
        int num = 0;
        for(int x=0;x<mines.length;x++) {
            for(int y=0;y<mines[0].length;y++) {
                if(mines[x][y]==COVERED_BOMB)
                    num++;
            }
        }
        if(num==0)
            finish(true);
    }

    public void setCell(int x, int y, int val) {
        mines[x][y] = val;
    }
}
