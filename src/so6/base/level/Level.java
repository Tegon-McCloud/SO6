package so6.base.level;

import processing.core.PGraphics;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level {

    private static final int width = 16, height = 9;

    private Cell[][] cells;

    public Level() throws IOException {
        cells = new Cell[width][height];
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++) {
                BufferedImage bufImg = ImageIO.read(new File("./resources/grass.png"));
                PImage img = new PImage(bufImg);
                cells[i][j] = new Cell(img, false);
            }
        }
    }

    public void draw(PGraphics g) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].draw(g, i, j);
            }
        }

    }

}
