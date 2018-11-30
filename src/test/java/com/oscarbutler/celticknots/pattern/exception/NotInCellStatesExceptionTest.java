package com.oscarbutler.celticknots.pattern.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class NotInCellStatesExceptionTest {

    @Test
    public void NotInCellStatesException_CorrectStackTraceString() {
        IllegalArgumentException notInCellStatesException = new NotInCellStatesException();
        String expectedExeceptionMessage = (
            "com.oscarbutler.celticknots.pattern.exception.NotInCellStatesException: "
            + "Invalid cell state! Valid cell states are 0, 1, 2, 3, 4, 5, 6, 7, 8."
        );

        try {
            throw notInCellStatesException;
        } catch(IllegalArgumentException e) {
            assertEquals(expectedExeceptionMessage, e.toString());
        }
        try {
            throw notInCellStatesException;
        } catch(NotInCellStatesException e) {
            assertEquals(expectedExeceptionMessage, e.toString());
        }
    }
}
