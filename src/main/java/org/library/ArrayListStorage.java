package org.library;

import java.util.ArrayList;
import java.util.Objects;

class ArrayListStorage implements Storage {

    private ArrayList<LibraryItem> libraryItems;

    public ArrayListStorage() {
        this.libraryItems = new ArrayList<>();
        this.libraryItems.add(new Book("The Fountainhead", "Ayn Rand", 2022, "978-5-9614-6537-2"));
        this.libraryItems.add(new LocalizedBook("The Idiot", "Fyodor Dostoevsky", "Федор Достоевский", 2010, "978-1-85326-175-6"));
        this.libraryItems.add(new Magazine("The Economist", "James Wilson", 2024, 12));
    }

    @Override
    public LibraryItem search(String isbn) {
        for (var item : libraryItems) {
            if (Objects.equals(item.getISBN(), isbn)) {
                return item;
            }
        }

        return null;
    }
}
