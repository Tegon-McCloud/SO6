package so6.ui;

import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import so6.Window;
import so6.util.IntVec2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Menu {

    private State state;

    private PImage mainImg;

    private Button playButton;
    private Button playCustomButton;
    private Button editorButton;
    private Button controlsButton;
    private Button exitButton;

    private PFont font;

    public Menu() throws IOException {
        state = State.IN_MENU;

        mainImg = new PImage(ImageIO.read(new File("./resources/menu/main.png")));

        playButton = new Button("Play", new PVector(mainImg.width / 2, 256), new IntVec2(128, 16));
        playCustomButton = new Button("Play custom", new PVector(mainImg.width / 2, 256 + 96), new IntVec2(128, 16));
        editorButton = new Button("Edit", new PVector(mainImg.width / 2, 256 + 2*96), new IntVec2(128, 16));
        controlsButton = new Button("Controls", new PVector(mainImg.width / 2, 256 + 3*96), new IntVec2(128, 16));
        exitButton = new Button("Exit", new PVector(mainImg.width / 2, 256 + 4*96), new IntVec2(128, 16));

        try {
            font = new PFont(Font.createFont(Font.TRUETYPE_FONT, new File("./resources/menu/font/MinecraftRegular-Bmg3.otf")), false);
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

    }

    public void draw(PGraphics g) {

        g.textFont(font);

        g.imageMode(PConstants.CORNER);
        g.image(mainImg, 0, 0);
        playButton.draw(g);
        playCustomButton.draw(g);
        editorButton.draw(g);
        controlsButton.draw(g);
        exitButton.draw(g);

    }

    public State getState() {
        return state;
    }
    public void setState(State state){
        this.state = state;
    }

    public void mousePressed(Window wnd, MouseEvent e) {

        PVector mousePos = new PVector(e.getX(), e.getY());

        if(e.getButton() == PConstants.LEFT){
            if(playButton.isInside(mousePos)){
                try {
                    wnd.createGame("base");
                    state = State.IN_GAME;
                } catch(IOException exception) {
                    exception.printStackTrace();
                }
            }

            if(playCustomButton.isInside(mousePos)){
                try {
                    wnd.createGame("custom");
                    state = State.IN_GAME;
                } catch(IOException exception) {
                    exception.printStackTrace();
                }
            }

            if(editorButton.isInside(mousePos)) {
                try {
                    wnd.createEditor();
                    state = State.IN_EDITOR;
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

            if(controlsButton.isInside(mousePos)){
                wnd.showControls();
            }

            if(exitButton.isInside(mousePos)){
                wnd.exit();
            }
        }

    }

    public void keyPressed(Window wnd, KeyEvent e) throws IOException {
        if(e.getKey() == 'p' || e.getKey() == 'P') {

        } else if(e.getKey() == 'e' || e.getKey() == 'E') {
            wnd.createEditor();
            state = State.IN_EDITOR;
        }

    }

    public PFont getFont() {
        return font;
    }
}
