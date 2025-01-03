package org.library.application.exporters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.library.application.models.content.Content;
import org.library.application.models.content.MimeType;
import org.library.application.models.content.XmlContent;
import org.library.application.exceptions.BookExportFailedException;
import org.library.application.models.Book;

import java.util.ArrayList;
import java.util.List;

public class XmlBookExporter implements BookExporter {
    private static final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public boolean canProcess(MimeType type) {
        return type.equals(MimeType.XML);
    }

    @Override
    public Content export(Book book) throws BookExportFailedException {
        String data = null;
        try {
            data = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new BookExportFailedException(MimeType.XML);
        }
        return new XmlContent(data);
    }

    @Override
    public List<Content> export(List<Book> books) throws BookExportFailedException {
        List<Content> booksXml = new ArrayList<>();
        for (Book book : books) {
            booksXml.add(export(book));
        }
        return booksXml;
    }
}
