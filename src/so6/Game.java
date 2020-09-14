package so6;

import processing.core.PGraphics;
import so6.base.Tower;
import so6.base.level.Level;
import so6.levels.Level0;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Game {

    private Level level;
    private List<Tower> towers;

    public Game() throws IOException {
        level = new Level0();
        towers = new Vector<Tower>();

    }

    public void draw(PGraphics g) {
        level.draw(g);

        for(Tower t : towers){
            t.draw(g);
        }
    }

    public static void main(String[] args) throws IOException {

        //BufferedImage img = ImageIO.read(new File("./resources/base/no_path.png"));
        //for(int i = 0; i < 16; i++) {
        //    for (int j = 0; j < 9; j++) {
        //        ImageIO.write(img, "PNG", new File("./resources/base/" + i + "," + j + ".png"));
        //    }
        //}

        Thread graphicsThread = new Thread(() -> Window.run(), "graphics");
        graphicsThread.start();
    }

}
