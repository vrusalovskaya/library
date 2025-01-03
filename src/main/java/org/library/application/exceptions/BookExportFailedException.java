package org.library.application.exceptions;

import org.library.application.models.content.MimeType;

public class BookExportFailedException extends Exception {
    public BookExportFailedException(MimeType type) {
        super("The export of the book to " + type.getType() + " type failed");
    }
}
