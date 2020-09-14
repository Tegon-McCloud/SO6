package so6;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import so6.levels.editor.Editor;
import so6.ui.Menu;

import java.io.IOException;


public class Window extends PApplet {

    private PGraphics g;
    private Menu menu;
    private Game game;
    private Editor editor;

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        g = createGraphics(width, height);

        menu = new Menu();


    }

    @Override
    public void draw() {

        g.beginDraw();

        switch (menu.getState()){
            case IN_MENU:
                menu.draw(g);
                break;
            case IN_GAME:
                game.draw(g);
                break;
            case IN_EDITOR:
                editor.draw(g);
                break;
        }

        g.endDraw();

        image(g, 0, 0);
    }

    public void createGame() throws IOException {
        game = new Game();
    }

    public void createEditor() throws IOException {
        editor = new Editor();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            menu.keyPressed(this, e);
        } catch (IOException e0) {
            e0.printStackTrace();
        }
    }

    public static void run() {
        PApplet.main("so6.Window");
    }

}
