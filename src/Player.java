import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final String surname;
    protected cellState state;

    private int pointWinner;

    public void setPoint(int number) {
        pointWinner = number;
    }

    public int getPoint() {
        return pointWinner;
    }

    Player(String name, String surname, cellState state) {
        this.name = name;
        this.surname = surname;
        this.state = state;
    }


    public List<Cell> findPotentialCell(Cell[][] field) {
        List<Cell> correct = new ArrayList<>();
        cellState opposition;
        if (state == cellState.IsBlack) {
            opposition = cellState.IsWhite;
        } else {
            opposition = cellState.IsBlack;
        }

        for (var line : field) {
            for (var cell : line) {
                if (cell.getState().equals(cellState.none)) {
                    if (cell.getX() - 1 > 0 && cell.getY() - 1 > 0) {
                        if (field[cell.getX() - 2][cell.getY() - 2].getState().equals(opposition)) {
                            correct.add(field[cell.getX() - 1][cell.getY() - 1]);
                            continue;
                        }
                    }
                    if (cell.getX() - 1 > 0) {
                        if (field[cell.getX() - 2][cell.getY() - 1].getState().equals(opposition)) {
                            correct.add(field[cell.getX() - 1][cell.getY() - 1]);
                            continue;
                        }
                    }
                    if (cell.getX() - 1 > 0 && cell.getY() + 1 < 9) {
                        if (field[cell.getX() - 2][cell.getY()].getState().equals(opposition)) {
                            correct.add(field[cell.getX() - 1][cell.getY() - 1]);
                            continue;
                        }
                    }
                    if (cell.getY() + 1 < 9) {
                        if (field[cell.getX() - 1][cell.getY()].getState().equals(opposition)) {
                            correct.add(field[cell.getX() - 1][cell.getY() - 1]);
                            continue;
                        }
                    }
                    if (cell.getX() + 1 < 9 && cell.getY() + 1 < 9) {
                        if (field[cell.getX()][cell.getY()].getState().equals(opposition)) {
                            correct.add(field[cell.getX() - 1][cell.getY() - 1]);
                            continue;
                        }
                    }
                    if (cell.getX() + 1 < 9) {
                        if (field[cell.getX()][cell.getY() - 1].getState().equals(opposition)) {
                            correct.add(field[cell.getX() - 1][cell.getY() - 1]);
                            continue;
                        }
                    }
                    if (cell.getX() + 1 < 9 && cell.getY() - 1 > 0) {
                        if (field[cell.getX()][cell.getY() - 2].getState().equals(opposition)) {
                            correct.add(field[cell.getX() - 1][cell.getY() - 1]);
                            continue;
                        }
                    }
                    if (cell.getY() - 1 > 0) {
                        if (field[cell.getX() - 1][cell.getY() - 2].getState().equals(opposition)) {
                            correct.add(field[cell.getX() - 1][cell.getY() - 1]);
                        }
                    }
                }
            }
        }
        return correct;
    }

    public List<inGameInfoCell> FindCorrectCell(Cell[][] field) {
        cellState opposition;
        if (state == cellState.IsBlack) {
            opposition = cellState.IsWhite;
        } else {
            opposition = cellState.IsBlack;
        }
        List<Cell> correct = this.findPotentialCell(field);
        List<inGameInfoCell> onlyCorrect = new ArrayList<>();
        for (var cell : correct) {
            inGameInfoCell infoCell = new inGameInfoCell(cell, 0);
            double sum = 0;
            int x = cell.getX();
            int y = cell.getY();
            List<Cell> temporaryCells = new ArrayList<>();
            double temporary = 0;
            while (field[x - 1][y - 1].getState().equals(opposition) || field[x - 1][y - 1].getState().equals(cellState.none)) {
                if (x - 1 > 0 && y - 1 > 0) {
                    x -= 1;
                    y -= 1;
                    if (field[x - 1][y - 1].getState() == cellState.none) {
                        temporaryCells.clear();
                        break;
                    }
                    if (field[x - 1][y - 1].getState() == state) {
                        sum += temporary;
                        infoCell.reverse.addAll(temporaryCells);
                        temporaryCells.clear();
                        break;
                    }
                    if ((x == 8 || x == 1) || (y == 8 || y == 1)) {
                        temporary += 2;
                    } else {
                        temporary += 1;
                    }
                    temporaryCells.add(field[x - 1][y - 1]);
                } else {
                    temporaryCells.clear();
                    break;
                }
            }
            temporary = 0;
            x = cell.getX();
            y = cell.getY();
            while (field[x - 1][y - 1].getState() == opposition || field[x - 1][y - 1].getState() == cellState.none) {
                if (x - 1 > 0) {
                    x -= 1;
                    if (field[x - 1][y - 1].getState() == cellState.none) {
                        temporaryCells.clear();
                        break;
                    }
                    if (field[x - 1][y - 1].getState() == state) {
                        sum += temporary;
                        infoCell.reverse.addAll(temporaryCells);
                        temporaryCells.clear();
                        break;
                    }
                    if ((x == 8 || x == 1) || (y == 8 || y == 1)) {
                        temporary += 2;
                    } else {
                        temporary += 1;
                    }
                    temporaryCells.add(field[x - 1][y - 1]);
                } else {
                    temporaryCells.clear();
                    break;
                }
            }
            temporary = 0;
            x = cell.getX();
            while (field[x - 1][y - 1].getState() == opposition || field[x - 1][y - 1].getState() == cellState.none) {
                if (x - 1 > 0 && y + 1 < 9) {
                    x -= 1;
                    y += 1;
                    if (field[x - 1][y - 1].getState() == cellState.none) {
                        temporaryCells.clear();
                        break;
                    }
                    if (field[x - 1][y - 1].getState() == state) {
                        sum += temporary;
                        infoCell.reverse.addAll(temporaryCells);
                        temporaryCells.clear();
                        break;
                    }
                    if ((x == 8 || x == 1) || y == 8) {
                        temporary += 2;
                    } else {
                        temporary += 1;
                    }
                    temporaryCells.add(field[x - 1][y - 1]);
                } else {
                    temporaryCells.clear();
                    break;
                }
            }
            temporary = 0;
            x = cell.getX();
            y = cell.getY();
            while (field[x - 1][y - 1].getState() == opposition || field[x - 1][y - 1].getState() == cellState.none) {
                if (y + 1 < 9) {
                    y += 1;
                    if (field[x - 1][y - 1].getState() == cellState.none) {
                        temporaryCells.clear();
                        break;
                    }
                    if (field[x - 1][y - 1].getState() == state) {
                        sum += temporary;
                        infoCell.reverse.addAll(temporaryCells);
                        temporaryCells.clear();
                        break;
                    }
                    if ((x == 8 || x == 1) || y == 8) {
                        temporary += 2;
                    } else {
                        temporary += 1;
                    }
                    temporaryCells.add(field[x - 1][y - 1]);
                } else {
                    temporaryCells.clear();
                    break;
                }
            }
            temporary = 0;
            x = cell.getX();
            y = cell.getY();
            while (field[x - 1][y - 1].getState() == opposition || field[x - 1][y - 1].getState() == cellState.none) {
                if (x + 1 < 9 && y + 1 < 9) {
                    x += 1;
                    y += 1;
                    if (field[x - 1][y - 1].getState() == cellState.none) {
                        temporaryCells.clear();
                        break;
                    }
                    if (field[x - 1][y - 1].getState() == state) {
                        sum += temporary;
                        infoCell.reverse.addAll(temporaryCells);
                        temporaryCells.clear();
                        break;
                    }
                    if (x == 8 || y == 8) {
                        temporary += 2;
                    } else {
                        temporary += 1;
                    }
                    temporaryCells.add(field[x - 1][y - 1]);
                } else {
                    temporaryCells.clear();
                    break;
                }
            }
            temporary = 0;
            x = cell.getX();
            y = cell.getY();
            while (field[x - 1][y - 1].getState() == opposition || field[x - 1][y - 1].getState() == cellState.none) {
                if (x + 1 < 9) {
                    x += 1;
                    if (field[x - 1][y - 1].getState() == cellState.none) {
                        temporaryCells.clear();
                        break;
                    }
                    if (field[x - 1][y - 1].getState() == state) {
                        sum += temporary;
                        infoCell.reverse.addAll(temporaryCells);
                        temporaryCells.clear();
                        break;
                    }
                    if (x == 8 || (y == 8 || y == 1)) {
                        temporary += 2;
                    } else {
                        temporary += 1;
                    }
                    temporaryCells.add(field[x - 1][y - 1]);
                } else {
                    temporaryCells.clear();
                    break;
                }
            }
            temporary = 0;
            x = cell.getX();
            y = cell.getY();
            while (field[x - 1][y - 1].getState() == opposition || field[x - 1][y - 1].getState() == cellState.none) {
                if (x + 1 < 9 && y - 1 > 0) {
                    x += 1;
                    y -= 1;
                    if (field[x - 1][y - 1].getState() == cellState.none) {
                        temporaryCells.clear();
                        break;
                    }
                    if (field[x - 1][y - 1].getState() == state) {
                        sum += temporary;
                        infoCell.reverse.addAll(temporaryCells);
                        temporaryCells.clear();
                        break;
                    }
                    if (x == 8 || (y == 8 || y == 1)) {
                        temporary += 2;
                    } else {
                        temporary += 1;
                    }
                    temporaryCells.add(field[x - 1][y - 1]);
                } else {
                    temporaryCells.clear();
                    break;
                }
            }
            temporary = 0;
            x = cell.getX();
            y = cell.getY();
            while (field[x - 1][y - 1].getState() == opposition || field[x - 1][y - 1].getState() == cellState.none) {
                if (y - 1 > 0) {
                    y -= 1;
                    if (field[x - 1][y - 1].getState() == cellState.none) {
                        temporaryCells.clear();
                        break;
                    }
                    if (field[x - 1][y - 1].getState() == state) {
                        sum += temporary;
                        infoCell.reverse.addAll(temporaryCells);
                        temporaryCells.clear();
                        break;
                    }
                    if ((x == 8 || x == 1) || (y == 8 || y == 1)) {
                        temporary += 2;
                    } else {
                        temporary += 1;
                    }
                    temporaryCells.add(field[x - 1][y - 1]);
                } else {
                    temporaryCells.clear();
                    break;
                }
            }
            if (sum > 0) {
                if ((cell.getX() == cell.getY() && (cell.getX() == 1 || cell.getX() == 8)) || (cell.getX() == 1 && cell.getY() == 8) || (cell.getX() == 8 && cell.getY() == 1)) {
                    sum += 0.8;
                } else if ((cell.getX() == 8 || cell.getX() == 1) || (cell.getY() == 8 || cell.getY() == 1)) {
                    sum += 0.4;
                }
                infoCell.value = sum;
                if (infoCell.reverse.size() > 0) {
                    onlyCorrect.add(infoCell);
                }
            }
        }
        return onlyCorrect;
    }

    public boolean chooseCell(Cell[][] field) {
        var cells = FindCorrectCell(field);
        if (cells.size() == 0) {
            return false;
        }
        int num = BoardDrawer.playerChooses(cells);
        if (state == cellState.IsWhite) {
            cells.get(num - 1).cell.setState(cellState.IsWhite);
        } else {
            cells.get(num - 1).cell.setState(cellState.IsBlack);
        }
        for (var cell : cells.get(num - 1).reverse) {
            if (cell.getState().equals(cellState.IsBlack)) {
                cell.setState(cellState.IsWhite);
            } else {
                cell.setState(cellState.IsBlack);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
