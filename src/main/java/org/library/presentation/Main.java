package org.library.presentation;

import org.library.application.Library;
import org.library.application.LibraryImpl;
import org.library.application.models.content.MimeType;
import org.library.application.exceptions.*;
import org.library.application.exporters.BookExporter;
import org.library.application.exporters.JsonBookExporter;
import org.library.application.exporters.XmlBookExporter;
import org.library.application.models.Book;
import org.library.application.models.User;
import org.library.persistence.BookDaoImpl;
import org.library.persistence.UserDaoImpl;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new LibraryImpl(new BookDaoImpl(), new UserDaoImpl());

        Book book1 = library.addBook("Atlas Shrugged", "Ayn Rand");
        System.out.println("The new book was added to the library: \n" + book1 + "\n");

        library.addBook("Martin Eden", "Jack London");
        library.addBook("Martin Eden", "Jack London");
        library.addBook("Flowers for Algernon", "Daniel Keyes");

        System.out.println("The books present in the library:");
        printList(library.getBooks());

        User tom = library.addUser("Tom");
        System.out.println("New user was created: \n" + tom + "\n");

        User bob = library.addUser("Bob");
        System.out.println("New user was created: \n" + bob + "\n");

        Book borrowedByTom = null;
        try {
            borrowedByTom = library.provideBook(tom.getId(), "Martin Eden");
            System.out.println("User " + tom.getName() + " has borrowed the following book: \n" + borrowedByTom + "\n");
        } catch (BorrowedBooksLimitReachedException | BookNotAvailableException | BookNotFoundException |
                 UserNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            Book borrowedByBob = library.provideBook(bob.getId(), "Martin Eden");
            System.out.println("User " + bob.getName() + " has borrowed the following book: \n" + borrowedByBob + "\n");
        } catch (BorrowedBooksLimitReachedException | BookNotAvailableException | BookNotFoundException |
                 UserNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("The list of the borrowed books:");
        Map<Book, User> borrowedBooks = library.getBorrowedBooks();
        for (Map.Entry<Book, User> bookUserEntry : borrowedBooks.entrySet()) {
            System.out.println("[" + bookUserEntry.getKey() + "] is borrowed by [id = "
                    + bookUserEntry.getValue().getId() + ", name = " + bookUserEntry.getValue().getName()
                    + "] user");
        }
        System.out.println();

        System.out.println("The statuses of the books");
        printList(library.getBooks());

        try {
            library.receiveBook(tom.getId(), borrowedByTom.getId());
            System.out.println("The [" + borrowedByTom + "] book was returned to the library  \n");
        } catch (UserNotFoundException | BookNotFoundException | BookNotFoundInUserListException e) {
            System.out.println(e.getMessage() + "\n");
        }

        printList(library.getBooks());
        printList(library.getUsers());

        try {
            Book borrowedBook = library.provideBook(bob.getId(), "Martin Eden");
            System.out.println("User " + bob.getName() + " has borrowed the following book: \n" + borrowedBook + "\n");
        } catch (BorrowedBooksLimitReachedException | BookNotAvailableException | BookNotFoundException |
                 UserNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        //the book does not exist
        try {
            Book borrowedBook = library.provideBook(bob.getId(), "Flowers for Algernon2");
            System.out.println("User " + bob.getName() + " has borrowed the following book: \n" + borrowedBook + "\n");
        } catch (BorrowedBooksLimitReachedException | BookNotAvailableException | BookNotFoundException |
                 UserNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            Book borrowedBook = library.provideBook(bob.getId(), "Atlas Shrugged");
            System.out.println("User " + bob.getName() + " has borrowed the following book: \n" + borrowedBook + "\n");
        } catch (BorrowedBooksLimitReachedException | BookNotAvailableException | BookNotFoundException |
                 UserNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        //the book is already borrowed by another user
        try {
            Book borrowedBook = library.provideBook(tom.getId(), "Atlas Shrugged");
            System.out.println("User " + tom.getName() + " has borrowed the following book: \n" + borrowedBook + "\n");
        } catch (BorrowedBooksLimitReachedException | BookNotAvailableException | BookNotFoundException |
                 UserNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        //4th book should not be provided to user
        try {
            Book borrowedBook = library.provideBook(bob.getId(), "Flowers for Algernon");
            System.out.println("User " + bob.getName() + " has borrowed the following book: \n" + borrowedBook + "\n");
        } catch (BorrowedBooksLimitReachedException | BookNotAvailableException | BookNotFoundException |
                 UserNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        //user does not exist
        try {
            Book borrowedBook = library.provideBook(5, "Flowers for Algernon");
            System.out.println("User " + bob.getName() + " has borrowed the following book: \n" + borrowedBook + "\n");
        } catch (BorrowedBooksLimitReachedException | BookNotAvailableException | BookNotFoundException |
                 UserNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        BookExporter exporter = null;
        BookExporter[] exporters = new BookExporter[]{new JsonBookExporter(), new XmlBookExporter()};

        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Specify the format for exporting books: ");
            var enums = MimeType.values();
            int enumsQuantity = enums.length;
            for (int i = 1; i <= enumsQuantity; i++) {
                System.out.println(i + ". " + enums[i - 1]);
            }
            String format = in.nextLine().toUpperCase();

            var type = MimeType.valueOf(format);
            exporter = Arrays.stream(exporters).filter(e -> e.canProcess(type)).findFirst().get();

            try {
                System.out.println("The books were exported to the " + format + " format:");
                printList(exporter.export(library.getBooks()));
            } catch (BookExportFailedException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static <T> void printList(Iterable<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
        System.out.println();
    }
}