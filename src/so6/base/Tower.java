package so6.base;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import so6.base.level.Cell;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tower {
    private int x, y;
    private int tier;

    private PImage img;

    public Tower(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        this.tier = 0;

        updateImg();
    }

    private void updateImg() throws IOException {
        BufferedImage bufImg = ImageIO.read(new File("./resources/towers/" + getName() + "/" + tier + ".png"));
        img = new PImage(bufImg);
    }

    public void upgrade() {
        tier++;
        try {
            updateImg();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(PGraphics g) {
        g.imageMode(PConstants.CENTER);
        g.pushMatrix();

        g.translate(Cell.pxWidth * (x + 0.5f), Cell.pxHeight * (y + 0.5f));
        g.rotate(getAngle());
        g.image(img, 0, 0);

        g.popMatrix();
    }

    public String getName() {
        return "base";
    }

    public int getTier() {
        return 0;
    }

    public float getAngle() {
        return 0.0f;
    }

}
