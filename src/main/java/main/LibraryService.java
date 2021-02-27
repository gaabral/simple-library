package main;

import utils.LibraryException;

import java.util.Map;

public interface LibraryService {
    Copy lendACopy(long id, String clientName) throws LibraryException;
    Map<Book, Integer> listAvailableTitles();
    void removeACopyFromLibrary(long id);
    void addNewBookToLibrary(Book book, int numberOfCopies);

}
