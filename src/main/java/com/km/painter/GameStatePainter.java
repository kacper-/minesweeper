package com.km.painter;

import com.km.game.Board;

public interface GameStatePainter {
    void paint();
    void setBoard(Board board);
}
