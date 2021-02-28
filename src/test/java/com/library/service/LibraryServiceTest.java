package com.library.service;

import junit.framework.TestCase;
import com.library.data.LibraryData;
import com.library.model.Book;
import com.library.model.Copy;
import utils.LibraryException;
import utils.MyIdSequence;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class LibraryServiceTest extends TestCase {
    private static final MyIdSequence testSequence = mock(MyIdSequence.class);
    LibraryData data = mock(LibraryData.class);
    LibraryService underTest = new LibraryServiceImpl(data);
    Book b = new Book("t", "a", 1889);


    public void testLendACopyThanDoesNotExist() throws LibraryException {
        when(data.findAvailableCopy(1L)).thenThrow(new LibraryException("Copy not found"));

        assertThrows(LibraryException.class, () -> underTest.lendACopy(1L, "clientName"));
        verify(data, times(1)).findAvailableCopy(1L);
    }


    public void testLendACopySuccessfully() throws LibraryException {
        Copy foundCopy = new Copy(new Book("author", "title", 1786), testSequence);
        when(data.findAvailableCopy(1L)).thenReturn(foundCopy);

        underTest.lendACopy(1L, "clientName");

        assertTrue(foundCopy.isLent());
        assertEquals("clientName", foundCopy.getBorrowerName());
        verify(data, times(1)).findAvailableCopy(1L);
    }


    public void testListAvailableTitles() {
        Map<Book, Integer> booksIndexed = new HashMap<>();
        booksIndexed.put(new Book("t", "a", 1872), 8);
        when(data.indexCopiesByBookAndAvailableQty()).thenReturn(booksIndexed);

        Map<Book, Integer> result = underTest.listAvailableTitles();

        assertEquals(1, result.size());
        assertEquals(booksIndexed, result);
        verify(data, times(1)).indexCopiesByBookAndAvailableQty();
    }


    public void testSeeDetailsCopyNotFound() throws LibraryException {
        when(data.findCopy(1L)).thenThrow(new LibraryException("not found"));
        assertThrows(LibraryException.class, () -> underTest.seeDetails(1L));
        verify(data, times(1)).findCopy(1L);
    }

    public void testSeeDetails() throws LibraryException {
        when(testSequence.getNextValue()).thenReturn(1L);
        Copy copy = new Copy(b, testSequence);
        when(data.findCopy(1L)).thenReturn(copy);

        Copy result = underTest.seeDetails(1L);

        assertSame(copy, result);
        verify(data, times(1)).findCopy(1L);
    }

    public void testAddNewBookToLibrary() {
        underTest.addNewBookToLibrary(b, 2);
        verify(data, times(2)).addCopy(b);
    }

    public void testRemoveBook() {
        when(data.removeCopy(10L)).thenReturn(true);
        assertTrue(underTest.removeACopyFromLibrary(10L));
        verify(data, times(1)).removeCopy(10L);
    }
}