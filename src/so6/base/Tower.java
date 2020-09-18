package so6.base;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.Game;
import so6.base.level.Cell;
import so6.util.IntVec2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Tower {
    private IntVec2 pos;
    private int tierSpeed;
    private int tierRange;
    private PImage img;

    public Tower(IntVec2 pos) throws IOException {
        this.pos = pos;
        this.tierSpeed = 0;
        this.tierRange = 0;
        updateImg();

    }

    private void updateImg() throws IOException {
        BufferedImage bufImg = ImageIO.read(new File("./resources/towers/" + getName() + "/" + tierSpeed + tierRange + ".png"));
        img = new PImage(bufImg);
    }

    public void upgrade(String tierType) {
        if (tierType== "speed"){
            tierSpeed++;
        }else if (tierType=="range"){
            tierRange++;
        }
        try {
            updateImg();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(PGraphics g) {
        g.imageMode(PConstants.CENTER);
        g.pushMatrix();

        g.translate(Cell.pxWidth * (pos.x + 0.5f), Cell.pxHeight * (pos.y+ + 0.5f));
        g.rotate(getAngle());
        g.image(img, 0, 0);

        g.popMatrix();
    }

    public abstract void update(Game game, float t, float dt);

    public String getName() {
        return "base";
    }

    public int getTierSpeed() {
        return 0;
    }

    public float getAngle() {
        return 0.0f;
    }

    public PVector getPosition() {
        return new PVector((pos.x + 0.5f) * Cell.pxWidth, (pos.y + 0.5f) * Cell.pxHeight);
    }

}
