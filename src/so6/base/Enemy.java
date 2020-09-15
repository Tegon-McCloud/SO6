package so6.base;

import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import so6.Game;
import so6.base.level.Level;

public class Enemy {

    private PVector pos;
    private float speed;

    private PImage img;

    float f;
    int targetIndex;

    public Enemy() {
        targetIndex = 0;
        speed = 1.0f;
    }

    protected void updateImg() {

    }

    public void draw(PGraphics g) {
        g.fill(255.0f, 0.0f, 0.0f);
        g.noStroke();
        g.ellipse(pos.x, pos.y, 10.0f, 10.0f);
    }

    public void update(Game game, float t, float dt) {
        Level lvl = game.getLvl();
        pos = PVector.lerp(lvl.getTargets().get(targetIndex), lvl.getTargets().get(targetIndex + 1), f);

        f += dt * speed;
        if(f > 1.0f){
            if(targetIndex == lvl.getTargets().size()) {

            }
            targetIndex++;
            f = 0.0f;
        }

    }

    protected String getName() {
        return "base";
    }

}
