package so6.ui;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import so6.Window;

import java.io.IOException;

public class Menu {

    private State state;

    public Menu() {
        state = State.IN_MENU;

    }

    public void draw(PGraphics g) {
        g.background(255.0f);

        g.textAlign(PConstants.CENTER);
        g.rectMode(PConstants.CENTER);

        g.fill(0.0f);
        g.textSize(36);

        g.pushMatrix();
        g.translate(g.width/2, g.height/2);

        g.text("Press p to play", 0, 0);
        g.text("Press e to edit", 0, 48);

        g.popMatrix();


    }

    public State getState() {
        return state;
    }

    public void keyPressed(Window wnd, KeyEvent e) throws IOException {
        if(e.getKey() == 'p' || e.getKey() == 'P') {
            wnd.createGame();
            state = State.IN_GAME;
        }else if(e.getKey() == 'e' || e.getKey() == 'E') {
            wnd.createEditor();
            state = State.IN_EDITOR;
        }


    }


}
