package org.library;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public interface Storage {
    LibraryItem search(String isbn) throws CsvValidationException, IOException;
}
