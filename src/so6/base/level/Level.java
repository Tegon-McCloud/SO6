package so6.base.level;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.util.IntVec2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {

    public static final int width = 16, height = 9;

    private Cell[][] cells;

    private String levelName;

    private IntVec2 begin, end;

    private List<PVector> targets;

    public Level(String levelName) throws IOException {
        this.levelName = levelName;
        this.targets = new ArrayList<>();

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

                    cells[i][j] = new Cell(img, line.equals("1"));
                }
            }

            line = in.readLine();
            begin = new IntVec2(line);
            line = in.readLine();
            end = new IntVec2(line);
        }

        buildTargets();
    }

    public void buildTargets() {

        IntVec2 prevPos = begin.copy();

        if(begin.x == 0){
            prevPos.x--;
        }
        if(begin.x == width - 1){
            prevPos.x++;
        }
        if(begin.y == 0){
            prevPos.y--;
        }
        if(begin.y == height - 1) {
            prevPos.y++;
        }

        IntVec2 nextPos = end.copy();

        if(end.x == 0){
            nextPos.x--;
        }
        if(end.x == width - 1){
            nextPos.x++;
        }
        if(end.y == 0){
            nextPos.y--;
        }
        if(end.y == height - 1) {
            nextPos.y++;
        }

        targets.add(new PVector((prevPos.x + 0.5f) * Cell.pxWidth, (prevPos.y + 0.5f) * Cell.pxHeight));

        appendToTargets(begin, prevPos);

        targets.add(new PVector((nextPos.x + 0.5f) * Cell.pxWidth, (nextPos.y + 0.5f) * Cell.pxHeight));

    }

    public void appendToTargets(IntVec2 pos, IntVec2 lastPos) {

        targets.add(new PVector((pos.x + 0.5f) * Cell.pxWidth, (pos.y + 0.5f) * Cell.pxHeight));

        if(pos.x == end.x && pos.y == end.y){
            return;
        }

        IntVec2 testPos;
        IntVec2 nextPos = null;

        testPos = new IntVec2(pos.x + 1, pos.y);
        if(isPath(testPos)) {
            if(!(lastPos.x == testPos.x && lastPos.y == testPos.y)) {
                nextPos = testPos;
            }
        }

        testPos = new IntVec2(pos.x - 1, pos.y);
        if(isPath(testPos)) {
            if(!(lastPos.x == testPos.x && lastPos.y == testPos.y)) {
                nextPos = testPos;
            }
        }

        testPos = new IntVec2(pos.x, pos.y + 1);
        if(isPath(testPos)) {
            if(!(lastPos.x == testPos.x && lastPos.y == testPos.y)) {
                nextPos = testPos;
            }
        }

        testPos = new IntVec2(pos.x, pos.y - 1);
        if(isPath(testPos)) {
            if(!(lastPos.x == testPos.x && lastPos.y == testPos.y)) {
                nextPos = testPos;
            }
        }

        assert(nextPos != null);

        appendToTargets(nextPos, pos);
    }

    public void draw(PGraphics g) {
        g.imageMode(PConstants.CORNER);
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

    public List<PVector> getTargets() {
        return targets;
    }

    public boolean isPath(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y > height) {
            return false;
        }

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
