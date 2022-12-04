import java.util.ArrayList;
import java.util.List;

public class inGameInfoCell  implements Comparable <inGameInfoCell> {
    Cell cell;
    public Double value;
    List<Cell> reverse;
    public inGameInfoCell(Cell cell, double value){
        this.cell = cell;
        this.value = value;
        reverse = new ArrayList<>();
    }

    @Override
    public int compareTo(inGameInfoCell o) {
        return this.value.compareTo(o.value);
    }
}
