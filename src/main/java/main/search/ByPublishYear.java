package main.search;

import lombok.experimental.UtilityClass;
import main.model.Book;

import java.util.function.Predicate;

@UtilityClass
public class ByPublishYear {
    public static Predicate<Book> byPublishYear(int publishYear) {
        return x -> x.getPublishYear() == publishYear;
    }
}
