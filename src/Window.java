import processing.core.PApplet;

public class Window extends PApplet implements Runnable {

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        background(0.0f);
    }

    @Override
    public void run() {
        PApplet.main("Window");
    }
}
