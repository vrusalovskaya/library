package org.library.application.mappers;

import org.library.application.models.Book;
import org.library.persistence.entities.BookEntity;

public class BookMapper {

    private BookMapper() {
    }

    public static BookEntity toEntity(Book book) {
        return new BookEntity(book.getId(), book.getTitle(), book.getAuthor(), book.getIsAvailable());
    }

    public static Book toModel(BookEntity bookEntity) {
        return new Book(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getIsAvailable());
    }
}
