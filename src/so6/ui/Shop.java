package so6.ui;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import so6.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Shop {
    private static final List<ShopElement> elements = new Vector<>();

    public static List<ShopElement> getElementList() {
        return elements;
    }

    private PImage img;
    private PGraphics g;
    boolean dirty;

    public Shop() throws IOException {
        Collections.sort(elements, (t1, t2) -> t1.name.compareTo(t2.name));

        img = new PImage(ImageIO.read(new File("./resources/menu/shop.png")));
        g = Window.getWnd().createGraphics(66 * 4, 112 * 4);
        dirty = true;
    }

    public void draw(PGraphics g) {
        g.imageMode(PConstants.CORNER);
        g.image(img, g.width - img.width, 0);

        if(dirty) {
            int col = 0, row = 0;

            this.g.beginDraw();
            for (ShopElement e : elements) {

                e.draw(this.g, col, row);

                col++;
                if (col > 1) {
                    row++;
                    col = 0;
                }
            }
            this.g.endDraw();
        }

        g.image(this.g, g.width - img.width + 8 * 4, 8*4,  this.g.width, this.g.height);

    }


}
