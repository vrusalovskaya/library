package org.library;

public class ConsolePrinter implements Printer {
    @Override
    public void print(LibraryItem item) {
        if (item instanceof LocalizedBook localizedBook) {
            System.out.println("Title: " + localizedBook.getTitle() + "\nAuthor: " + localizedBook.getAuthor() + "\nTranslated author: " + localizedBook.getTranslatedAuthor() + "\nYear of publication: " + localizedBook.getPublicationYear() + "\nISBN: " + localizedBook.getISBN());
        } else if (item instanceof Book) {
            System.out.println("Title: " + item.getTitle() + "\nAuthor: " + item.getAuthor() + "\nYear of publication: " + item.getPublicationYear() + "\nISBN: " + item.getISBN());
        } else if (item instanceof Magazine magazine) {
            System.out.println("Title: " + magazine.getTitle() + "\nAuthor: " + magazine.getAuthor() + "\nYear: " + magazine.getPublicationYear() + "\nIssue number: " + magazine.getIssueNumber());
        }
    }
}
