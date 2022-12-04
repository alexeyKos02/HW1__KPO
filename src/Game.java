import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
  Cell [][] field = new Cell[8][8];
  Player[] players = new Player[2];
  List<Cell[][]> backup;
  Player winner;
  public Player getWinner(){
    return winner;
  }
  public Game(String nameFirst, String surnameFirst,String nameSecond, String surnameSecond,Boolean mode){
    backup = new ArrayList<>();
    for (int i = 1; i<9; i++){
      for (int j = 1; j<9; j++){
        field[i-1][j-1] = new Cell(i,j);
        if (i ==j && (i==4||i==5)){
          field[i-1][j-1].setState(cellState.IsBlack);
          continue;
        }
        if ((i == 4 && j ==5) ||(i == 5 && j ==4)){
          field[i-1][j-1].setState(cellState.IsWhite);
        }
      }
    }
    addBackup();
    players[0] = new Player(nameFirst,surnameFirst, cellState.IsWhite);
    if(Objects.equals(nameSecond, "Bob") && Objects.equals(surnameSecond, "Computer")){
      players[1] = new Computer(nameSecond,surnameSecond,cellState.IsBlack,mode );
    } else{
      players[1] = new Player(nameSecond,surnameSecond,cellState.IsBlack);
    }
  }
  public void start(){
    boolean check = true;
    while (check){
      check = round();
      backup.clear();
      addBackup();
    }
    chooseWinner();
  }

  private boolean round(){
    boolean first,second;
    BoardDrawer.drawField(field);
    System.out.print("Ход игрока: "+players[0]+"\n");
    first = move(0);
    BoardDrawer.drawField(field);
    System.out.print("Ход игрока: "+players[1]+"\n");
    second = move(1);
    return first || second;
  }
  private boolean move(int num){
    boolean back = true;
    while (back){
      boolean first =players[num].chooseCell(field);
      if (first){
        BoardDrawer.drawField(field);
        addBackup();
      } else{
        System.out.print("there is no way to make a move\n");
        return false;
      }
      back = BoardDrawer.checkBackup();
      if (back){
        backup.remove(backup.size()-1);
        for (int i = 0; i<8; i++){
          for (int j = 0; j<8; j++){
            field[i][j] = new Cell(i+1,j+1);
            field[i][j].setState(backup.get(backup.size()-1)[i][j].getState());
          }
        }
        BoardDrawer.drawField(field);
      }
    }
    return true;
  }
  private void addBackup(){
    Cell[][] newBackup = new Cell[8][8];
    for (int i = 0; i<8; i++){
      for (int j = 0; j<8; j++){
        newBackup[i][j] = new Cell(i+1,j+1);
        newBackup[i][j].setState(field[i][j].getState());
      }
    }
    backup.add(newBackup);
  }
  public void chooseWinner(){
    int whiteCount = 0;
    int blackCount = 0;
    for (var line:field){
      for (var cell: line){
        if (cell.getState()==cellState.IsWhite){
          whiteCount++;
        } else if (cell.getState()== cellState.IsBlack){
          blackCount++;
        }
      }
    }
    if (whiteCount>blackCount){
      winner = players[0];
      winner.setPoint(whiteCount);
      System.out.print("Winner: "+players[0]+"\n");
    } else if(whiteCount<blackCount){
      winner = players[1];
      winner.setPoint(blackCount);
      System.out.print("Winner: "+players[1]+"\n");
    } else{
      System.out.print("Draw\n");
    }
  }
}