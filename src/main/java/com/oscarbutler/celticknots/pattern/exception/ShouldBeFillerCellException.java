package com.oscarbutler.celticknots.pattern.exception;

import com.oscarbutler.celticknots.pattern.RectangularKnot;

public class ShouldBeFillerCellException extends IllegalArgumentException {

    public ShouldBeFillerCellException() {
        super(
            "Cell should have the filler cell state i.e. "
            + RectangularKnot.FILLER_CELL_STATE + "!"
        );
    }
}
