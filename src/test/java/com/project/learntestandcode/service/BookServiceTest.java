package com.project.learntestandcode.service;

import com.project.learntestandcode.model.Book;
import com.project.learntestandcode.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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
}
