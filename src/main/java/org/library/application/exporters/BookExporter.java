package org.library.application.exporters;

import org.library.application.models.content.Content;
import org.library.application.models.content.MimeType;
import org.library.application.exceptions.BookExportFailedException;
import org.library.application.models.Book;

import java.util.List;

public interface BookExporter {
    boolean canProcess(MimeType type);

    Content export(Book book) throws BookExportFailedException;

    List<Content> export(List<Book> books) throws BookExportFailedException;
}
