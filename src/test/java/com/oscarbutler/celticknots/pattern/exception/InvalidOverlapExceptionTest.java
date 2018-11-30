package com.oscarbutler.celticknots.pattern.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidOverlapExceptionTest {

    @Test
    public void InvalidOverlapException_CorrectStackTraceString() {
        IllegalArgumentException invalidOverlapException = new InvalidOverlapException();
        String expectedExeceptionMessage = (
            "com.oscarbutler.celticknots.pattern.exception.InvalidOverlapException: "
            + "Invalid overlap!"
        );

        try {
            throw invalidOverlapException;
        } catch(IllegalArgumentException e) {
            assertEquals(expectedExeceptionMessage, e.toString());
        }
        try {
            throw invalidOverlapException;
        } catch(InvalidOverlapException e) {
            assertEquals(expectedExeceptionMessage, e.toString());
        }
    }
}
