package org.library.persistence;

import org.library.persistence.entities.BookEntity;
import org.library.persistence.entities.UserEntity;
import org.library.persistence.exceptions.BookEntityNotFoundInUserListException;
import org.library.persistence.exceptions.UserEntityNotFoundException;

import java.util.List;

public interface UserDao {
    UserEntity addUser(String name);

    List<UserEntity> getUsers();

    List<UserEntity> getUsersWithBooks();

    UserEntity getUserById(Integer id) throws UserEntityNotFoundException;

    void addBookEntity(Integer userId, BookEntity bookEntity) throws UserEntityNotFoundException;

    void removeBookEntity(Integer userId, BookEntity bookEntity) throws UserEntityNotFoundException, BookEntityNotFoundInUserListException;
}
