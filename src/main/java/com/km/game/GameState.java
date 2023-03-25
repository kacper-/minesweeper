package com.km.game;

public class GameState {
    private int level = 1;
    private int score = 0;
    private Shape shape;
    private int shapeX = 0;
    private int shapeY = 0;
    private final Board board;

    public GameState(int maxX, int maxY) {
        board = new Board(maxX, maxY);
    }

    public int getLevel() {
        return level;
    }

    void nextLevel() {
        level++;
    }

    public int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    public Shape getShape() {
        return shape;
    }

    void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getShapeX() {
        return shapeX;
    }

    public void setShapeX(int shapeX) {
        this.shapeX = shapeX;
    }

    public int getShapeY() {
        return shapeY;
    }

    public void setShapeY(int shapeY) {
        this.shapeY = shapeY;
    }

    public int getMaxX() {
        return board.getX();
    }

    public int getMaxY() {
        return board.getY();
    }

    public Board getBoard() {
        return board;
    }
}
