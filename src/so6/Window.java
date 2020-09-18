package so6;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import so6.levels.editor.LevelEditor;
import so6.ui.Menu;

import java.io.IOException;


public class Window extends PApplet {

    private PGraphics g;
    private Menu menu;
    private Game game;
    private LevelEditor editor;

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        g = createGraphics(width, height);

        try {
            menu = new Menu();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        editor = new LevelEditor();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (menu.getState()) {
            case IN_MENU:
                try {
                    menu.keyPressed(this, e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case IN_EDITOR:
                editor.keyPressed(e);
                break;
        }

        if(e.getKey() == PConstants.ESC) {
            key = 0;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (menu.getState()) {
            case IN_MENU:
                menu.mousePressed(this, e);
                break;
            case IN_EDITOR:
                editor.mousePressed(e);
                break;
        }

    }

    public static void run() {
        PApplet.main("so6.Window");
    }

}
