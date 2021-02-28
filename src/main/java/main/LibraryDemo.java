package main;

import main.data.LibraryData;
import main.model.Book;
import main.model.Copy;
import main.service.LibraryService;
import main.service.LibraryServiceImpl;
import utils.MyIdSequence;

import java.util.List;
import java.util.Map;

import static main.search.ByAuthor.byAuthor;
import static main.search.ByPublishYear.byPublishYear;
import static main.search.ByTitle.byTitle;

public class LibraryDemo {

    public static void main(String[] args) throws Exception {
        MyIdSequence myIdSequence = new MyIdSequence();
        LibraryData libraryData = new LibraryData(myIdSequence);
        LibraryService libraryService = new LibraryServiceImpl(libraryData);


        libraryService.addNewBookToLibrary(new Book("Harry Potter", "J.K. Rowling", 1997), 2);
        libraryService.addNewBookToLibrary(new Book("Romeo and Juliet", "William Shakespeare", 1597), 3);
        libraryService.addNewBookToLibrary(new Book("Frankenstein", "Mary Shelley", 1818), 1);


        System.out.println("********************** LIBRARY **********************\n");


        Map<Book, Integer> availableForLenting = libraryService.listAvailableTitles();
        System.out.println(availableForLenting);
        System.out.println("********************** \n");

        Copy lentCopy = libraryService.lendACopy(1L, "Arthur");
        System.out.println("A copy was lent to Arthur: " + lentCopy);
        System.out.println("**********************\n");

        boolean removed = libraryService.removeACopyFromLibrary(2L);
        System.out.println("Copy with id = 2 was removed: " + removed);
        System.out.println("**********************\n");

        Map<Book, Integer> availableForLentingAfterRemoval = libraryService.listAvailableTitles();
        System.out.println(availableForLentingAfterRemoval);
        System.out.println("**********************\n");

        Copy copy = libraryService.seeDetails(3L);
        System.out.println(copy);
        System.out.println("**********************\n");


        List<Book> searchResult = libraryData.searchBy(byAuthor("J.K. Rowling").and(byTitle("Harry Potter")));
        System.out.println("Search found: " + searchResult.size() + " results: " + searchResult);

        List<Book> searchResult2 = libraryData.searchBy(byAuthor("J.K. Rowling").or(byPublishYear(1597)));
        System.out.println("Search found: " + searchResult2.size() + " results: " + searchResult2);


    }


}
