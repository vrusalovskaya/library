package org.library;

public class Main {
    public static void main(String[] args) {
        Storage storage = new ArrayListStorage();
        Library library = new Library(storage);

        Printer consolePrinter = new ConsolePrinter();

        consolePrinter.print(library.search("978-5-9614-6537-2"));

        System.out.println();
        consolePrinter.print(library.search("978-1-85326-175-6"));

        System.out.println();
        consolePrinter.print(library.search("2024-12"));
    }
}