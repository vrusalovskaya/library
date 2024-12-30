package org.library.application.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Book {
    @EqualsAndHashCode.Include
    private final Integer id;
    private final String title;
    private final String author;
    private Boolean isAvailable;

    @Override
    public String toString() {
        return "Book: " +
                "id = " + id +
                ", title = " + title +
                ", author = " + author +
                ", status = " + (isAvailable ? "available" : "borrowed");
    }
}
