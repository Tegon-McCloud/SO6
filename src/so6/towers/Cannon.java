package so6.towers;

import processing.core.PVector;
import so6.Game;
import so6.base.Enemy;
import so6.base.Projectile;
import so6.base.Tower;
import so6.util.IntVec2;

import java.io.IOException;

public class Cannon extends Tower {

    private float angle;
    private float tLastShot;
    private Enemy target;

    public Cannon(IntVec2 pos) throws IOException {
        super(pos);

        angle = 0.0f;
        tLastShot = 0.0f;
    }

    @Override
    public void update(Game game, float t, float dt) {

        Enemy target = null;
        float priority = 0.0f;

        for(Enemy e : game.getEnemies()) {
            float ePriority = e.getPriority();

            if(ePriority > priority){
                priority = ePriority;
                target = e;
            }

        }

        if(target == null){
            return;
        }

        PVector targetPos = target.getPosition();
        PVector thisPos = getPosition();
        PVector diff = PVector.sub(thisPos, targetPos);

        angle = (float)Math.atan2(diff.y, diff.x);

        if(t - tLastShot > 1.0f) {

            try {
                game.getProjectiles().add(new Projectile("cannonball", this, target));
            } catch (IOException e) {
                e.printStackTrace();
            }

            tLastShot = t;
        }

    }

    @Override
    public String getName() {
        return "cannonbeck";
    }

    @Override
    public float getAngle() {
        return angle;
    }
}
