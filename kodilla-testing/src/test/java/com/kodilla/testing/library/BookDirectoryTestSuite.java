package com.kodilla.testing.library;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookDirectoryTestSuite {

    @Mock
    private LibraryDatabase libraryDatabaseMock;

    private List<Book> generateListOfNBooks(int booksQuantity) {
        List<Book> resultList = new ArrayList<>();
        for (int n = 1; n <= booksQuantity; n++) {
            Book theBook = new Book("Title " + n, "Author " + n, 1970 + n);
            resultList.add(theBook);
        }
        return resultList;
    }

    @Test
    void testListBooksWithConditionsReturnList() {

        // Given
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOfBooks = new ArrayList<>();
        Book book1 = new Book("Secrets of Alamo", "John Smith", 2008);
        Book book2 = new Book("Secretaries and Directors", "Dilbert Michigan", 2012);
        Book book3 = new Book("Secret life of programmers", "Steve Wolkowitz", 2016);
        Book book4 = new Book("Secrets of Java", "Ian Tenewitch", 2010);
        resultListOfBooks.add(book1);
        resultListOfBooks.add(book2);
        resultListOfBooks.add(book3);
        resultListOfBooks.add(book4);
        when(libraryDatabaseMock.listBooksWithCondition("Secret")).thenReturn(resultListOfBooks);

        // When
        List<Book> theListOfBooks = bookLibrary.listBooksWithCondition("Secret");

        // Then
        assertEquals(4, theListOfBooks.size());
    }

    @Test
    void testListBooksWithConditionMoreThan20() {

        // Given
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf0Books = new ArrayList<Book>();
        List<Book> resultListOf15Books = generateListOfNBooks(15);
        List<Book> resultListOf40Books = generateListOfNBooks(40);
        when(libraryDatabaseMock.listBooksWithCondition(anyString()))
                .thenReturn(resultListOf15Books);
        when(libraryDatabaseMock.listBooksWithCondition("ZeroBooks"))
                .thenReturn(resultListOf0Books);
        when(libraryDatabaseMock.listBooksWithCondition("FortyBooks"))
                .thenReturn(resultListOf40Books);

        // When
        List<Book> theListOfBooks0 = bookLibrary.listBooksWithCondition("ZeroBooks");
        List<Book> theListOfBooks15 = bookLibrary.listBooksWithCondition("Any title");
        List<Book> theListOfBooks40 = bookLibrary.listBooksWithCondition("FortyBooks");
        // Then

        assertEquals(0, theListOfBooks0.size());
        assertEquals(15, theListOfBooks15.size());
        assertEquals(0, theListOfBooks40.size());
    }

    @Test
    public void testListBooksWithConditionShorterThan3() {
        //Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);

        // When
        List<Book> theListOfBook10 = bookLibrary.listBooksWithCondition("An");

        //Then
        assertEquals(0, theListOfBook10.size());
        verify(libraryDatabaseMock, never()).listBooksWithCondition(anyString());
    }

    @Test
    public void testListBooksInHandsOf() {
        //Given
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        LibraryUser libraryUser0 = new LibraryUser("John", "Smith", "90102012345");
        LibraryUser libraryUser1 = new LibraryUser("Paolo", "Rossi", "99102012345");
        LibraryUser libraryUser5 = new LibraryUser("John", "Rambo", "80102012345");
        List<Book> resultOf0Books = new ArrayList<>();
        List<Book> resultOf1Book = generateListOfNBooks(1);
        List<Book> resultOf5Books = generateListOfNBooks(5);
        HashMap<LibraryUser, ArrayList<Book>> newList0 = new HashMap<>();
        HashMap<LibraryUser, ArrayList<Book>> newList1 = new HashMap<>();
        HashMap<LibraryUser, ArrayList<Book>> newList5 = new HashMap<>();
        newList0.put(libraryUser0, (ArrayList<Book>) resultOf0Books);
        newList1.put(libraryUser1, (ArrayList<Book>) resultOf1Book);
        newList5.put(libraryUser5, (ArrayList<Book>) resultOf5Books);
        when(libraryDatabaseMock.listBooksInHandsOf(libraryUser0)).thenReturn(resultOf0Books);
        when(libraryDatabaseMock.listBooksInHandsOf(libraryUser1)).thenReturn(resultOf1Book);
        when(libraryDatabaseMock.listBooksInHandsOf(libraryUser5)).thenReturn(resultOf5Books);

        //When
        List<Book> resultList0 = bookLibrary.listBooksInHandsOf(libraryUser0);
        List<Book> resultList1 = bookLibrary.listBooksInHandsOf(libraryUser1);
        List<Book> resultList5 = bookLibrary.listBooksInHandsOf(libraryUser5);

        //Then
        assertEquals(0, resultList0.size());
        assertEquals(1, resultList1.size());
        assertEquals(5, resultList5.size());
    }
}
