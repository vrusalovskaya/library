package org.library.application.exceptions;

public class BookNotFoundInUserListException extends Exception{
    public BookNotFoundInUserListException(Integer userId, Integer bookId){
        super("The book with id = " + bookId + " is not found in the list of the user with id = " + userId);
    }
}
