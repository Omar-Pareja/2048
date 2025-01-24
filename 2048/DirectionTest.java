package org.cis1200.twentyfortyeight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DirectionTest {

    @Test
    public void testDirectionValues() {
        Direction[] directions = Direction.values();
        assertEquals(4, directions.length, "There should be 4 directions.");
        assertArrayEquals(
                new Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT},
                directions, "The directions should be UP, DOWN, LEFT, RIGHT in order.");
    }

    @Test
    public void testDirectionValueOf() {
        assertEquals(Direction.UP, Direction.valueOf("UP"), "valueOf should return " +
                "UP for 'UP'.");
        assertEquals(Direction.DOWN, Direction.valueOf("DOWN"), "valueOf should " +
                "return DOWN for" + " 'DOWN'.");
        assertEquals(Direction.LEFT, Direction.valueOf("LEFT"), "valueOf should" +
                " return LEFT for " + "'LEFT'.");
        assertEquals(Direction.RIGHT, Direction.valueOf("RIGHT"), "valueOf should " +
                "return RIGHT for" + " 'RIGHT'.");
    }
}
