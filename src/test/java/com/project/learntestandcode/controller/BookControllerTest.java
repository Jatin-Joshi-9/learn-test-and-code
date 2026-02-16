package com.project.learntestandcode.controller;

import com.project.learntestandcode.model.Book;
import com.project.learntestandcode.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @MockitoBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void ShouldReturn201AndBook_WhenBookAddedSuccessfully() throws Exception {
        //given
        Book book = new Book("Harry Potter");

        when(bookService.addBook("Harry Potter")).thenReturn(book);

        //when

        mockMvc.perform(MockMvcRequestBuilders.post("/books").contentType(MediaType.APPLICATION_JSON).content(
            objectMapper.writeValueAsString(book))).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Harry Potter"));

    }
    @Test
    void shouldReturn400_WhenTitleIsBlank() throws Exception {
        // given
        String invalidJson = """
        {
            "title": ""
        }
        """;

        // when + then
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldReturn200AndListOfBooks_WhenTheGetAllBooksMethodIsCalled() throws Exception {
        //given
        List<Book> books = List.of(
                new Book("Jumanji"),
                new Book("Technogise")
        );
        when(bookService.findAll()).thenReturn(books);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Jumanji"))
                .andExpect(jsonPath("$[1].title").value("Technogise"));
    }
}
