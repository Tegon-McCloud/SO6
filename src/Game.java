public class Game {

    Window window;

    public Game() {
        window = new Window();
        Thread graphicsThread = new Thread(window, "graphics");
        graphicsThread.start();
    }

    public static void main(String[] args) {
        Game g = new Game();


    }

}
