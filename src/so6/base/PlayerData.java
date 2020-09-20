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
                life = 40;
                coins = 150;
                break;
            case "Blue head":
                life = 2;
                coins = 2;
                break;
            case "Flower monster":
                life = 10;
                coins = 20;
                break;
            case "Ghost":
                life = 30;
                coins = 40;
                break;
            case "Spooky tree":
                life = 80;
                coins = 100;
                break;
        }

    }

    public int getCoins() {
        return defaultCoins;
    }

    public int getLife()  {
        return defaultLife;
    }

    public boolean trySpend(int price){
        if(defaultCoins >= price){
            defaultCoins -= price;
            return true;
        } else {
            return false;
        }

    }
}
