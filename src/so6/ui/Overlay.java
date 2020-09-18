package so6.ui;

import processing.core.PConstants;
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
    private PImage coinIcon;
    private PImage heartIcon;

    private Shop shop;

    public Overlay(Game g) throws IOException {
        data = g.getPlayerData();

        coinIcon = new PImage(ImageIO.read(new File("./resources/icon/coin.png")));
        heartIcon = new PImage(ImageIO.read(new File("./resources/icon/heart.png")));
        shop = new Shop();
    }


    public void draw(PGraphics g){
        g.textSize(24);
        g.textAlign(PConstants.CORNER);
        g.fill(0.0f);
        float coinsWidth = g.textWidth(data.getCoins()+"") + coinIcon.width;
        g.text(data.getCoins(), 10 + coinIcon.width, 36);
        g.text(data.getLife(), 20 + coinsWidth + heartIcon.width, 36);

        g.imageMode(PConstants.CORNER);
        g.image(coinIcon, 10, 12);
        g.image(heartIcon, 20 + coinsWidth, 12);


        if(show) {
            shop.draw(g);

        }
    }

    public void toggle() {
        show = !show;
    }

}
