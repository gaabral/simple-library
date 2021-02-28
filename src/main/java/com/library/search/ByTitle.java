package com.library.search;

import com.library.model.Book;
import lombok.experimental.UtilityClass;

import java.util.function.Predicate;

@UtilityClass
public class ByTitle {
    public static Predicate<Book> byTitle(String title){
        return x-> x.getTitle().equals(title);
    }
}
