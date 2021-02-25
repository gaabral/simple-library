import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LibraryDemo {

    private static List<Copy> library = new ArrayList<>();
    private static final MyIdSequence idSequence = new MyIdSequence();


    public static void main(String[] args) throws Exception {

        addNewBookToLibrary(new Book("ss", "Jane Austen", 1990), 2);
        addNewBookToLibrary(new Book("Romeo and Juliet", "William Shakespeare", 1990), 3);


        HashMap<Book, Integer> availableForLenting = listAvailableTitles();
        System.out.println(availableForLenting);

        Copy lentCopy = lendACopy(1l, "Arthur");
        removeACopyFromLibrary(2l);
        System.out.println("//////////////////////////////////////");
        System.out.println(library);

    }

    private static Copy lendACopy(long id, String clientName) throws Exception {
        Copy toBeLent = library.stream()
                .filter(x -> (x.getId()==id && !x.isLent()))
                .findFirst()
                .orElseThrow(() -> new Exception("Given copy is nt available for lent"));

        toBeLent.setBorrowerName(clientName);
        toBeLent.setLent(true);
        return toBeLent;
    }

    private static HashMap<Book, Integer> listAvailableTitles() {
        HashMap<Book, Integer> libraryListing = new HashMap<>();
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

    private static void removeACopyFromLibrary(long id) {
        library.removeIf(x -> (id==x.getId() && !x.isLent()));
    }

    private static void addNewBookToLibrary(Book book, int numberOfCopies) {
        for (int i = 0; i < numberOfCopies; i++) {

            library.add(new Copy(book, idSequence));
        }
    }

}
