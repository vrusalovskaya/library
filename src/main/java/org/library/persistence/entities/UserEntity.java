package org.library.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.library.persistence.exceptions.BookEntityNotFoundInUserListException;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserEntity {
    private Integer id;
    private String name;
    private List<BookEntity> bookEntities;

    public void addBookEntity(BookEntity bookEntity) {
        bookEntities.add(bookEntity);
    }

    public void removeBookEntity(BookEntity bookEntity) throws BookEntityNotFoundInUserListException {
       if (!bookEntities.contains(bookEntity)){
           throw new BookEntityNotFoundInUserListException(this.getId(), bookEntity.getId());
       }
        bookEntities.remove(bookEntity);
    }
}
