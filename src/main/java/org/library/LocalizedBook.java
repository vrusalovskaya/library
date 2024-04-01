package org.library;

class LocalizedBook extends Book {
    String translatedAuthor;

    LocalizedBook(String title, String author, String translatedAuthor, Integer year, String ISBN) {
        super(title, author, year, ISBN);
        this.translatedAuthor = translatedAuthor;
    }

    public String getTranslatedAuthor() {
        return this.translatedAuthor;
    }
}
