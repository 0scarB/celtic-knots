package com.oscarbutler.celticknots.pattern;

import org.junit.Test;
import static org.junit.Assert.*;
import com.oscarbutler.celticknots.pattern.exception.NonRectangularGridException;
import com.oscarbutler.celticknots.pattern.exception.NotInCellStatesException;
import com.oscarbutler.celticknots.pattern.exception.ShouldBeFillerCellException;
import com.oscarbutler.celticknots.pattern.exception.InvalidOverlapException;

public class RectangularKnotTest {

    @Test
    public void RectangularKnot_FILLER_CELL_STATE() {
        RectangularKnot knot = new RectangularKnot(new short[][]{{}});
        assertEquals(0, knot.FILLER_CELL_STATE);
    }

    @Test
    public void RectangularKnot_CELL_STATES() {
        RectangularKnot knot = new RectangularKnot(new short[][]{{}});
        assertEquals(knot.CELL_STATES_N, knot.CELL_STATES_STRINGS.length);
    }

    @Test
    public void RectangularKnot_initalization() {
        short[][] grid = {{}};
        boolean cornerIsFiller = false;
        boolean overlapStartsBottomLeftToTopRight = true;
        RectangularKnot knot = new RectangularKnot(
            grid,
            cornerIsFiller,
            overlapStartsBottomLeftToTopRight
        );

        assertArrayEquals(grid, knot.grid);
        assertSame(cornerIsFiller, knot.cornerIsFiller);
        assertSame(
            overlapStartsBottomLeftToTopRight,
            knot.overlapStartsBottomLeftToTopRight
        );
    }

    @Test
    public void RectangularKnot_correctDefaults() {
        RectangularKnot knot1 = new RectangularKnot(new short[][]{{}});
        RectangularKnot knot2 = new RectangularKnot(new short[][]{{}}, false);

        assertTrue(knot1.cornerIsFiller);
        assertTrue(knot1.overlapStartsBottomLeftToTopRight);
        assertFalse(knot2.cornerIsFiller);
        assertTrue(knot2.overlapStartsBottomLeftToTopRight);
    }

    @Test(expected = NonRectangularGridException.class)
    public void RectangularKnot_throwsExceptionOnNonRectangularTileReferenceGrid() {
        new RectangularKnot(new short[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        });
    }

    @Test
    public void RectangularKnot_throwsExceptionOnNotInCellStates() {
        for (short state: new short[]{0, 1, 2, 3, 4, 5, 6, 7}) {
            boolean overlapStartsBottomLeftToTopRight;
            if (state == 2) {
                // Avoids InvalidOverlapException being thrown.
                overlapStartsBottomLeftToTopRight = false;
            } else {
                overlapStartsBottomLeftToTopRight = true;
            }
            new RectangularKnot(new short[][]{{state}}, false, overlapStartsBottomLeftToTopRight);
        }
        try {
            new RectangularKnot(new short[][]{{-1}});
            fail();
        } catch(NotInCellStatesException e) {}
        try {
            new RectangularKnot(new short[][]{{9}});
            fail();
        } catch(NotInCellStatesException e) {}
    }

    @Test
    public void RectangularKnot_throwsExceptionOnNotFillerCell() {
        new RectangularKnot(new short[][]{{RectangularKnot.FILLER_CELL_STATE}}, true);
        try {
            new RectangularKnot(new short[][]{{1}}, true);
            fail();
        } catch(ShouldBeFillerCellException e) {}

        new RectangularKnot(new short[][]{{1, RectangularKnot.FILLER_CELL_STATE}}, false);
        try {
            new RectangularKnot(new short[][]{{RectangularKnot.FILLER_CELL_STATE, 1}}, false);
            fail();
        } catch(ShouldBeFillerCellException e) {}
    }

    @Test
    public void RectangularKnot_throwsExceptionOnInvalidOverlap() {
        new RectangularKnot(new short[][]{
            {0, 1},
            {2, 0}
        }, true, true);
        try {
            new RectangularKnot(new short[][]{
                {0, 2},
                {1, 0},
            }, true, true);
            fail();
        } catch(InvalidOverlapException e) {}

        new RectangularKnot(new short[][]{
            {0, 2},
            {1, 0}
        }, true, false);
        try {
            new RectangularKnot(new short[][]{
                {0, 1},
                {2, 0},
            }, true, false);
            fail();
        } catch(InvalidOverlapException e) {}
    }

