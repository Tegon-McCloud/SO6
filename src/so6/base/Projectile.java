package so6.base;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.Game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Projectile {
    private PImage img;
    private PVector pos;
    private PVector vel;

    private float f;
    private float speed;
    private float angle;
    private int damage;
    private Enemy target;

    public Projectile(String name, Tower source, Enemy target) throws IOException {
        this.target = target;

        f = 0.0f;
        speed = 10.0f;
        damage = 1;

        img = new PImage(ImageIO.read(new File("./resources/projectiles/" + name + ".png")));
        pos = source.getPosition();
    }

    public void draw(PGraphics g) {
        g.imageMode(PConstants.CENTER);

        g.pushMatrix();
        g.translate(pos.x, pos.y);
        g.rotate(angle);
        g.image(img, 0, 0);
        g.popMatrix();
    }



    public void update(Game game, float t, float dt) {

        for(Enemy enemy : game.getEnemies()) {
            PVector diff = PVector.sub(enemy.getPosition(), pos);
            if(diff.mag() < 32.0f) {
                game.remove(this);
                enemy.takeDamage(damage);
            }
        }

        if(game.getEnemies().contains(target)){
            PVector targetPos = target.getPosition();
            vel = PVector.sub(targetPos, pos).normalize();
            vel.mult(speed);

            angle = (float)Math.atan2(vel.y, vel.x);
        } else  {
            if(pos.x < 0 || pos.x > Game.width || pos.y < 0 || pos.y > Game.height) {
                game.remove(this);
            }
        }

        pos.add(vel);





    }

}
