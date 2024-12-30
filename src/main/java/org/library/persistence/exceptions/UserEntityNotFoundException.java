package org.library.persistence.exceptions;

public class UserEntityNotFoundException extends Exception{
    public UserEntityNotFoundException(Integer id){
        super("The user with id = " + id + " was not found");
    }
}
