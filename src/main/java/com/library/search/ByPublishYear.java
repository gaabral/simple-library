package com.library.search;

import com.library.model.Book;
import lombok.experimental.UtilityClass;

import java.util.function.Predicate;

@UtilityClass
public class ByPublishYear {
    public static Predicate<Book> byPublishYear(int publishYear) {
        return x -> x.getPublishYear() == publishYear;
    }
}
