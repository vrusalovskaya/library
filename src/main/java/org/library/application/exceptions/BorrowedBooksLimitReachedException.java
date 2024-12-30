package org.library.application.exceptions;

public class BorrowedBooksLimitReachedException extends Exception{
    public BorrowedBooksLimitReachedException(Integer bookCount){
        super("The limit has been reached, the user already has " + bookCount + " books.");
    }
}
