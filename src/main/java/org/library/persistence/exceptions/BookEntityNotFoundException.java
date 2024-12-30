package org.library.persistence.exceptions;

public class BookEntityNotFoundException extends Exception{
    public BookEntityNotFoundException(Integer id){
        super("The book with id = " + id + " was not found");
    }
}
