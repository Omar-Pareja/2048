package org.cis1200.twentyfortyeight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;


public class GameBoardTest {

    @Test
    public void testGameBoardReset() {
        JLabel status = new JLabel();
        GameBoard board = new GameBoard(status);

        board.reset();

        assertEquals("Game Reset. Score: 0", status.getText(), "Reset should update the status.");
    }

    @Test
    public void testGameSaveAndLoad() {
        JLabel status = new JLabel();
        GameBoard board = new GameBoard(status);

        board.reset();
        board.saveGame();
        String initialStatus = status.getText();

        board.reset();
        board.loadGame();

        assertEquals("Game Saved!", initialStatus, "Save status should indicate successful save.");
        assertEquals("Game Loaded! Score: 0", status.getText(), "Load should indicate successful " +
                "load and restore state.");
    }

    @Test
    public void testComponentRendering() {
        JLabel status = new JLabel();
        GameBoard board = new GameBoard(status);

        Dimension expectedSize = new Dimension(GameBoard.BOARD_WIDTH, GameBoard.BOARD_HEIGHT);
        assertEquals(expectedSize, board.getPreferredSize(), "Preferred size of the game " +
                "board should " + "match constants.");
    }
}
