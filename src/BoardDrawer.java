import java.util.List;
import java.util.Scanner;

public class BoardDrawer {
    static Scanner in = new Scanner(System.in);

    static void drawField(Cell[][] field) {
        System.out.print("X\n");
        for (int i = 0; i < 8; i++) {
            System.out.println("    +-----+-----+-----+-----+-----+-----+-----+-----+");
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    System.out.print((i + 1) + "   |  ");
                    System.out.print(field[i][j].getState().equals(cellState.none) ? "   |" :
                            field[i][j].getState().equals(cellState.IsBlack) ? "◯  |" : "●  |");
                } else {
                    System.out.print(field[i][j].getState().equals(cellState.none) ? "     |" :
                            field[i][j].getState().equals(cellState.IsBlack) ? "  ◯  |" : "  ●  |");
                }

            }
            System.out.print("\n");
        }
        System.out.println("    +-----+-----+-----+-----+-----+-----+-----+-----+");
        System.out.println("Y      1     2     3     4     5     6     7     8");
        CountOfChips(field);
    }

    static public void CountOfChips(Cell[][] field) {
        int whiteCount = 0;
        int blackCount = 0;
        for (var line : field) {
            for (var cell : line) {
                if (cell.getState() == cellState.IsWhite) {
                    whiteCount++;
                } else if (cell.getState() == cellState.IsBlack) {
                    blackCount++;
                }
            }
        }
        System.out.printf("Number of whites: %d \n", whiteCount);
        System.out.printf("Number of blacks: %d \n", blackCount);
    }

    static int playerChooses(List<inGameInfoCell> cells) {
        System.out.println("Выберете клетку");
        int count = 1;
        for (var cell : cells) {
            System.out.printf("%d. x=%d; y=%d \n", count, cell.cell.getX(), cell.cell.getY());
            count++;
        }
        Scanner in = new Scanner(System.in);
        int num = 0;
        boolean check = true;
        while (check) {
            try {
                num = Integer.parseInt(in.next());
                if (num < 1 || num > cells.size()) {
                    System.out.println("Incorrect number");
                    continue;
                }
                check = false;
            } catch (Exception e) {
                System.out.println("Incorrect data");
            }
        }
        return num;
    }

    static boolean checkBackup() {
        System.out.print("  Do you want to cancel the selection?\n");
        System.out.print("1. Yes\n");
        System.out.print("2. No\n");
        boolean check = true;
        int num;
        while (check) {
            try {
                String number = in.next();
                num = Integer.parseInt(number);
                check = false;
                if (num < 0 || num > 2) {
                    System.out.print("Incorrect data");
                    check = true;
                } else return num == 1;
            } catch (Exception e) {
                System.out.print("Incorrect data");
            }
        }
        return false;
    }

    static Game startGame() {
        System.out.print("  SELECT THE GAME MODE\n");
        System.out.print("1. 1 VS 1\n");
        System.out.print("2. 1 VS Computer\n");
        boolean check = true;
        int num = 0;
        while (check) {
            try {
                System.out.print("Choose mode:\n");
                String number = in.next();
                num = Integer.parseInt(number);
                check = false;
                if (num < 0 || num > 2) {
                    System.out.print("Incorrect data");
                    check = true;
                }
            } catch (Exception e) {
                System.out.print("Incorrect data");
            }
        }
        System.out.print("Enter name:\n");
        String firstName = in.next();
        System.out.print("Enter surname:\n");
        String firstSurname = in.next();
        if (num == 1) {
            System.out.print("Enter name:\n");
            String secondName = in.next();
            System.out.print("Enter surname:\n");
            String secondSurname = in.next();
            return new Game(firstName, firstSurname, secondName, secondSurname, false);
        } else {
            System.out.print("Choose level:\n");
            System.out.print("1.Easy\n");
            System.out.print("2.Hard\n");
            check = true;
            while (check) {
                try {
                    String number = in.next();
                    num = Integer.parseInt(number);
                    if (num < 0 || num > 2) {
                        System.out.print("Incorrect data");
                    } else if (num == 1) {
                        return new Game(firstName, firstSurname, "Bob", "Computer", true);
                    } else {
                        return new Game(firstName, firstSurname, "Bob", "Computer", false);
                    }
                } catch (Exception e) {
                    System.out.print("Incorrect data");
                }
            }
        }
        return null;
    }

    static boolean endGame() {
        System.out.print("New round?\n");
        System.out.print("1.Yes\n");
        System.out.print("2.No\n");
        boolean check = true;
        int num;
        while (check) {
            try {
                String number = in.next();
                num = Integer.parseInt(number);
                if (num < 0 || num > 2) {
                    System.out.print("Incorrect data");
                } else return num == 1;
            } catch (Exception e) {
                System.out.print("Incorrect data");
            }
        }
        return true;
    }
}
