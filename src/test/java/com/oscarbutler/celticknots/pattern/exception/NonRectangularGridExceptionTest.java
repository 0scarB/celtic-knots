package com.oscarbutler.celticknots.pattern.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class NonRectangularGridExceptionTest {

  @Test
  public void NonRectangularGridException_CorrectStackTraceString() {
    IllegalArgumentException nonRectangularGridException = new NonRectangularGridException();
    String expectedExeceptionMessage = (
      "com.oscarbutler.celticknots.pattern.exception.NonRectangularGridException: "
      + "All rows in the grid must have the same length!"
    );

    try {
      throw nonRectangularGridException;
    } catch(IllegalArgumentException e) {
      assertEquals(expectedExeceptionMessage, e.toString());
    }
    try {
      throw nonRectangularGridException;
    } catch(NonRectangularGridException e) {
      assertEquals(expectedExeceptionMessage, e.toString());
    }
  }
}
