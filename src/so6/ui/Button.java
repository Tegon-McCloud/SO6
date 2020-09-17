package so6.ui;

import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.util.IntVec2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Button {

    private static final int pxSize = 4;

    private PVector position;
    private IntVec2 dimensions;
    private PImage img;

    public Button(String name, PVector position, IntVec2 dimensions) throws IOException {
        this.position = position;
        this.dimensions = dimensions;

        position.sub(new PVector(dimensions.x * pxSize, dimensions.y * pxSize).mult(0.5f));


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

        BufferedImage textImg = ImageIO.read(new File("./resources/menu/buttons/" + name + ".png"));
        float textRatio = textImg.getWidth() / (float)textImg.getHeight();
        int textHeight = bufImg.getHeight() - 8 * pxSize;
        int textWidth = (int) (textRatio * textHeight);

        g.drawImage(textImg, (bufImg.getWidth() - textWidth)/2, 4 * pxSize, textWidth, textHeight, null);

        img = new PImage(bufImg);

    }

    public void draw(PGraphics g) {
        g.image(img, position.x, position.y);
    }

    public boolean isInside(PVector point) {
        return position.x < point.x && point.x < position.x + pxSize * dimensions.x &&
        position.y < point.y && point.y < position.y + pxSize * dimensions.y;
    }

}
