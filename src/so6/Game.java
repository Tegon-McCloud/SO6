package so6;

import processing.core.PGraphics;
import so6.base.level.Level;

import java.io.IOException;

public class Game {

    private static Game instance;

    public static Game get() {
        return instance;
    }

    private Level level;

    public Game() throws IOException {
        level = new Level();

    }

    public void draw(PGraphics g) {
        level.draw(g);
    }

    public static void main(String[] args) throws IOException {
        instance = new Game();
        Window.run();
        //Thread graphicsThread = new Thread(() -> , "graphics");
        //graphicsThread.start();
    }

}
