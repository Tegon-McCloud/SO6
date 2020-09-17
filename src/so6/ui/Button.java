package so6.ui;

import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.util.IntVec2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private static final int pxSize = 4;

    private PVector position;
    private IntVec2 dimensions;
    private PImage img;

    public Button(PVector position, IntVec2 dimensions) {
        this.position = position;
        this.dimensions = dimensions;

        BufferedImage bufImg = new BufferedImage(pxSize * dimensions.x, pxSize * dimensions.y, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufImg.getGraphics();

        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, pxSize * dimensions.x, pxSize * dimensions.y);

        g.setColor(new Color(225, 225, 225));
        g.fillRect(0, 0, pxSize, pxSize * dimensions.y);

        g.setColor(new Color(175, 175, 175));
        g.fillRect(pxSize * (dimensions.x - 1), 0, pxSize, pxSize * dimensions.y);

        g.setColor(new Color(225, 225, 225));
        g.fillRect(0, 0, pxSize * dimensions.x, pxSize);

        g.setColor(new Color(175, 175, 175));
        g.fillRect(0, pxSize * (dimensions.y - 1), pxSize * dimensions.x, pxSize);


        img = new PImage(bufImg);

    }

    public void draw(PGraphics g) {
        g.image(img, position.x, position.y);
    }


}
