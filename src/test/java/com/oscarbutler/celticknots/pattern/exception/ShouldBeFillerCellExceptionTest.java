package com.oscarbutler.celticknots.pattern.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShouldBeFillerCellExceptionTest {

    @Test
    public void ShouldBeFillerCellException_CorrectStackTraceString() {
        IllegalArgumentException shouldBeFillerCellException = new ShouldBeFillerCellException();
        String expectedExeceptionMessage = (
            "com.oscarbutler.celticknots.pattern.exception.ShouldBeFillerCellException: "
            + "Cell should have the filler cell state i.e. 0!"
        );

        try {
            throw shouldBeFillerCellException;
        } catch(IllegalArgumentException e) {
            assertEquals(expectedExeceptionMessage, e.toString());
        }
        try {
            throw shouldBeFillerCellException;
        } catch(ShouldBeFillerCellException e) {
            assertEquals(expectedExeceptionMessage, e.toString());
        }
    }
}
