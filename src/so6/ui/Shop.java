package so6.ui;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.MouseEvent;
import so6.Game;
import so6.Window;
import so6.towers.*;
import so6.util.IntVec2;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Shop {

    private static final List<ShopElement> elements = new Vector<>();

    static {
        try {
            elements.add(new ShopElement(100, new Archer(null)));
            elements.add(new ShopElement(100, new Cannon(null)));
            elements.add(new ShopElement(100, new Flamethrower(null)));
            elements.add(new ShopElement(100, new Mage(null)));
            elements.add(new ShopElement(100, new Mortar(null)));
            elements.add(new ShopElement(100, new SniperTroop(null)));


        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    private PImage img;
    private PGraphics g;
    boolean dirty;

    private IntVec2 shopPos;

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

            dirty = false;
        }



        shopPos = new IntVec2(g.width - img.width + 8 * 4, 8*4);

        g.image(this.g, shopPos.x, shopPos.y,  this.g.width, this.g.height);

    }

    public void mousePressed(Game game, MouseEvent e) {
        IntVec2 mouse = new IntVec2(e.getX() - shopPos.x, e.getY() - shopPos.y);

        int col = 0, row = 0;

        for (ShopElement element : elements) {

            if(element.isInside(mouse, col, row, g.width)) {
                if(game.getPlayerData().trySpend(element.price)){
                    game.grab(element.cls);
                }
                break;
            }

            col++;
            if (col > 1) {
                row++;
                col = 0;
            }
        }

    }


}
