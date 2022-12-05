public class Cell {
    private int x;
    private int y;
    private cellState state;

    public cellState getState() {
        return state;
    }

    public void setState(cellState state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Cell() {
        state = cellState.none;
    }

    public Cell(int x, int y) {
        this();
        this.setX(x);
        this.setY(y);
    }
}