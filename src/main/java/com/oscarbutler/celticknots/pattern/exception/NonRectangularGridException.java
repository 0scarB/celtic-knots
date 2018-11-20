package com.oscarbutler.celticknots.pattern.exception;

public class NonRectangularGridException extends IllegalArgumentException {
  public NonRectangularGridException() {
    super("All rows in the grid must have the same length!");
  }
}
