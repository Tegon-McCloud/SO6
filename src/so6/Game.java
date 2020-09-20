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
import so6.towers.Archer;
import so6.ui.Menu;
import so6.ui.Overlay;
import so6.ui.State;
import so6.ui.UpgradeMenu;
import so6.util.IntVec2;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

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

    boolean enemySpawned = false;

    private Tower grabbed;

    public Game() throws IOException {
        level = new Level("custom0");
        towers = new Vector<>();
        enemies = new Vector<>();
        projectiles = new Vector<>();

        deadEnemies = new Vector<>();
        deadProjectiles = new Vector<>();

        enemies.add(new Enemy("flower monster"));

        /*towers.add(new Archer(new IntVec2(2, 2)));
        towers.add(new Mage(new IntVec2(5, 5)));
        towers.add(new Cannon(new IntVec2(3, 0)));
        towers.add(new Flamethrower(new IntVec2(2, 3)));
        towers.add(new Mortar(new IntVec2(5, 3)));

        towers.add(new SniperTroop(new IntVec2(1, 0)));*/

        grabbed = new Archer(null);

        tstart = System.nanoTime();
        tlast = tstart;

        pd = new PlayerData(200,200);
        overlay = new Overlay(this);
        um = new UpgradeMenu();
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
            um.getTowerPos(tower);
            um.draw(g);
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
            towers.add(grabbed);
            grabbed = null;
        } else {
            overlay.mousePressed(this, e);
        }

    }

    public void grab(Class towerClass) {
        

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
