package so6;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import so6.base.Enemy;
import so6.base.PlayerData;
import so6.base.Projectile;
import so6.base.Tower;
import so6.base.level.Cell;
import so6.base.level.Level;
import so6.ui.Menu;
import so6.ui.Overlay;
import so6.ui.State;
import so6.ui.UpgradeMenu;
import so6.util.IntVec2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Game {

    public static final int width = 1280, height  = 720;

    private PlayerData pd;

    private Level level;
    private List<Tower> towers;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;

    private List<Enemy> deadEnemies;
    private List<Projectile> deadProjectiles;

    private Overlay overlay;
    private UpgradeMenu um;

    private long tlast;
    private long tstart;

    private HashMap<String, float[]> spawnTimers;

    private Tower grabbed;

    public Game(String levelName) throws IOException {
        level = new Level(levelName);
        towers = new Vector<>();
        enemies = new Vector<>();
        projectiles = new Vector<>();

        deadEnemies = new Vector<>();
        deadProjectiles = new Vector<>();

        spawnTimers = new HashMap<>();

        try(
                BufferedReader in = new BufferedReader(new FileReader(new File("./resources/spawntimers.txt")));
                ) {

            String enemyName = null;
            while((enemyName = in.readLine()) != null) {
                float[] timers = new float[2];
                timers[0] = Float.parseFloat(in.readLine());
                timers[1] = Float.parseFloat(in.readLine());

                spawnTimers.put(enemyName, timers);
            }
        }


        grabbed = null;

        tstart = System.nanoTime();
        tlast = tstart;

        pd = new PlayerData(300,200);
        overlay = new Overlay(this);
        um = new UpgradeMenu();
    }

    public void draw(PGraphics g) {

        long tnow = System.nanoTime();
        float t = (tnow - tstart) / 1e9f;
        float dt = (tnow - tlast) / 1e9f;
        tlast = tnow;

        try {
            spawn(t);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        level.draw(g);

        for(Tower tower : towers) {
            tower.update(this, t, dt);
            tower.draw(g);
            um.getTowerPos(tower);
            um.draw(g);
            um.speedUpgrade();
            um.rangeUpgrade();
        }

        for(Projectile projectile : projectiles) {
            projectile.update(this, t, dt);
            projectile.draw(g);
        }

        while(deadProjectiles.size() != 0){
            projectiles.remove(deadProjectiles.get(0));
            deadProjectiles.remove(0);
        }

        for(Enemy enemy : enemies) {
            enemy.update(this, t, dt, pd);
            enemy.draw(g);
        }

        while(deadEnemies.size() != 0){
            enemies.remove(deadEnemies.get(0));
            deadEnemies.remove(0);
        }

        overlay.draw(g);

        if(grabbed != null){
            int mx = Window.getWnd().mouseX;
            int my = Window.getWnd().mouseY;

            grabbed.move(new IntVec2(mx / Cell.pxWidth, my / Cell.pxHeight));
            grabbed.draw(g);
        }

        if(pd.getLife() <= 0) {
            Window.getWnd().getMenu().setState(State.IN_MENU);
        }

    }

    private void spawn(float t) throws IOException {
        Iterator it = spawnTimers.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, float[]> pair = (Map.Entry) it.next();
            float[] timers = pair.getValue();

            if (t > timers[0]) {
                timers[0] += timers[1];
                enemies.add(new Enemy(pair.getKey()));
            }

        }

    }

    public Level getLvl() {
        return level;
    }

    public void remove(Enemy enemy) {
        deadEnemies.add(enemy);
    }

    public void remove(Projectile projectile) {
        deadProjectiles.add(projectile);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public PlayerData getPlayerData() {
        return pd;
    }


    public void keyPressed(Menu menu, KeyEvent e) {
        if(e.getKey() == ' '){
            overlay.toggle();
        }else if(e.getKey() == 'h' ||e.getKey() == 'H') {

        }else if(e.getKey() == PConstants.ESC) {
            menu.setState(State.IN_MENU);
        }else if(e.getKey() == 'f'){
            um.toggleUM();
        }

    }

    public void mousePressed(MouseEvent e) {
        if(grabbed != null){
            if(level.isPath(grabbed.getCellPosition())){
                return;
            }
            for(Tower tower : towers){
                if(grabbed.getCellPosition().equals(tower.getCellPosition())){
                    return;
                }
            }

            towers.add(grabbed);
            grabbed = null;
        } else {
            overlay.mousePressed(this, e);
        }

    }

    public void grab(Class towerClass) {
        try {
            Constructor<?> ctor = towerClass.getConstructor(IntVec2.class);
            grabbed = (Tower)ctor.newInstance(new Object[] {null});
        } catch (NoSuchMethodException e) {
            System.err.println("All towers must have a constructor that takes an IntVec2");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


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
