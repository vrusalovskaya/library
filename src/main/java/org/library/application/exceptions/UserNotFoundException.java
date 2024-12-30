package org.library.application.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(Integer id){
        super("User with id = " + id + " was not found");
    }
}
