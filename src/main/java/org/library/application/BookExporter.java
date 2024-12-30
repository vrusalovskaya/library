package org.library.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.library.application.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookExporter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static List<String> toJson(List<Book> books) throws JsonProcessingException {
        List<String> booksJson = new ArrayList<>();
        for (Book book : books) {
            booksJson.add(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book));
        }
        return booksJson;
    }

    public static String toJson(Book book) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
    }

    public static List<String> toXml(List<Book> books) throws JsonProcessingException {
        List<String> booksXml = new ArrayList<>();
        for (Book book : books) {
            booksXml.add(xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book));
        }
        return booksXml;
    }

    public static String toXml(Book book) throws JsonProcessingException {
        return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
    }
}
