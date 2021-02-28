package main.search;

import lombok.experimental.UtilityClass;
import main.model.Book;

import java.util.function.Predicate;

@UtilityClass
public class ByTitle {
    public static Predicate<Book> byTitle(String title){
        return x-> x.getTitle().equals(title);
    }
}
