package com.library.data;

import com.library.model.Book;
import com.library.model.Copy;
import com.library.search.ByAuthor;
import junit.framework.TestCase;
import utils.LibraryException;
import utils.MyIdSequence;

import java.util.List;
import java.util.Map;

import static com.library.search.ByPublishYear.byPublishYear;
import static com.library.search.ByTitle.byTitle;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryDataTest extends TestCase {
    MyIdSequence idSequence = mock(MyIdSequence.class);
    LibraryData underTest = new LibraryData(idSequence);

    Book book = new Book("book", "Jane Austen", 1890);
    Book book1 = new Book("book1", "John Du", 1990);
    Book book2 = new Book("book2", "John Lennon", 1990);
    Book book3 = new Book("book3", "John Lennon", 1980);

    public void setUp() {
        underTest.addCopy(book1);
        underTest.addCopy(book2);
        underTest.addCopy(book3);
    }


    public void testFindAvailableCopyDoesNotExist() {
        when(idSequence.getNextValue()).thenReturn(10L);
        underTest.addCopy(book);

        assertThrows(LibraryException.class, () -> underTest.findAvailableCopy(1L));
    }

    public void testFindAvailableCopyIsLent() throws LibraryException {
        when(idSequence.getNextValue()).thenReturn(10L);
        underTest.addCopy(book);
        Copy x = underTest.findCopy(10L);
        x.setLent(true);

        assertThrows(LibraryException.class, () -> underTest.findAvailableCopy(10L));
    }

    public void testFindCopyDoesNotExist() {
        when(idSequence.getNextValue()).thenReturn(10L);
        underTest.addCopy(book);

        assertThrows(LibraryException.class, () -> underTest.findCopy(2L));
    }

    public void testIndexCopies() {
        underTest.addCopy(book1);

        Map<Book, Integer> result = underTest.indexCopiesByBookAndAvailableQty();

        assertEquals(2, (int) result.get(book1));
        assertEquals(1, (int) result.get(book2));
        assertEquals(1, (int) result.get(book3));
    }

    public void testRemoveNotExistingCopy() throws LibraryException {
        when(idSequence.getNextValue()).thenReturn(10L);
        underTest.addCopy(book);
        assertSame(book, underTest.findCopy(10L).getBook());

        assertFalse(underTest.removeCopy(5L));
    }

    public void testRemoveCopy() throws LibraryException {
        when(idSequence.getNextValue()).thenReturn(10L);
        underTest.addCopy(book);
        assertSame(book, underTest.findCopy(10L).getBook());

        assertTrue(underTest.removeCopy(10L));
    }

    public void testAddCopy() throws LibraryException {
        assertThrows(LibraryException.class, () -> underTest.findCopy(10L));
        when(idSequence.getNextValue()).thenReturn(10L);

        underTest.addCopy(book2);

        Copy result = underTest.findCopy(10L);
        assertSame(book2, result.getBook());
    }

    public void testSearchByAuthor() {
        List<Book> result = underTest.searchBy(ByAuthor.byAuthor("John Lennon"));
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    public void testSearchByTitle() {
        List<Book> result = underTest.searchBy(byTitle("book2"));
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertSame(book2, result.get(0));
    }

    public void testSearchByYear() {
        List<Book> result = underTest.searchBy(byPublishYear(1990));
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    public void testSearchByTitleAndAuthor() {
        List<Book> result = underTest.searchBy(byTitle("book1").and(ByAuthor.byAuthor("John Du")));
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertSame(book1, result.get(0));
    }
}