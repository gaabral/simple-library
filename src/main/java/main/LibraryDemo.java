package main;

import java.util.*;

public class LibraryDemo {

    public static void main(String[] args) throws Exception {
        LibraryData libraryData = new LibraryData();
        LibraryService libraryService = new LibraryServiceImpl(libraryData);

        libraryService.addNewBookToLibrary(new Book("ss", "Jane Austen", 1990), 2);
        libraryService.addNewBookToLibrary(new Book("Romeo and Juliet", "William Shakespeare", 1990), 3);


        Map<Book, Integer> availableForLenting = libraryService.listAvailableTitles();
        System.out.println(availableForLenting);

        Copy lentCopy = libraryService.lendACopy(1L, "Arthur");
        System.out.println(lentCopy);
        libraryService.removeACopyFromLibrary(2L);
        System.out.println("//////////////////////////////////////");
        Map<Book, Integer> availableForLenting2 = libraryService.listAvailableTitles();
        System.out.println(availableForLenting2);


    }



}
