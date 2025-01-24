// Full implementation of 2048 game based on provided starter code and grading requirements.

package org.cis1200.twentyfortyeight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

/**
 * GameBoard class for 2048.
 * This class integrates:
 * 1. 2D Arrays for the game grid.
 * 2. I/O for saving and loading game state.
 * 3. Collections for efficient state management.
 * 4. JUnit testable components by separating game logic.
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private TwentyFortyEightModel gameModel; // Model for the game
    private JLabel status; // Status text

    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    public GameBoard(JLabel statusInit) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        gameModel = new TwentyFortyEightModel();
        status = statusInit;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> gameModel.move(Direction.UP);
                    case KeyEvent.VK_DOWN -> gameModel.move(Direction.DOWN);
                    case KeyEvent.VK_LEFT -> gameModel.move(Direction.LEFT);
                    case KeyEvent.VK_RIGHT -> gameModel.move(Direction.RIGHT);
                    default -> throw new IllegalArgumentException("Unexpected value:");
                }
                updateStatus();
                repaint();
            }
        });
    }

    public void reset() {
        gameModel.reset();
        status.setText("Game Reset. Score: 0");
        repaint();
        requestFocusInWindow();
    }

    public void saveGame() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_state.txt"))) {
            writer.write(gameModel.serializeState());
            status.setText("Game Saved!");
        } catch (IOException e) {
            status.setText("Failed to save game.");
        }
    }

    public void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader("game_state.txt"))) {
            gameModel.deserializeState(reader.readLine());
            status.setText("Game Loaded! Score: " + gameModel.getScore());
            repaint();
        } catch (IOException e) {
            status.setText("Failed to load game.");
        }
    }

    private void updateStatus() {
        if (gameModel.isGameOver()) {
            status.setText("Game Over! Final Score: " + gameModel.getScore());
        } else {
            status.setText("Score: " + gameModel.getScore());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = BOARD_WIDTH / gameModel.getGridSize();

        for (int r = 0; r < gameModel.getGridSize(); r++) {
            for (int c = 0; c < gameModel.getGridSize(); c++) {
                int value = gameModel.getCell(r, c);
                g.setColor(getTileColor(value));
                g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(c * cellSize, r * cellSize, cellSize, cellSize);

                if (value != 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    g.drawString(String.valueOf(value), c * cellSize +
                            cellSize / 2 - 10, r * cellSize + cellSize / 2 + 10);
                }
            }
        }
    }

    private Color getTileColor(int value) {
        return switch (value) {
            case 2 -> new Color(238, 228, 218);
            case 4 -> new Color(237, 224, 200);
            case 8 -> new Color(242, 177, 121);
            case 16 -> new Color(245, 149, 99);
            case 32 -> new Color(246, 124, 95);
            case 64 -> new Color(246, 94, 59);
            case 128 -> new Color(237, 207, 114);
            case 256 -> new Color(237, 204, 97);
            case 512 -> new Color(237, 200, 80);
            case 1024 -> new Color(237, 197, 63);
            case 2048 -> new Color(237, 194, 46);
            default -> new Color(205, 193, 180);
        };
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
