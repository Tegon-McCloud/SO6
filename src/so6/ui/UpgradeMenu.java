package so6.ui;

import processing.core.*;
import so6.Window;
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

    public void draw(PGraphics g) {

        if (showUMenu) {
            g.imageMode(PConstants.CENTER);
            g.image(img, pos.x, pos.y);
        }

    }

    public void toggleUM() {
        showUMenu = !showUMenu;

    }

    public void getTowerPos(Tower tower) {
        pos = tower.getPosition();

    }

    public void rangeUpgrade() {
        int mouseX = Window.getWnd().mouseX;
        int mouseY = Window.getWnd().mouseY;
        boolean clickEvent = Window.getWnd().mousePressed;
        if (showUMenu) {
            if (clickEvent) {
                if (mouseX >= pos.x + 4 &&
                        mouseX <= pos.x + 28 &&
                        mouseY >= pos.y - 4 &&
                        mouseY <= pos.y - 28) {
                    System.out.println("heya");
                    System.out.println(pos.x+"posx "+" mousex"+mouseX);
                }

            }

        }
    }
    public void speedUpgrade() {
        int mouseX = Window.getWnd().mouseX;
        int mouseY = Window.getWnd().mouseY;
        boolean clickEvent = Window.getWnd().mousePressed;
        if (showUMenu){
            if (clickEvent){
                if (mouseX >= pos.x - 3 &&
                        mouseX <= pos.x - 11 &&
                        mouseY >= pos.y - 3 &&
                        mouseY <= pos.y - 11) {
                    System.out.println("hoopla");
                }
            }
        }
    }
}


