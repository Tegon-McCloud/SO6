package so6.util;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntVec2 intVec2 = (IntVec2) o;
        return x == intVec2.x &&
                y == intVec2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public IntVec2 copy() {
        return new IntVec2(x, y);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
