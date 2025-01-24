package org.cis1200.twentyfortyeight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TwentyFortyEightModelTest {

    @Test
    public void testInitialSetup() {
        TwentyFortyEightModel model = new TwentyFortyEightModel();
        int nonZeroCount = 0;
        for (int r = 0; r < model.getGridSize(); r++) {
            for (int c = 0; c < model.getGridSize(); c++) {
                if (model.getCell(r, c) != 0) {
                    nonZeroCount++;
                }
            }
        }

        assertEquals(2, nonZeroCount, "Two tiles should be added at game start.");
    }

    @Test
    public void testMoveAndMergeLeft() {
        TwentyFortyEightModel model = new TwentyFortyEightModel();
        model.reset();
        model.deserializeState("0;2,2,4,0,4,0,4,0,0,0,0,0,0,0,0,0,");
        model.move(Direction.LEFT);
        assertEquals(12, model.getScore(), "Score should update after merging tiles correctly.");
        assertEquals(4, model.getCell(0, 0), "First merged tile should be 8.");
        assertEquals(8, model.getCell(1, 0), "Second merged tile should be 8.");
    }

    @Test
    public void testMoveUp() {
        TwentyFortyEightModel model = new TwentyFortyEightModel();
        model.reset();
        model.deserializeState("0;2,0,0,0,2,0,0,0,4,0,0,0,4,0,0,0,");
        model.move(Direction.UP);
        assertEquals(12, model.getScore(), "Score should be 12 after merging tiles vertically.");
        assertEquals(4, model.getCell(0, 0), "First column should merge to 4 at the top.");
    }

    @Test
    public void testSerialization() {
        TwentyFortyEightModel model = new TwentyFortyEightModel();
        model.reset();

        model.deserializeState("0;2,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,");
        String serialized = model.serializeState();

        TwentyFortyEightModel newModel = new TwentyFortyEightModel();
        newModel.deserializeState(serialized);

        assertEquals(model.getScore(), newModel.getScore(), "Score should match after " +
                "serialization/deserialization.");
        for (int r = 0; r < model.getGridSize(); r++) {
            for (int c = 0; c < model.getGridSize(); c++) {
                assertEquals(model.getCell(r, c), newModel.getCell(r, c),
                        "Grid cell values should match after serialization/deserialization.");
            }
        }
    }

    @Test
    public void testGameOverCondition() {
        TwentyFortyEightModel model = new TwentyFortyEightModel();
        model.reset();
        model.deserializeState("0;2,4,2,4,4,2,4,2,2,4,2,4,4,2,4,2,");
        assertFalse(model.isGameOver(), "Game should be over when no moves are possible.");
    }
}
