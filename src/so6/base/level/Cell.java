package so6.base.level;

import processing.core.PGraphics;
import processing.core.PImage;

public class Cell {

    public static final int pxWidth = 80, pxHeight = 80;

    private PImage img;
    private boolean isRoad;

    public Cell(PImage img, boolean isRoad) {
        this.img = img;
        this.isRoad = isRoad;
    }

    public void draw(PGraphics g, int x, int y) {
        g.image(img, pxWidth * x, pxHeight * y);
    }

    public boolean isRoad() {
        return isRoad;
    }

}
