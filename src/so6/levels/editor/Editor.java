package so6.levels.editor;

import processing.core.PGraphics;
import processing.event.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Editor {

    BufferedImage straightPath, cornerPath, noPath;

    public Editor() throws IOException {
        noPath = ImageIO.read(new File("./resources/editor/no_path.png"));
        straightPath = ImageIO.read(new File("./resources/editor/straight_path.png"));
        cornerPath = ImageIO.read(new File("./resources/editor/corner_path.png"));
    }

    public void draw(PGraphics g) {

    }

    public void mousePressed(MouseEvent e) {


    }


}
