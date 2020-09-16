package so6;

import processing.core.PGraphics;
import so6.base.Enemy;
import so6.base.Tower;
import so6.base.level.Level;
import so6.towers.Archer;
import so6.util.IntVec2;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Game {

    private Level level;
    private List<Tower> towers;
    private List<Enemy> enemies;

    private List<Enemy> deadEnemies;

    private long tlast;
    private long tstart;


    boolean enemySpawned = false;

    public Game() throws IOException {
        level = new Level("custom0");
        towers = new Vector<>();
        enemies = new Vector<>();
        deadEnemies = new Vector<>();

        enemies.add(new Enemy("flower monster"));
        towers.add(new Archer(new IntVec2(2, 2)));

        tstart = System.nanoTime();
        tlast = tstart;
    }

    public void draw(PGraphics g) {

        long tnow = System.nanoTime();
        float t = (tnow - tstart) / 1e9f;
        float dt = (tnow - tlast) / 1e9f;
        tlast = tnow;

        if(t > 3.0f && !enemySpawned){
            try{
                enemies.add(new Enemy("flower monster"));
            }catch(Exception e){}
            
            enemySpawned = true;
        }

        level.draw(g);

        for(Tower tower : towers) {
            tower.update(this, t, dt);
            tower.draw(g);
        }

        for(Enemy enemy : enemies) {
            enemy.update(this, t, dt);
            enemy.draw(g);
        }

        while(deadEnemies.size() != 0){
            enemies.remove(deadEnemies.get(0));
            deadEnemies.remove(0);
        }



    }

    public Level getLvl() {
        return level;
    }

    public void remove(Enemy enemy) {
        deadEnemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public static void main(String[] args) throws IOException {

        //BufferedImage img = ImageIO.read(new File("./resources/base/no_path.png"));
        //for(int i = 0; i < 16; i++) {
        //    for (int j = 0; j < 9; j++) {
        //        ImageIO.write(img, "PNG", new File("./resources/base/" + i + "," + j + ".png"));
        //    }
        //}
        Window.run();
    }

}
