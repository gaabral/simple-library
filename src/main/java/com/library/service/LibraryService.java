package com.library.service;

import com.library.model.Book;
import com.library.model.Copy;
import utils.LibraryException;

import java.util.Map;

public interface LibraryService {
    Copy lendACopy(long id, String clientName) throws LibraryException;

    Map<Book, Integer> listAvailableTitles();

    boolean removeACopyFromLibrary(long id);

    void addNewBookToLibrary(Book book, int numberOfCopies);

    Copy seeDetails(long id) throws LibraryException;
}
