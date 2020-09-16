package so6;

import processing.core.PGraphics;
import so6.base.Enemy;
import so6.base.Tower;
import so6.base.level.Level;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Game {

    private Level level;
    private List<Tower> towers;
    private List<Enemy> enemies;

    private List<Enemy> deadEnemies;


    public Game() throws IOException {
        level = new Level("custom0");
        towers = new Vector<>();
        enemies = new Vector<>();
        deadEnemies = new Vector<>();

        enemies.add(new Enemy("flower monster"));
    }

    public void draw(PGraphics g) {
        level.draw(g);

        for(Tower t : towers){
            t.draw(g);
        }

        for(Enemy e : enemies) {
            e.update(this, 0.0f, 0.017f);
            e.draw(g);
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

    public static void main(String[] args) throws IOException {

        //BufferedImage img = ImageIO.read(new File("./resources/base/no_path.png"));
        //for(int i = 0; i < 16; i++) {
        //    for (int j = 0; j < 9; j++) {
        //        ImageIO.write(img, "PNG", new File("./resources/base/" + i + "," + j + ".png"));
        //    }
        //}

        Thread graphicsThread = new Thread(() -> Window.run(), "graphics");
        graphicsThread.start();
    }

}
