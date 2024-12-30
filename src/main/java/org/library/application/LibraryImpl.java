package org.library.application;

import org.library.application.exceptions.*;
import org.library.application.mappers.BookMapper;
import org.library.application.mappers.UserMapper;
import org.library.application.models.Book;
import org.library.application.models.User;
import org.library.persistence.BookDao;
import org.library.persistence.UserDao;
import org.library.persistence.entities.BookEntity;
import org.library.persistence.exceptions.BookEntityNotFoundException;
import org.library.persistence.exceptions.BookEntityNotFoundInUserListException;
import org.library.persistence.exceptions.UserEntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryImpl implements Library {
    private final BookDao bookDao;
    private final UserDao userDao;
    private static final Integer BOOK_LIMIT = 3;

    public LibraryImpl(BookDao books, UserDao users) {
        this.bookDao = books;
        this.userDao = users;
    }

    public Book addBook(String title, String author) {
        return BookMapper.toModel(bookDao.addBook(title, author));
    }

    @Override
    public User addUser(String name) {
        return UserMapper.toModel(userDao.addUser(name));
    }

    @Override
    public List<Book> getBooks() {
        List<BookEntity> bookEntities = bookDao.getBooks();
        return bookEntities.stream().map(BookMapper::toModel).toList();
    }

    @Override
    public Map<Book, User> getBorrowedBooks() {
        List<User> users = userDao.getUsersWithBooks().stream().map(UserMapper::toModel).toList();
        HashMap<Book, User> borrowedBooks = new HashMap<>();
        for (User user : users) {
            for (Book book : user.getBookList()) {
                borrowedBooks.put(book, user);
            }
        }
        return borrowedBooks;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers().stream().map(UserMapper::toModel).toList();
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        List<BookEntity> bookEntities = bookDao.getBooksByTitle(title);
        return bookEntities.stream().map(BookMapper::toModel).toList();
    }

    @Override
    public Book provideBook(Integer userId, String title) throws BookNotFoundException, BorrowedBooksLimitReachedException, BookNotAvailableException, UserNotFoundException {
        User borrowingUser;
        try {
            borrowingUser = UserMapper.toModel(userDao.getUserById(userId));
        } catch (UserEntityNotFoundException e) {
            throw new UserNotFoundException(userId);
        }

        List<Book> borrowedBooks = borrowingUser.getBookList();
        if (borrowedBooks.size() >= BOOK_LIMIT) {
            throw new BorrowedBooksLimitReachedException(borrowedBooks.size());
        }

        List<Book> requiredBooks = getBooksByTitle(title);
        if (requiredBooks.isEmpty()) {
            throw new BookNotFoundException(title);
        }

        Book providedBook = requiredBooks.stream()
                .filter(Book::getIsAvailable)
                .findFirst()
                .orElseThrow(BookNotAvailableException::new);

        try {
            bookDao.updateStatus(providedBook.getId(), false);
            providedBook.setIsAvailable(false);
            userDao.addBookEntity(userId, BookMapper.toEntity(providedBook));
            borrowingUser.addBook(providedBook);
        } catch (UserEntityNotFoundException | BookEntityNotFoundException ignored) {
        }

        return providedBook;
    }

    @Override
    public void receiveBook(Integer userId, Integer bookId) throws BookNotFoundException, UserNotFoundException, BookNotFoundInUserListException {
        try {
            BookEntity receivedBookEntity = bookDao.getBookById(bookId);
            userDao.removeBookEntity(userId, receivedBookEntity);
            User user = UserMapper.toModel(userDao.getUserById(userId));
            Book receivedBook = BookMapper.toModel(receivedBookEntity);
            user.removeBook(receivedBook);
            bookDao.updateStatus(bookId, true);
            receivedBook.setIsAvailable(true);
        } catch (UserEntityNotFoundException e) {
            throw new UserNotFoundException(userId);
        } catch (BookEntityNotFoundInUserListException e) {
            throw new BookNotFoundInUserListException(userId, bookId);
        } catch (BookEntityNotFoundException e) {
            throw new BookNotFoundException(bookId);
        }
    }
}
