package org.cis1200.twentyfortyeight;

import java.util.*;

/**
 * Model class for the 2048 game.
 * This class encapsulates all the game logic and state.
 */
public class TwentyFortyEightModel {

    private static final int GRID_SIZE = 4;
    private int[][] grid;
    private int score;
    private boolean gameOver;

    public TwentyFortyEightModel() {
        reset();
    }

    public void reset() {
        grid = new int[GRID_SIZE][GRID_SIZE];
        score = 0;
        gameOver = false;
        addRandomTile();
        addRandomTile();
    }

    public int getGridSize() {
        return GRID_SIZE;
    }

    public int getCell(int row, int col) {
        return grid[row][col];
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void move(Direction direction) {
        if (gameOver) {
            return;
        }

        boolean moved = false;
        switch (direction) {
            case UP -> moved = moveVertical(-1);
            case DOWN -> moved = moveVertical(1);
            case LEFT -> moved = moveHorizontal(-1);
            case RIGHT -> moved = moveHorizontal(1);
            default -> throw new IllegalArgumentException("Unexpected direction: " + direction);
        }

        if (moved) {
            addRandomTile();
            if (!canMove()) {
                gameOver = true;
            }
        }
    }

    private boolean moveVertical(int dir) {
        boolean moved = false;
        for (int col = 0; col < GRID_SIZE; col++) {
            int[] column = new int[GRID_SIZE];
            for (int row = 0; row < GRID_SIZE; row++) {
                column[row] = grid[row][col];
            }

            int[] merged = merge(column, dir);
            for (int row = 0; row < GRID_SIZE; row++) {
                if (grid[row][col] != merged[row]) {
                    moved = true;
                    grid[row][col] = merged[row];
                }
            }
        }
        return moved;
    }

    private boolean moveHorizontal(int dir) {
        boolean moved = false;
        for (int row = 0; row < GRID_SIZE; row++) {
            int[] line = grid[row].clone();
            int[] merged = merge(line, dir);
            if (!Arrays.equals(grid[row], merged)) {
                moved = true;
                grid[row] = merged;
            }
        }
        return moved;
    }

    private int[] merge(int[] line, int dir) {
        LinkedList<Integer> tiles = new LinkedList<>();
        for (int value : line) {
            if (value != 0) {
                tiles.add(value);
            }
        }

        if (dir == -1) {
            for (int i = 0; i < tiles.size() - 1; i++) {
                if (tiles.get(i).equals(tiles.get(i + 1))) {
                    tiles.set(i, tiles.get(i) * 2);
                    score += tiles.get(i);
                    tiles.remove(i + 1);
                }
            }
        } else {
            for (int i = tiles.size() - 1; i > 0; i--) {
                if (tiles.get(i).equals(tiles.get(i - 1))) {
                    tiles.set(i, tiles.get(i) * 2);
                    score += tiles.get(i);
                    tiles.remove(i - 1);
                }
            }
        }

        int[] merged = new int[GRID_SIZE];
        if (dir == -1) {
            for (int i = 0; i < tiles.size(); i++) {
                merged[i] = tiles.get(i);
            }
        } else {
            for (int i = 0; i < tiles.size(); i++) {
                merged[GRID_SIZE - 1 - i] = tiles.get(tiles.size() - 1 - i);
            }
        }
        return merged;
    }

    private void addRandomTile() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grid[row][col] == 0) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        if (!emptyCells.isEmpty()) {
            int[] cell = emptyCells.get(new Random().nextInt(emptyCells.size()));
            grid[cell[0]][cell[1]] = new Random().nextDouble() < 0.9 ? 2 : 4;
        }
    }

    private boolean canMove() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grid[row][col] == 0) {
                    return true;
                }
                if (row > 0 && grid[row][col] == grid[row - 1][col]) {
                    return true;
                }
                if (row < GRID_SIZE - 1 && grid[row][col] == grid[row + 1][col]) {
                    return true;
                }
                if (col > 0 && grid[row][col] == grid[row][col - 1]) {
                    return true;
                }
                if (col < GRID_SIZE - 1 && grid[row][col] == grid[row][col + 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    public String serializeState() {
        StringBuilder sb = new StringBuilder();
        sb.append(score).append(";");
        for (int[] row : grid) {
            for (int cell : row) {
                sb.append(cell).append(",");
            }
        }
        return sb.toString();
    }

    public void deserializeState(String state) {
        String[] parts = state.split(";");
        score = Integer.parseInt(parts[0]);

        String[] cells = parts[1].split(",");
        int index = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                grid[row][col] = Integer.parseInt(cells[index++]);
            }
        }
    }
}
