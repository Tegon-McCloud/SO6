package so6.ui;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import so6.Window;
import so6.util.IntVec2;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Menu {

    private State state;

    private PImage mainImg;
    private PImage controlsImg;
    private boolean showControls;

    private Button playButton;
    private Button editorButton;
    private Button controlsButton;
    private Button exitButton;

    public Menu() throws IOException {
        showControls = false;
        state = State.IN_MENU;

        mainImg = new PImage(ImageIO.read(new File("./resources/menu/main.png")));
        controlsImg = new PImage(ImageIO.read(new File("./resources/menu/controls.png")));

        playButton = new Button("play", new PVector(mainImg.width / 2, 256), new IntVec2(128, 16));
        editorButton = new Button("editor", new PVector(mainImg.width / 2, 256 + 96), new IntVec2(128, 16));
        controlsButton = new Button("controls", new PVector(mainImg.width / 2, 256 + 2*96), new IntVec2(128, 16));
        exitButton = new Button("exit", new PVector(mainImg.width / 2, 256 + 3*96), new IntVec2(128, 16));

    }

    public void draw(PGraphics g) {

        g.image(mainImg, 0, 0);
        playButton.draw(g);
        editorButton.draw(g);
        controlsButton.draw(g);
        exitButton.draw(g);

        if(showControls){
            g.image(controlsImg, 0, 0);
        }
    }

    public State getState() {
        return state;
    }

    public void keyPressed(Window wnd, KeyEvent e) throws IOException {
        if(e.getKey() == 'p' || e.getKey() == 'P') {
            wnd.createGame();
            state = State.IN_GAME;
        } else if(e.getKey() == 'e' || e.getKey() == 'E') {
            wnd.createEditor();
            state = State.IN_EDITOR;
        }

    }

}
