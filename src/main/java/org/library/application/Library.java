package org.library.application;

import org.library.application.exceptions.*;
import org.library.application.models.Book;
import org.library.application.models.User;

import java.util.List;
import java.util.Map;

public interface Library {
    Book addBook(String title, String author);

    User addUser(String name);

    List<Book> getBooks();

    Map<Book, User> getBorrowedBooks();

    List<User> getUsers();

    List<Book> getBooksByTitle(String title);

    Book provideBook(Integer userId, String bookTitle) throws BookNotFoundException, BorrowedBooksLimitReachedException, BookNotAvailableException, UserNotFoundException;

    void receiveBook(Integer userId, Integer bookId) throws BookNotFoundException, UserNotFoundException, BookNotFoundInUserListException;
}
