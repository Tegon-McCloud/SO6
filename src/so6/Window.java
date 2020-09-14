package so6;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Window extends PApplet {

    private PGraphics g;

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        g = createGraphics(width, height);


    }

    @Override
    public void draw() {

        g.beginDraw();

        Game.get().draw(g);

        g.endDraw();

        image(g, 0, 0);
    }

    public static void run() {
        PApplet.main("so6.Window");
    }

}
