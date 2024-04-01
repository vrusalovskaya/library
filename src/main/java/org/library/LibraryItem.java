package org.library;

public abstract class LibraryItem {
    private final String title;
    private final String author;
    private final Integer publicationYear;
    private final String ISBN;

    LibraryItem(String title, String author, Integer year, String ISBN) {
        this.title = title;
        this.author = author;
        this.publicationYear = year;
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public String getISBN() {
        return ISBN;
    }

}
