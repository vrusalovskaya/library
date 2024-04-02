package org.library;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class Library implements Storage {
    private final Storage storage;

    public Library(Storage storage) {
        this.storage = storage;
    }

    @Override
    public LibraryItem search(String isbn) throws CsvValidationException, IOException {
        return storage.search(isbn);
    }
}
