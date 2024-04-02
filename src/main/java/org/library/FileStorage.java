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

                if (nextRecord.length == 4 && IsbnValidator.isValidIsbn(nextRecord[3])) {
                    Book book = new Book (nextRecord[0], nextRecord[1], Integer.parseInt(nextRecord[2]), nextRecord[3]);
                    items.add(book);
                } else if (nextRecord.length == 4 && !IsbnValidator.isValidIsbn(nextRecord[3])) {
                   Magazine magazine = new Magazine (nextRecord[0], nextRecord[1], Integer.parseInt(nextRecord[2]), Integer.parseInt(nextRecord[3]));
                    items.add(magazine);
                } else if (nextRecord.length == 5) {
                    LocalizedBook localizedBook = new LocalizedBook (nextRecord[0], nextRecord[1], nextRecord[2], Integer.parseInt(nextRecord[3]), nextRecord[4]);
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
}
