package main;

import main.model.Book;
import main.model.Copy;
import main.data.LibraryData;
import main.service.LibraryService;
import main.service.LibraryServiceImpl;

import java.util.Map;
import java.util.Optional;

import static main.search.ByAuthor.byAuthor;
import static main.search.ByTitle.byTitle;

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

        System.out.println("######################################");

        Optional<Book> x = libraryData.searchBy(byAuthor("Jane Austen").and(byTitle("ss")));
        System.out.println(x.get());

    }


}
