package org.library.application.exceptions;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException() {
        super("No books with the required title are currently available");
    }
}
