package com.project.learntestandcode.service;

import com.project.learntestandcode.model.Book;import com.project.learntestandcode.repository.BookRepository;import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(String title) {
        return bookRepository.save(new Book(title));
    }
}
