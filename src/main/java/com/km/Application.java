package com.km;

import com.km.game.Game;
import com.km.painter.Painter;

import javax.swing.*;

public class Application {

    public static void main(String... args) {
        com.km.painter.Painter painter = new Painter();
        Game game = new Game(painter);
        MainFrame frame = new MainFrame(painter, game);
        SwingUtilities.invokeLater(frame::setUp);
        new Thread(game::start).start();
    }
}
