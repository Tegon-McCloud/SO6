package so6.levels.editor;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import so6.base.level.Cell;
import so6.base.level.Level;
import so6.ui.Menu;
import so6.ui.State;
import so6.util.IntVec2;

import javax.imageio.ImageIO;
import java.io.*;

public class LevelEditor {

    private PImage horizontalPath, verticalPath, corner0Path, corner1Path, corner2Path, corner3Path, noPath;

    private IntVec2 begin, end;
    private Cell[][] cells;

    int mode;

    public LevelEditor() throws IOException {
        noPath = new PImage(ImageIO.read(new File("./resources/editor/no_path.png")));
        horizontalPath = new PImage(ImageIO.read(new File("./resources/editor/horizontal_path.png")));
        verticalPath = new PImage(ImageIO.read(new File("./resources/editor/vertical_path.png")));
        corner0Path = new PImage(ImageIO.read(new File("./resources/editor/corner0_path.png")));
        corner1Path = new PImage(ImageIO.read(new File("./resources/editor/corner1_path.png")));
        corner2Path = new PImage(ImageIO.read(new File("./resources/editor/corner2_path.png")));
        corner3Path = new PImage(ImageIO.read(new File("./resources/editor/corner3_path.png")));

        cells = new Cell[Level.width][Level.height];

        mode = 3;

        for (int i = 0; i < Level.width; i++) {
            for (int j = 0; j < Level.height; j++) {
                cells[i][j] = new Cell(noPath, false);
            }
        }

    }

