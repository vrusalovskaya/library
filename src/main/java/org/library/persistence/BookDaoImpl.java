package org.library.persistence;

import org.library.persistence.entities.BookEntity;
import org.library.persistence.exceptions.BookEntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDaoImpl implements BookDao {

    private static final ArrayList<BookEntity> bookStorage = new ArrayList<>();
    private static Integer count = 0;

    @Override
    public BookEntity addBook(String title, String author) {
        BookEntity newBook = new BookEntity(++count, title, author, true);
        bookStorage.add(newBook);
        return newBook;
    }

    @Override
    public List<BookEntity> getBooksByTitle(String title) {
        ArrayList<BookEntity> books = new ArrayList<>();
        for (BookEntity bookEntity : bookStorage) {
            if (Objects.equals(bookEntity.getTitle(), title)) {
                books.add(bookEntity);
            }
        }
        return books;
    }

    @Override
    public List<BookEntity> getBooks() {
        return new ArrayList<>(bookStorage);
    }

    @Override
    public BookEntity getBookById(Integer id) throws BookEntityNotFoundException {
        for (BookEntity bookEntity : bookStorage) {
            if (Objects.equals(bookEntity.getId(), id)) {
                return bookEntity;
            }
        }
        throw new BookEntityNotFoundException(id);
    }

    @Override
    public void updateStatus(Integer id, Boolean available) throws BookEntityNotFoundException {
        BookEntity updatedBook = getBookById(id);
        updatedBook.setIsAvailable(available);
    }
}
