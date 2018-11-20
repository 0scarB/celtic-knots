package com.oscarbutler.celticknots.pattern;

import java.util.Arrays;
import com.oscarbutler.celticknots.pattern.exception.NonRectangularGridException;

public class RectangularKnot {

    public short[][] grid;
    public boolean cornerIsOpen;
    public boolean overlapStartsBottomLeftToTopRight;
    public int xLength;
    public int yLength;

    public RectangularKnot(
        short[][] grid,
        boolean cornerIsOpen,
        boolean overlapStartsBottomLeftToTopRight
    ) {
        this.cornerIsOpen = cornerIsOpen;
        this.overlapStartsBottomLeftToTopRight = overlapStartsBottomLeftToTopRight;

        this.throwOnInvalidGrid(grid);
        this.grid = grid;
    }

    public RectangularKnot(short[][] grid, boolean cornerIsOpen) {
        this(grid, cornerIsOpen, true);
    }

    public RectangularKnot(short[][] grid) {
        this(grid, true, true);
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

    // private static void

    private void throwOnInvalidGrid(
        short[][] grid
    ) throws NonRectangularGridException {
        this.throwOnNonRectangularGrid(grid);
    }
}
