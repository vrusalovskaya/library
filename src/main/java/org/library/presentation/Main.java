package org.library.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.library.application.BookExporter;
import org.library.application.Library;
import org.library.application.LibraryImpl;
import org.library.application.exceptions.*;
import org.library.application.models.Book;
import org.library.application.models.User;
import org.library.persistence.BookDaoImpl;
import org.library.persistence.UserDaoImpl;

import java.util.Map;

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

        try{
            System.out.println(BookExporter.toJson(borrowedByTom));
            System.out.println(BookExporter.toJson(library.getBooks()));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        try{
            System.out.println(BookExporter.toXml(borrowedByTom));
            System.out.println(BookExporter.toXml(library.getBooks()));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

    }


    private static <T> void printList(Iterable<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
        System.out.println();
    }
}