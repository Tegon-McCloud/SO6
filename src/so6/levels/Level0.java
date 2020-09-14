package so6.levels;

import so6.base.level.Level;

import java.io.IOException;

public class Level0 extends Level {

    public Level0() throws IOException {
        super();
    }

    @Override
    protected String getName() {
        return "level0";
    }

    @Override
    protected boolean isPath(int x, int y) {
        return y == 1;
    }
}
