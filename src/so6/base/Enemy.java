package so6.base;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.Game;
import so6.base.level.Level;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Enemy {

    private PVector pos;
    private float speed;
    private int health;
    private String enemyName;
    private PImage img;

    float f;
    int targetIndex;

    public Enemy(String name) throws IOException {
        targetIndex = 1;
        speed = 1.0f;
        enemyName = name;
        try(
                BufferedReader in = new BufferedReader(new FileReader(new File("./resources/enemies/" + name + ".txt")))
                ) {
            speed = Float.parseFloat(in.readLine());
            health = Integer.parseInt(in.readLine());
        }

        img = new PImage(ImageIO.read(new File("./resources/enemies/" + name + ".png")));
        pos = new PVector(0.0f, 0.0f);

    }

    void takeDamage(int dmg) {
        health -= dmg;
    }

    public void draw(PGraphics g) {
        g.imageMode(PConstants.CENTER);
        g.image(img, pos.x, pos.y);
    }

    public void update(Game game, float t, float dt, PlayerData pd) {
        if(health < 0){
            game.remove(this);
            pd.addCoins(enemyName);
            return;
        }

        Level lvl = game.getLvl();
        pos = PVector.lerp(lvl.getTargets().get(targetIndex - 1), lvl.getTargets().get(targetIndex), f);

        f += dt * speed;
        if(f > 1.0f){
            targetIndex++;
            f = 0.0f;

            if(targetIndex == lvl.getTargets().size()){
                game.remove(this);
                pd.removeHealth(enemyName);
            }
        }

    }

    public PVector getPosition() {
        return pos;
    }

    public float getPriority() {
        return targetIndex + f;
    }
}
