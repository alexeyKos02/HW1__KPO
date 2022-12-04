import java.util.List;

public class Computer extends Player{
  private final Boolean easy;
  Computer(String name, String surname, cellState state,Boolean mode) {
    super(name, surname, state);
    easy = mode;
  }

  @Override
  public boolean chooseCell(Cell[][] field){
    var cells = FindCorrectCell(field);
    if (cells.size()==0){
      return false;
    }
    inGameInfoCell max;
    if (easy){
      max = easyMode(cells);
    } else{
      max = hardMode(field,cells);
    }
    if (state==cellState.IsWhite){
      max.cell.setState(cellState.IsWhite);
    } else{
      max.cell.setState(cellState.IsBlack);
    }
    for (var cell: max.reverse){
      if (cell.getState().equals(cellState.IsBlack)){
        cell.setState(cellState.IsWhite);
      } else{
        cell.setState(cellState.IsBlack);
      }
    }
    return true;
  }
  private inGameInfoCell easyMode(List<inGameInfoCell> cells){
    var max = cells.get(0);
    for (var cell:cells){
      if (cell.value>max.value){
        max = cell;
      }
    }
    return max;
  }

  private inGameInfoCell hardMode(Cell[][] field, List<inGameInfoCell> cells){
    for (var cell: cells){
      Double before = cell.value;
      if (state==cellState.IsWhite){
        cell.cell.setState(cellState.IsWhite);
      } else{
        cell.cell.setState(cellState.IsBlack);
      }
      if (state==cellState.IsWhite){
       state = cellState.IsBlack;
      } else{
        state = cellState.IsWhite;
      }
      for (var reverseCell:cell.reverse){
        if (reverseCell.getState()==cellState.IsWhite){
          reverseCell.setState(cellState.IsBlack);
        } else{
          reverseCell.setState(cellState.IsWhite);
        }
      }
      var cellsAfter = FindCorrectCell(field);
      if (cellsAfter.size()!=0){
        var max = easyMode(cellsAfter);
        cell.value = before - max.value;
      }
      if (state==cellState.IsWhite){
        state = cellState.IsBlack;
      } else{
        state = cellState.IsWhite;
      }
      cell.cell.setState(cellState.none);
      for (var reverseCell:cell.reverse){
        if (reverseCell.getState()==cellState.IsWhite){
          reverseCell.setState(cellState.IsBlack);
        } else{
          reverseCell.setState(cellState.IsWhite);
        }
      }
    }
    return easyMode(cells);
  }
}
