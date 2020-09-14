package so6.base.level;

import processing.core.PGraphics;
import processing.core.PImage;

public class Cell {

    private PImage img;
    boolean isRoad;

    public Cell(PImage img, boolean isRoad) {
        this.img = img;
        this.isRoad = isRoad;
    }

    public void draw(PGraphics g, int x, int y) {
        g.image(img, 1280/16 * x, 720/9 * y);

    }


}
