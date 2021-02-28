package com.library.search;

import lombok.experimental.UtilityClass;
import com.library.model.Book;

import java.util.function.Predicate;

@UtilityClass
public class ByAuthor {

    public static Predicate<Book> byAuthor(String author){
         return x-> x.getAuthor().equals(author);
     }
}
