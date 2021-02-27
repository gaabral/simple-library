package main;

import utils.LibraryException;
import utils.MyIdSequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class LibraryData {
    private static final MyIdSequence idSequence = new MyIdSequence();
    private List<Copy> library = new ArrayList<>();

    public Copy findAvailableCopy(Long id) throws LibraryException {
        return library.stream()
                .filter(isCopyAvailable(id))
                .findFirst()
                .orElseThrow(() -> new LibraryException("Given copy is not available for lent"));
    }

    public Map<Book, Integer> indexCopies() {
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

    public void removeCopy(long id) {
        library.removeIf(isCopyAvailable(id));
    }

    public void addCopy(Book book) {
        library.add(new Copy(book, idSequence));
    }

    private Predicate<Copy> isCopyAvailable(long id) {
        return copy -> id == copy.getId() && !copy.isLent();
    }
}
