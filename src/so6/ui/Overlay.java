package so6.ui;

import processing.core.PGraphics;
import processing.core.PImage;
import so6.Game;
import so6.base.PlayerData;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Overlay {
    private boolean show;
    private PlayerData data;
    private PImage shopImg;

    public Overlay(Game g) throws IOException {
        data = g.getPlayerData();

        shopImg = new PImage(ImageIO.read(new File("./resources/menu/shop.png")));
    }


    public void draw(PGraphics g){
        g.textSize(24);
        g.fill(0.0f);
        g.text(data.getCoins(), 10, 36);
        g.text(data.getLife(), 110, 36);

        if(show) {


        }
    }

    public void toggle() {
        show = !show;
    }

}
