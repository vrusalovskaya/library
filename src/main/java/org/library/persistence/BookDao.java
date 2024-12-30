package org.library.persistence;

import org.library.persistence.entities.BookEntity;
import org.library.persistence.exceptions.BookEntityNotFoundException;

import java.util.List;

public interface BookDao {
    BookEntity addBook(String title, String author);

    List<BookEntity> getBooksByTitle(String title);

    List<BookEntity> getBooks();

    BookEntity getBookById(Integer id) throws BookEntityNotFoundException;

    void updateStatus(Integer id, Boolean available) throws BookEntityNotFoundException;
}
