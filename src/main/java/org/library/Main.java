package org.library;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
//        Storage storage = new ArrayListStorage();
//        Library library = new Library(storage);
//
//        Printer consolePrinter = new ConsolePrinter2();
//
//        consolePrinter.print(library.search("978-5-9614-6537-2"));
//
//        System.out.println();
//        consolePrinter.print(library.search("978-1-85326-175-6"));
//
//        System.out.println();
//        consolePrinter.print(library.search("2024-12"));

        Storage storage = new FileStorage("LibraryItems.csv");

        Library library = new Library(storage);

        Printer consolePrinter = new ConsolePrinter();
        consolePrinter.print(library.search("978-5-9614-6859-5"));

        System.out.println();
        consolePrinter.print(library.search("978-5-17-153458-5"));

        System.out.println();
        consolePrinter.print(library.search("2024-24"));
    }
}