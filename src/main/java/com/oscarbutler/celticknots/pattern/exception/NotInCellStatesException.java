package com.oscarbutler.celticknots.pattern.exception;

public class NotInCellStatesException extends IllegalArgumentException {

    public NotInCellStatesException() {
        super("Invalid cell state! Valid cell states are 0, 1, 2, 3, 4, 5, 6, 7, 8.");
    }
}
