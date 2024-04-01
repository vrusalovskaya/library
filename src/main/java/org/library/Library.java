package org.library;

public class Library implements Storage {
    private final Storage storage;

    public Library(Storage storage) {
        this.storage = storage;
    }

    @Override
    public LibraryItem search(String isbn) {
        return storage.search(isbn);
    }

}
