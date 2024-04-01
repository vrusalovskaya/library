package org.library;

public class ConsolePrinter2 implements Printer{
    @Override
    public void print(LibraryItem item) {
        if (item instanceof LocalizedBook localizedBook) {
            System.out.println("Title: " + localizedBook.getTitle() + ", Author: " + localizedBook.getAuthor() + ", Translated author: " + localizedBook.getTranslatedAuthor() + ", Year of publication: " + localizedBook.getPublicationYear() + ", ISBN: " + localizedBook.getISBN());
        } else if (item instanceof Book) {
            System.out.println("Title: " + item.getTitle() + ", Author: " + item.getAuthor() + ", Year of publication: " + item.getPublicationYear() + ", ISBN: " + item.getISBN());
        } else if (item instanceof Magazine magazine) {
            System.out.println("Title: " + magazine.getTitle() + ", Author: " + magazine.getAuthor() + ", Year: " + magazine.getPublicationYear() + ", Issue number: " + magazine.getIssueNumber());
        }
    }
}
