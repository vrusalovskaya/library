package org.library;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage implements Storage {

    private final String filePath;

    public FileStorage(String filePath) throws IOException, CsvValidationException {
        this.filePath = filePath;
        File file = new File(filePath);
        file.createNewFile();
        this.getLibraryItems();
    }

    public List<LibraryItem> getLibraryItems() throws IOException, CsvValidationException {
        ArrayList<LibraryItem> items = new ArrayList<>();
        try (
                FileReader filereader = new FileReader(filePath);
                CSVReader csvReader = new CSVReader(filereader)
        ) {
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {

                String type = nextRecord[0];
                if (Objects.equals(defineType(type), "BOOK")) {
                    Book book = new Book (nextRecord[1], nextRecord[2], Integer.parseInt(nextRecord[3]), nextRecord[4]);
                    items.add(book);
                } else if (Objects.equals(defineType(type), "MAGAZINE")) {
                   Magazine magazine = new Magazine (nextRecord[1], nextRecord[2], Integer.parseInt(nextRecord[3]), Integer.parseInt(nextRecord[4]));
                    items.add(magazine);
                } else if (Objects.equals(defineType(type), "LOCALIZEDBOOK")) {
                    LocalizedBook localizedBook = new LocalizedBook (nextRecord[1], nextRecord[2], nextRecord[3], Integer.parseInt(nextRecord[4]), nextRecord[5]);
                    items.add(localizedBook);
                }
            }
        }
        return items;
    }

    @Override
    public LibraryItem search(String isbn) throws CsvValidationException, IOException {
        var items = getLibraryItems();
        for (var item : items){
            if (Objects.equals(item.getISBN(), isbn)){
                return item;
            }
        }
        return null;
    }

    private String defineType (String value){
        if (value.toUpperCase().trim().equals(LibraryItemType.BOOK.toString())){
            return "BOOK";
        } else if (value.toUpperCase().replaceAll("\\s+","").equals(LibraryItemType.LOCALIZEDBOOK.toString())) {
            return "LOCALIZEDBOOK";
        } else if (value.toUpperCase().trim().equals(LibraryItemType.MAGAZINE.toString())) {
            return "MAGAZINE";
        }
        return null;
    }
}
