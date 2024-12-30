package org.library.persistence.exceptions;

public class BookEntityNotFoundInUserListException extends Exception {
    public BookEntityNotFoundInUserListException(Integer userId, Integer bookId){
        super("The book with id = " + bookId + " is not found in the list of the user with id = " + userId);
    }
}