    public void draw(PGraphics g) {
        for (int i = 0; i < Level.width; i++) {
            for (int j = 0; j < Level.height; j++) {
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

    boolean isPath(int x, int y) {
        if(x < 0 || x >= Level.width || y < 0 || y >= Level.height) return false;
        return cells[x][y].isRoad();
    }

    boolean isPath(IntVec2 pos) {
        return isPath(pos.x, pos.y);
    }

    void updateImages() {
        for (int i = 0; i < Level.width; i++) {
            for (int j = 0; j < Level.height; j++) {
                if(isPath(i, j)) {
                    if(isPath(i - 1, j) && isPath(i + 1, j)) {
                        cells[i][j].setImg(horizontalPath);
                    }else if(isPath(i, j - 1) && isPath(i, j + 1)) {
                        cells[i][j].setImg(verticalPath);
                    }else if(isPath(i - 1, j) && isPath(i, j + 1)) {
                        cells[i][j].setImg(corner0Path);
                    }else if(isPath(i + 1, j) && isPath(i, j + 1)) {
                        cells[i][j].setImg(corner1Path);
                    }else if(isPath(i + 1, j) && isPath(i, j - 1)) {
                        cells[i][j].setImg(corner2Path);
                    }else if(isPath(i - 1, j) && isPath(i, j - 1)) {
                        cells[i][j].setImg(corner3Path);
                    }else {
                        if(isPath(i - 1, j) || isPath(i + 1, j)) {
                            cells[i][j].setImg(horizontalPath);
                        }else {
                            cells[i][j].setImg(verticalPath);
                        }
                    }

                } else {
                    cells[i][j].setImg(noPath);
                }
            }
        }
    }

    public boolean validate() {

        if(!validateBegin()) {
            return false;
        }

        if(!validateEnd()){
            return false;
        }

        IntVec2 nextPos = begin.copy();
        if(begin.x == 0){
            nextPos.x++;
        }
        if(begin.x == Level.width - 1){
            nextPos.x--;
        }
        if(begin.y == 0){
            nextPos.y++;
        }
        if(begin.y == Level.height - 1) {
            nextPos.y--;
        }

        return validate(nextPos, begin);
    }

    public boolean validate(IntVec2 pos, IntVec2 lastPos) {
        if(!isPath(pos)) {
            return false;
        }

        if(pos.x == end.x && pos.y == pos.y) {
            return true;
        }

        IntVec2 testPos = null;
        IntVec2 nextPos = null;
        int pathCount = 0;

        testPos = new IntVec2(pos.x + 1, pos.y);
        if(isPath(testPos)) {
            pathCount++;
            if(!(lastPos.x == testPos.x && lastPos.y == testPos.y)) {
                nextPos = testPos;
            }
        }

        testPos = new IntVec2(pos.x - 1, pos.y);
        if(isPath(testPos)) {
            pathCount++;
            if(!(lastPos.x == testPos.x && lastPos.y == testPos.y)) {
                nextPos = testPos;
            }
        }

        testPos = new IntVec2(pos.x, pos.y + 1);
        if(isPath(testPos)) {
            pathCount++;
            if(!(lastPos.x == testPos.x && lastPos.y == testPos.y)) {
                nextPos = testPos;
            }
        }

        testPos = new IntVec2(pos.x, pos.y - 1);
        if(isPath(testPos)) {
            pathCount++;
            if(!(lastPos.x == testPos.x && lastPos.y == testPos.y)) {
                nextPos = testPos;
            }
        }

        if(nextPos == null) {
            return false;
        }

        if(pathCount != 2) {
            return false;
        }

        return validate(nextPos, pos);
    }

    public boolean validateBegin() {

        if(begin == null){
            return false;
        }

        if(begin.x != 0 && begin.x != Level.width - 1 && begin.y != 0 &&  begin.y != Level.height - 1)
            return false;

        if((begin.x == 0 || begin.x == Level.width - 1) == (begin.y == 0 || begin.y == Level.height - 1)){
            return false;
        }

        if(begin.x == 0 && !isPath(1, begin.y)) {
            return false;
        }
        if(begin.x == Level.width - 1 && !isPath(Level.width - 1, begin.y)) {
            return false;
        }
        if(begin.y == 0 && !isPath(begin.x, 1)) {
            return false;
        }
        if(begin.y == Level.height - 1 && !isPath(begin.x, Level.height - 1)) {
            return false;
        }

        return true;
    }

    public boolean validateEnd() {

        if(end == null) {
            return false;
        }

        if(end.x != 0 && end.x != Level.width - 1 && end.y != 0 &&  end.y != Level.height - 1)
            return false;

        if((end.x == 0 || end.x == Level.width - 1) == (end.y == 0 || end.y == Level.height - 1)){
            return false;
        }

        if(end.x == 0 && !isPath(1, end.y)) {
            return false;
        }
        if(end.x == Level.width - 1 && !isPath(Level.width - 1, end.y)) {
            return false;
        }
        if(end.y == 0 && !isPath(end.x, 1)) {
            return false;
        }
        if(end.y == Level.height - 1 && !isPath(end.x, Level.height - 1)) {
            return false;
        }

        return true;
    }

    public boolean save() throws FileNotFoundException {
        if(!validate()) {
            System.out.println("Validation failed");
            return false;
        }

        File lvlDir;

        int n = 0;
        while(!(lvlDir = new File("./resources/levels/custom" + n)).mkdir()) {
            n++;
        }

        try (
                PrintWriter out = new PrintWriter(new FileOutputStream(new File(lvlDir, "level.txt")))
                ) {

            for (int i = 0; i < Level.width; i++) {
                for (int j = 0; j < Level.height; j++) {
                    out.println(isPath(i, j) ? "1" : "0");
                }
            }

            out.println(begin.toString());
            out.println(end.toString());

        }

        for (int i = 0; i < Level.width; i++) {
            for (int j = 0; j < Level.height; j++) {
                cells[i][j].getImg().save(new File(lvlDir, i + "," + j + ".png").getAbsolutePath());
            }
        }


        return true;
    }

    public void keyPressed(Menu menu, KeyEvent e) {
        if(e.getKey() == 's' || e.getKey() == 'S') {
            try {
                if(save()) {
                    System.out.println("Level saved.");
                }

            } catch (FileNotFoundException e1) {
                System.out.println("Failed to save level");
            }
        }else if(e.getKey() == '1') {
            mode = 1;
        }else if(e.getKey() == '2') {
            mode = 2;
        }else if(e.getKey() == '3') {
            mode = 3;
        }else if(e.getKey() == PConstants.ESC) {
            menu.setState(State.IN_MENU);
        }

    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX() / Cell.pxWidth;
        int y = e.getY() / Cell.pxHeight;

        switch (mode) {
            case 1:
                cells[x][y].setRoad(!cells[x][y].isRoad());
                break;
            case 2:
                begin = new IntVec2(x, y);
                break;
            case 3:
                end = new IntVec2(x, y);
                break;

        }

        updateImages();
    }


}
