package org.library.application.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private final Integer id;
    private final String name;
    private List<Book> bookList;

    public User(Integer id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        bookList = new ArrayList<>(books);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void removeBook(Book book) {
        bookList.remove(book);
    }

    @Override
    public String toString() {
        return "User: " +
                "id = " + id +
                ", name = " + name +
                ", bookList = " + bookList;
    }
}
