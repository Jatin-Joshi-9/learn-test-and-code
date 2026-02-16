package com.project.learntestandcode.service;

import com.project.learntestandcode.model.Book;
import com.project.learntestandcode.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;
    @Test
    void ShouldReturnBook_WhenBookIsAddedSuccessfully() {
        // Given
        String title = "Harry Potter";
        Book book = new Book(title);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        //When
        Book addedBook = bookService.addBook(title);

        //Then
        assertEquals(book, addedBook);
        assertEquals(title, addedBook.getTitle());
    }
    @Test
    void shouldReturnEmptyList_WhenThereIsNoBooksInTheLibraryRecord() {
        // Given
        List<Book> books = Collections.emptyList();
        when(bookRepository.findAll()).thenReturn(books);

        // When
        List<Book> result = bookService.findAll();

        // Then
        assertTrue(result.isEmpty());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnBooksList_WhenBooksAreInTheLibraryRecord() {
        //Given
        List<Book> books = Arrays.asList(
                new Book("clean code"),
                new Book("Last chance")
        );
        when(bookRepository.findAll()).thenReturn(books);

        //When
        List<Book> result = bookService.findAll();

        //Then
        assertEquals(books, result);
        verify(bookRepository, times(1)).findAll();
    }
}
