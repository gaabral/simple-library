package com.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.MyIdSequence;

@ToString
@Getter
@Setter
public class Copy {
    private final long id;
    private final Book book;
    private String borrowerName;
    private boolean lent;

    public Copy(Book book, MyIdSequence idSequence) {
        this.book = book;
        this.id = idSequence.getNextValue();
    }
}
