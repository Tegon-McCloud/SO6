package so6.util;

public class IntVec2 {
    public int x, y;

    public IntVec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVec2(String str) {
        String[] xy = str.split(",");
        x = Integer.parseInt(xy[0]);
        y = Integer.parseInt(xy[1]);
    }

    public IntVec2 copy() {
        return new IntVec2(x, y);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
