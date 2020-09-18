package so6.ui;

import processing.core.*;
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
        if (mouseX >= pos.x + 3 &&         // right of the left edge AND
                mouseX <= pos.x + 11 &&    // left of the right edge AND
                mouseY >= pos.y + 3 &&         // below the top AND
                mouseY <= pos.y + 11) {    // above the bottom
        }

    }
    private void speedUpgrade(){
        if (mouseX >= pos.x - 3 &&         // right of the left edge AND
                mouseX <= pos.x - 11 &&    // left of the right edge AND
                mouseY >= pos.y - 3 &&         // below the top AND
                mouseY <= pos.y - 11) {    // above the bottom
        }

    }

 */

}


