package so6.base;

public class PlayerData {

    private int coins;
    private int defaultCoins;
    private int life;
    private int defaultLife;
    public PlayerData(int coins_, int life_) {
        defaultCoins=coins_;
        defaultLife = life_;

    }
    public void removeHealth(String enemyName){
        getEnemyType(enemyName);
        defaultLife-=life;
        System.out.println("life "+life+" "+defaultLife);

    }
    public void addCoins(String enemyName){
        getEnemyType(enemyName);
        defaultCoins+=coins;
        System.out.println("coins "+coins+" "+defaultCoins);

    }
    private void getEnemyType(String enemyName){
        switch(enemyName) {
            case "Blue dragon":
                life = 1;
                coins = 1;
                break;
            case "Blue head":
                life = 2;
                coins = 2;
                break;
            case "flower monster":
                life = 3;
                coins = 3;
                break;
            case "Ghost":
                life = 4;
                coins = 4;
                break;
            case "Spooky tree":
                life = 5;
                coins = 5;
                break;
        }

    }

    public int getCoins() {
        return defaultCoins;
    }

    public int getLife()  {
        return defaultLife;
    }
}
