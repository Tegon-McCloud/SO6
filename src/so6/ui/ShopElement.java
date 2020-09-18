package so6.ui;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.Window;
import so6.base.Tower;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ShopElement {
    public int price;
    public Class cls;
    public String name;
    public PImage img;

    public ShopElement(int price, Tower tower) throws IOException {
        this.price = price;
        this.name = tower.getName();
        this.cls = tower.getClass();

        PGraphics g = Window.getWnd().createGraphics(82, 82 + 40);
        PImage cell = new PImage(ImageIO.read(new File("./resources/editor/no_path.png")));

        g.beginDraw();

        g.background(180.0f);
        g.stroke(0.0f);
        g.noFill();
        g.rect(0, 0, 81, 81);
        g.rect(0, 81, 81, 40);
        g.image(cell, 1, 1);

        tower.draw(g, new PVector(41.0f, 41.0f));

        g.textFont(Window.getWnd().getMenu().getFont());
        g.textSize(24);
        g.fill(0.0f);
        g.text(price, 20, 110);



        g.endDraw();

        img = g;
    }

    public void draw(PGraphics g, int col, int row) {

        int x = g.width / 2 + (col == 0 ? -60 : +60);
        int y = row * 140 + 80;

        g.imageMode(PConstants.CENTER);
        g.image(img, x, y);
    }

}
