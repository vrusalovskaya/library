package org.library.application.exporters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.library.application.models.content.Content;
import org.library.application.models.content.JsonContent;
import org.library.application.models.content.MimeType;
import org.library.application.exceptions.BookExportFailedException;
import org.library.application.models.Book;

import java.util.ArrayList;
import java.util.List;

public class JsonBookExporter implements BookExporter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean canProcess(MimeType type) {
        return type.equals(MimeType.JSON);
    }

    @Override
    public Content export(Book book) throws BookExportFailedException {
        String data = null;
        try {
            data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new BookExportFailedException(MimeType.JSON);
        }
        return new JsonContent(data);
    }

    @Override
    public List<Content> export(List<Book> books) throws BookExportFailedException {
        List<Content> booksJson = new ArrayList<>();
        for (Book book : books) {
            booksJson.add(export(book));
        }
        return booksJson;
    }
}
