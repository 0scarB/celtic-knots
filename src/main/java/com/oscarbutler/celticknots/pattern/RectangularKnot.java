package com.oscarbutler.celticknots.pattern;

import java.util.Arrays;
import com.oscarbutler.celticknots.pattern.exception.NonRectangularGridException;
import com.oscarbutler.celticknots.pattern.exception.NotInCellStatesException;
import com.oscarbutler.celticknots.pattern.exception.ShouldBeFillerCellException;
import com.oscarbutler.celticknots.pattern.exception.InvalidOverlapException;

public class RectangularKnot {

    public static short FILLER_CELL_STATE = 0;
    public static int CELL_STATES_N = 9;
    public static String[][] CELL_STATES_STRINGS = {
        {
            "   ",
            "   ",
            "   "
        },
        {
            "\\ /",
            " / ",
            "/ \\"
        },
        {
            "\\ /",
            " \\ ",
            "/ \\"
        },
        {
            "———",
            "   ",
            "___"
        },
        {
            "———",
            "   ",
            "   "
        },
        {
            "   ",
            "   ",
            "___"
        },
        {
            "| |",
            "| |",
            "| |"
        },
        {
            "|  ",
            "|  ",
            "|  "
        },
        {
            "  |",
            "  |",
            "  |"
        }
    };

    public short[][] grid;
    public boolean cornerIsFiller;
    public boolean overlapStartsBottomLeftToTopRight;
    public int xLength;
    public int yLength;

    public RectangularKnot(
        short[][] grid,
        boolean cornerIsFiller,
        boolean overlapStartsBottomLeftToTopRight
    ) {
        this.cornerIsFiller = cornerIsFiller;
        this.overlapStartsBottomLeftToTopRight = overlapStartsBottomLeftToTopRight;

        this.throwOnNonRectangularGrid(grid);
        this.xLength = grid[0].length;
        this.yLength = grid.length;
        this.throwOnInvalidCellsInGrid(grid);
        this.grid = grid;
    }

    public RectangularKnot(short[][] grid, boolean cornerIsFiller) {
        this(grid, cornerIsFiller, true);
    }

    public RectangularKnot(short[][] grid) {
        this(grid, true, true);
    }

    public String toString() {
        int rowsPerCell = CELL_STATES_STRINGS[0].length;
        int columnsPerCell = CELL_STATES_STRINGS[0][0].length();
        int rowLength = xLength;
        int columnLength = yLength * rowsPerCell;
        String rows[] = new String[columnLength];
        for (int y = 0; y < yLength; y++) {
            String cellRow[][] = new String[rowsPerCell][rowLength];
            for (int x = 0; x < xLength; x++) {
                String[] cellStrings = CELL_STATES_STRINGS[grid[y][x]];
                for (int i = 0; i < rowsPerCell; i++) {
                    cellRow[i][x] = cellStrings[i];
                }
            }
            for (int i = 0; i < rowsPerCell; i++) {
                rows[3 * y + i] = String.join("", cellRow[i]);
            }
        }
        return String.join("\n", rows);
    }

    public boolean isFillerCell(int x, int y) {
        if (cornerIsFiller) {
            return (x + y) % 2 == 0;
        }
        return (x + y) % 2 == 1;
    }

    public short overlapFromY(int y) {
        if (overlapStartsBottomLeftToTopRight) {
            if (y % 2 == 0) {
                return 1;
            }
            return 2;
        }
        if (y % 2 == 0) {
            return 2;
        }
        return 1;
    }

    private void throwOnNonRectangularGrid(
        short[][] grid
    ) throws NonRectangularGridException {
        int row1Length = grid[0].length;
        for (short[] row: grid) {
            if (row.length != row1Length) {
                throw new NonRectangularGridException();
            }
        }
    }

    private static void throwOnNotInCellStates(
        short state
    ) throws NotInCellStatesException {
        if (state < 0 || state >= CELL_STATES_N) {
            throw new NotInCellStatesException();
        }
    }

    private void throwOnNotFillerCell(
        int x, int y, short state
    ) throws ShouldBeFillerCellException {
        if (this.isFillerCell(x, y) && state != FILLER_CELL_STATE) {
            throw new ShouldBeFillerCellException();
        }
    };

    private void throwOnInvalidOverlap(
        int y, short state
    ) throws InvalidOverlapException {
        if ((state == 1 || state == 2) && state != this.overlapFromY(y)) {
            throw new InvalidOverlapException();
        }
    }

    private void throwOnInvalidCell(
        int x, int y, short state
    ) throws NotInCellStatesException, ShouldBeFillerCellException, InvalidOverlapException {
        this.throwOnNotInCellStates(state);
        this.throwOnNotFillerCell(x, y, state);
        this.throwOnInvalidOverlap(y, state);
    }

    private void throwOnInvalidCellsInGrid(
        short[][]grid
    ) throws NotInCellStatesException, ShouldBeFillerCellException {
        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {
                this.throwOnInvalidCell(x, y, grid[y][x]);
            }
        }
    }
}
