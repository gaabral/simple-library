package main.service;

import main.data.LibraryData;
import main.model.Book;
import main.model.Copy;
import utils.LibraryException;

import java.util.Map;

public class LibraryServiceImpl implements LibraryService {
    private final LibraryData libraryData;

    public LibraryServiceImpl(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    @Override
    public Copy lendACopy(long id, String clientName) throws LibraryException {
        Copy toBeLent = libraryData.findAvailableCopy(id);
        toBeLent.setBorrowerName(clientName);
        toBeLent.setLent(true);
        return toBeLent;
    }

    @Override
    public Map<Book, Integer> listAvailableTitles() {
        return libraryData.indexCopiesByBookAndAvailableQty();
    }

    @Override
    public boolean removeACopyFromLibrary(long id) {
       return libraryData.removeCopy(id);
    }

    @Override
    public void addNewBookToLibrary(Book book, int numberOfCopies) {
        for (int i = 0; i < numberOfCopies; i++) {
            libraryData.addCopy(book);
        }
    }

    @Override
    public Copy seeDetails(long id) throws LibraryException {
        return libraryData.findCopy(id);
    }

}
