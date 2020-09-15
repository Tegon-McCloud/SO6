package so6.base.level;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import so6.util.IntVec2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Level {

    public static final int width = 16, height = 9;

    private Cell[][] cells;

    private String levelName;

    private IntVec2 begin, end;

    public Level(String levelName) throws IOException {
        this.levelName = levelName;

        cells = new Cell[width][height];

        try(
                BufferedReader in = new BufferedReader(new FileReader("./resources/levels/" + levelName + "/level.txt"));
                ) {
            
            String line;
            for(int i = 0; i < width; i++){
                for (int j = 0; j < height; j++) {
                    line = in.readLine();
                    BufferedImage bufImg = ImageIO.read(new File("./resources/levels/" + levelName + "/" + i + "," + j + ".png"));
                    PImage img = new PImage(bufImg);

                    cells[i][j] = new Cell(img, line == "1");
                }
            }

            line = in.readLine();
            begin = new IntVec2(line);
            line = in.readLine();
            end = new IntVec2(line);
        }

    }

    public void draw(PGraphics g) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].draw(g, i, j);
            }
        }

        if(begin != null){
            g.fill(0.0f);
            g.noStroke();
            g.ellipseMode(PConstants.CENTER);
            g.ellipse(Cell.pxWidth * (begin.x + 0.5f), Cell.pxHeight * (begin.y + 0.5f), 40, 40);
        }

        if(end != null){
            g.fill(0.0f);
            g.noStroke();
            g.rectMode(PConstants.CENTER);
            g.rect(Cell.pxWidth * (end.x + 0.5f), Cell.pxHeight * (end.y + 0.5f), 40, 40);
        }

    }

    public boolean isPath(int x, int y) {
        return cells[x][y].isRoad();
    }

    public boolean isPath(IntVec2 pos) {
        return isPath(pos.x, pos.y);
    }

    public IntVec2 getBegin() {
        return begin.copy();
    }

    public IntVec2 getEnd() {
        return end.copy();
    }
}
