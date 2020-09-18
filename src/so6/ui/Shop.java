package so6.ui;

import processing.core.PGraphics;
import processing.core.PImage;
import so6.base.Tower;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Shop {
    private static final List<Tower> towers = new Vector<>();

    public static List<Tower> getTowerList() {
        return towers;
    }

    private PImage img, cellImg;

    public Shop() throws IOException {
        img = new PImage(ImageIO.read(new File("./resources/menu/shop.png")));
        cellImg = new PImage(ImageIO.read(new File("./resources/editor/no_path.png")));
    }

    public void draw(PGraphics g) {
        g.image(img, g.width - img.width, 0);

        int row = 0, col = 0;

        for(Tower t : towers) {
            

            col++;
            if(col >= 1){
                row++;
                col = 0;
            }
        }

    }


}
