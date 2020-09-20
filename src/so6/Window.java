package so6;

import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import so6.levels.editor.LevelEditor;
import so6.ui.Menu;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Window extends PApplet {

    private static Window wnd;

    public static Window getWnd() {
        return wnd;
    }

    private PGraphics g;
    private Menu menu;
    private Game game;
    private LevelEditor editor;


    private PImage controls;
    private boolean showControls;

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        wnd = this;
        g = createGraphics(width, height);

        try {
            menu = new Menu();
            controls = new PImage(ImageIO.read(new File("./resources/menu/controls.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        showControls = false;

    }

    @Override
    public void draw() {

        g.beginDraw();

        if(showControls){
            g.imageMode(PConstants.CORNER);
            g.image(controls, 0, 0);
        }else {
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
        }

        g.endDraw();

        image(g, 0, 0);
    }

    public void createGame(String levelName) throws IOException {
        game = new Game(levelName);
    }

    public void createEditor() throws IOException {
        editor = new LevelEditor();
    }

    public void showControls() {
        showControls = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKey() == PConstants.ESC) {
            key = 0;
        }

        if(showControls){
            if(e.getKey() == 'h' || e.getKey() == 'H' || e.getKey() == PConstants.ESC){
                showControls = false;
            }
            return;
        } else if(e.getKey() == 'h' || e.getKey() == 'H'){
            showControls = true;
        }

        switch (menu.getState()) {
            case IN_GAME:
                game.keyPressed(menu, e);
                break;
            case IN_EDITOR:
                editor.keyPressed(menu, e);
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (menu.getState()) {
            case IN_MENU:
                menu.mousePressed(this, e);
                break;
            case IN_GAME:
                game.mousePressed(e);
                break;
            case IN_EDITOR:
                editor.mousePressed(e);
                break;
        }
        System.out.println(e.getX()+ " "+" "+e.getY());
    }

    public Menu getMenu() {
        return menu;
    }

    public static void run() {
        PApplet.main("so6.Window");
    }

}
