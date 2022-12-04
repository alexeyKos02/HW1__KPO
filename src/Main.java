import java.util.ArrayList;
import java.util.List;

public class Main {
  static List<Player> winners = new ArrayList<>();
  public static void main(String[] args) {
    boolean isContinue = true;
    while (isContinue){
      Game game = BoardDrawer.startGame();
      assert game != null;
      game.start();
      winners.add(game.getWinner());
      record();
      isContinue = BoardDrawer.endGame();
    }
  }
  private static void record(){
    Player max = winners.get(0);
    for (var winner: winners){
      if(max.getPoint()<winner.getPoint()){
        max = winner;
      }
    }
    System.out.printf("current record "+max+" : %d\n",max.getPoint());
  }
}