package org.library.application.exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(Integer id){
        super("The book with id = " + id + " was not found");
    }

    public BookNotFoundException(String title){
        super("The book with " + title + " title was not found");
    }
}
