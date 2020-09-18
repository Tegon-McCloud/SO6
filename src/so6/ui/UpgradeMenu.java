package so6.ui;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.base.Tower;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class UpgradeMenu {
    private PVector pos;
    private PImage img;
    private boolean showUMenu = false;


   public UpgradeMenu() throws IOException {
       img = new PImage(ImageIO.read(new File("./resources/Upgrade menu/Upgrade menu.png")));


    }
    public void draw(PGraphics g){

       if(showUMenu){
            g.imageMode(PConstants.CENTER);
            g.image(img,pos.x,pos.y);
        }

    }
    public void toggleUM(){
        showUMenu = !showUMenu;

    }
    public void getTowerPos(Tower tower){
        pos = tower.getPosition();

    }
    /*
    public void rangeUpgrade(){
        if (px >= rx &&         // right of the left edge AND
                px <= rx + rw &&    // left of the right edge AND
                py >= ry &&         // below the top AND
                py <= ry + rh) {    // above the bottom
        }

    }
    private void speedUpgrade(){


    }
 */
}


