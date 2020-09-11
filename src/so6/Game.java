package so6;

import java.lang.Thread;

public class Game {

    Window window;

    public Game() {
        window = new Window();
        Thread graphicsThread = new Thread(window, "graphics");
        graphicsThread.start();
    }

    public static void main(String[] args) {
        System.out.println("hej");
        Game g = new Game();
    }

}
