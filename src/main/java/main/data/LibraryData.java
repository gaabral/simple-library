package main.data;

import main.model.Book;
import main.model.Copy;
import utils.LibraryException;
import utils.MyIdSequence;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibraryData {
    private final MyIdSequence idSequence;
    private final List<Copy> library = new ArrayList<>();

    public LibraryData(MyIdSequence idSequence) {
        this.idSequence = idSequence;
    }

    public Copy findAvailableCopy(Long id) throws LibraryException {
        return library.stream()
                .filter(isCopyAvailable(id))
                .findFirst()
                .orElseThrow(() -> new LibraryException("Given copy is not available for lent"));
    }

    public Copy findCopy(Long id) throws LibraryException {
        return library.stream()
                .filter(x -> id == x.getId())
                .findFirst()
                .orElseThrow(() -> new LibraryException("Given copy is does not exist"));
    }

    public Map<Book, Integer> indexCopiesByBookAndAvailableQty() {
        Map<Book, Integer> libraryListing = new HashMap<>();

        for (Copy copy : library) {
            if (!copy.isLent()) {
                if (libraryListing.containsKey(copy.getBook())) {
                    Integer value = libraryListing.get(copy.getBook());
                    libraryListing.put(copy.getBook(), value + 1);
                } else {
                    libraryListing.put(copy.getBook(), 1);
                }
            }
        }
        return libraryListing;
    }

    public boolean removeCopy(long id) {
        return library.removeIf(isCopyAvailable(id));
    }

    public void addCopy(Book book) {
        library.add(new Copy(book, idSequence));
    }

    public List<Book> searchBy(Predicate<Book> condition){
        return library.stream()
                .map(Copy::getBook)
                .filter(condition)
                .distinct()
                .collect(Collectors.toList());
    }

    private Predicate<Copy> isCopyAvailable(long id) {
        return copy -> id == copy.getId() && !copy.isLent();
    }
}
