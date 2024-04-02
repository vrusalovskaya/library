package org.library;

public class LibraryItemValidation {

    public static boolean isBook(String value) {
        return value.toUpperCase().trim().equals(LibraryItemType.BOOK.toString());
    }

    public static boolean isLocalizedBook(String value) {
        return value.toUpperCase().replaceAll("\\s+", "").equals(LibraryItemType.LOCALIZEDBOOK.toString());
    }

    public static boolean isMagazine(String value) {
        return value.toUpperCase().trim().equals(LibraryItemType.MAGAZINE.toString());
    }

}
