package org.library.persistence;

import org.library.persistence.entities.BookEntity;
import org.library.persistence.entities.UserEntity;
import org.library.persistence.exceptions.BookEntityNotFoundInUserListException;
import org.library.persistence.exceptions.UserEntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoImpl implements UserDao {

    public static final ArrayList<UserEntity> userStorage = new ArrayList<>();
    private static Integer count = 0;

    @Override
    public UserEntity addUser(String name) {
        UserEntity newUser = new UserEntity(++count, name, new ArrayList<>());
        userStorage.add(newUser);
        return newUser;
    }

    @Override
    public List<UserEntity> getUsers() {
        return new ArrayList<>(userStorage);
    }

    @Override
    public List<UserEntity> getUsersWithBooks() {
        return userStorage.stream().filter(x -> !(x.getBookEntities().isEmpty())).toList();
    }

    @Override
    public UserEntity getUserById(Integer id) throws UserEntityNotFoundException {
        for (UserEntity entity : userStorage) {
            if (Objects.equals(entity.getId(), id)) {
                return entity;
            }
        }
        throw new UserEntityNotFoundException(id);
    }

    @Override
    public void addBookEntity(Integer userId, BookEntity bookEntity) throws UserEntityNotFoundException {
        UserEntity user = getUserById(userId);
        user.addBookEntity(bookEntity);
    }

    @Override
    public void removeBookEntity(Integer userId, BookEntity bookEntity) throws UserEntityNotFoundException, BookEntityNotFoundInUserListException {
        UserEntity user = getUserById(userId);
        user.removeBookEntity(bookEntity);
    }
}
