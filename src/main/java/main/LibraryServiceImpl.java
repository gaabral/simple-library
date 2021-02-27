package main;

import utils.LibraryException;

import java.util.Map;

public class LibraryServiceImpl implements LibraryService {
    private LibraryData libraryData;

    public LibraryServiceImpl(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    public Copy lendACopy(long id, String clientName) throws LibraryException {
        Copy toBeLent = libraryData.findAvailableCopy(id);
        toBeLent.setBorrowerName(clientName);
        toBeLent.setLent(true);
        return toBeLent;
    }

    public Map<Book, Integer> listAvailableTitles() {
        return libraryData.indexCopies();
    }

    public void removeACopyFromLibrary(long id) {
        libraryData.removeCopy(id);
    }

    public void addNewBookToLibrary(Book book, int numberOfCopies) {
        for (int i = 0; i < numberOfCopies; i++) {
            libraryData.addCopy(book);
        }
    }


}