    @Test
    public void isFillerCell_cornerIsFillerTrue() {
        RectangularKnot knot = new RectangularKnot(
            new short[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
            },
            true
        );
        boolean[][] isFillerCellExpectedResults = {
            {true, false, true, false},
            {false, true, false, true},
            {true, false, true, false},
            {false, true, false, true},
            {true, false, true, false}
        };
        for (int y = 0; y < isFillerCellExpectedResults.length; y++) {
            for (int x = 0; x < isFillerCellExpectedResults[0].length; x++) {
                assertEquals(isFillerCellExpectedResults[y][x], knot.isFillerCell(x, y));
            }
        }
    }

    @Test
    public void isFillerCell_cornerIsFillerFalse() {
        RectangularKnot knot = new RectangularKnot(
            new short[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
            },
            false
        );
        boolean[][] isFillerCellExpectedResults = {
            {false, true, false, true, false},
            {true, false, true, false, true},
            {false, true, false, true, false},
            {true, false, true, false, true}
        };
        for (int y = 0; y < isFillerCellExpectedResults.length; y++) {
            for (int x = 0; x < isFillerCellExpectedResults[0].length; x++) {
                assertEquals(isFillerCellExpectedResults[y][x], knot.isFillerCell(x, y));
            }
        }
    }

    @Test
    public void overlapFromY_overlapStartsBottomLeftToTopRightTrue() {
        RectangularKnot knot = new RectangularKnot(new short[][]{{}}, true, true);
        assertEquals(1, knot.overlapFromY(0));
        assertEquals(2, knot.overlapFromY(1));
        assertEquals(1, knot.overlapFromY(2));
        assertEquals(2, knot.overlapFromY(3));
        assertEquals(1, knot.overlapFromY(4));
        assertEquals(2, knot.overlapFromY(5));
    }

    @Test
    public void overlapFromY_overlapStartsBottomLeftToTopRightFalse() {
        RectangularKnot knot = new RectangularKnot(new short[][]{{}}, true, false);
        assertEquals(2, knot.overlapFromY(0));
        assertEquals(1, knot.overlapFromY(1));
        assertEquals(2, knot.overlapFromY(2));
        assertEquals(1, knot.overlapFromY(3));
        assertEquals(2, knot.overlapFromY(4));
        assertEquals(1, knot.overlapFromY(5));
    }

    @Test
    public void toString_returnsCorrectString() {
        String expectedString = String.join("\n", new String[]{
            "                           ",
            "                           ",
            "   ___   ___   ___   ___   ",
            "  |   \\ /   | |   \\ /   |  ",
            "  |    \\    | |    \\    |  ",
            "  |   / \\   | |   / \\   |  ",
            "   \\ /   \\ /   \\ /   \\ /   ",
            "    /     /     /     /    ",
            "   / \\   / \\   / \\   / \\   ",
            "  |   ———   \\ /   ———   |  ",
            "  |          \\          |  ",
            "  |   ___   / \\   ___   |  ",
            "   \\ /   \\ /   \\ /   \\ /   ",
            "    /     /     /     /    ",
            "   / \\   / \\   / \\   / \\   ",
            "  |   \\ /   | |   \\ /   |  ",
            "  |    \\    | |    \\    |  ",
            "  |   / \\   | |   / \\   |  ",
            "   ———   ———   ———   ———   ",
            "                           ",
            "                           "
        });
        assertEquals(
            expectedString,
            new RectangularKnot(new short[][]{
                {0, 5, 0, 5, 0, 5, 0, 5, 0},
                {8, 0, 2, 0, 6, 0, 2, 0, 7},
                {0, 1, 0, 1, 0, 1, 0, 1, 0},
                {8, 0, 3, 0, 2, 0, 3, 0, 7},
                {0, 1, 0, 1, 0, 1, 0, 1, 0},
                {8, 0, 2, 0, 6, 0, 2, 0, 7},
                {0, 4, 0, 4, 0, 4, 0, 4, 0}
            }, true, true).toString()
        );
    }
}
