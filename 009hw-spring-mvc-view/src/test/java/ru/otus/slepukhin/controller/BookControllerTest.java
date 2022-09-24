package ru.otus.slepukhin.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.service.AuthorService;
import ru.otus.slepukhin.service.BookService;
import ru.otus.slepukhin.service.GenreService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Class BookController")
@WebMvcTest(BookController.class)
class BookControllerTest {
    @MockBean
    private BookService mockBookService;

    @MockBean
    private GenreService mockGenreService;
    @MockBean
    private AuthorService mockAuthorService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Should access to / ")
    @Test
    void shouldAllowAccessToRoot() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("books"));
    }

    @DisplayName("Should access to /edit?id={id} ")
    @Test
    void shouldAllowAccessToEditPage() throws Exception {
        when(mockBookService.getById(12)).thenReturn(Optional.of(new Book()));
        this.mockMvc
                .perform(get("/edit?id={id}", 12))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("authors"));
    }

    @DisplayName("Should access to /create ")
    @Test
    void shouldAllowAccessToCreatePage() throws Exception {
        this.mockMvc
                .perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("authors"));
    }

    @DisplayName("Should access to POST /edit ")
    @Test
    void shouldAllowAccessToEditPOST() throws Exception {
        Book book = new Book();
        this.mockMvc
                .perform(post("/edit", book))
                .andExpect(status().is3xxRedirection());
        verify(mockBookService, atLeastOnce()).save(book);
    }

    @DisplayName("Should access to /delete ")
    @Test
    void shouldAllowAccessToDelete() throws Exception {
        this.mockMvc
                .perform(get("/delete?id={id}", 12))
                .andExpect(status().is3xxRedirection());
        verify(mockBookService, atLeastOnce()).deleteById(12);
    }
}