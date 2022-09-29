package ru.otus.slepukhin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.service.AuthorService;
import ru.otus.slepukhin.service.BookService;
import ru.otus.slepukhin.service.GenreService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Class BookController")
@WebMvcTest(BookController.class)
class BookControllerTest {

    private final long testBookId = 12;
    private final long testNotExistedBookId = 1;
    private final Book testBook = new Book(
            testBookId,
            "New book",
            List.of(new Author(1, "Pushkin Alexander")),
            new Genre(2, "popular")
    );


    @MockBean
    private BookService mockBookService;

    @MockBean
    private GenreService mockGenreService;

    @MockBean
    private AuthorService mockAuthorService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Should access to GET /books ")
    @Test
    void shouldAllowAccessToGetAllBooks() throws Exception {
        this.mockMvc
                .perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        verify(mockBookService, atLeastOnce()).getAll();
    }

    @DisplayName("Should access to GET /books/{id} ")
    @Test
    void shouldAllowAccessToGetBook() throws Exception {
        when(mockBookService.getById(testBookId)).thenReturn(Optional.of(testBook));

        this.mockMvc
                .perform(get("/books/{id}", testBookId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        verify(mockBookService, atLeastOnce()).getById(testBookId);

        try {
            this.mockMvc
                    .perform(get("/books/{id}", testNotExistedBookId)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isNotFound());
        } catch (NotFoundException e) {
            verify(mockBookService, atLeastOnce()).getById(testNotExistedBookId);
            assertNotNull(e);
        }
    }

    @DisplayName("Should access to PUT /books/{id} ")
    @Test
    void shouldAllowAccessToPutBook() throws Exception {
        ObjectWriter ow = new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter();
        String jsonBook = ow.writeValueAsString(testBook);

        when(mockBookService.getById(testBookId)).thenReturn(Optional.of(testBook));
        this.mockMvc
                .perform(put("/books/{id}", testBookId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonBook))
                .andExpect(status().isOk());
        verify(mockBookService, atLeastOnce()).save(testBook);

        this.mockMvc
                .perform(put("/books/{id}", testNotExistedBookId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonBook))
                .andExpect(status().isCreated());
        verify(mockBookService, atLeastOnce()).save(testBook);
    }

    @DisplayName("Should access to POST /books ")
    @Test
    void shouldAllowAccessToPostBook() throws Exception {
        testBook.setId(0);

        ObjectWriter ow = new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter();
        String jsonBook = ow.writeValueAsString(testBook);

        this.mockMvc
                .perform(post("/books", testBook)
                        .content(jsonBook)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

        verify(mockBookService, atLeastOnce()).save(testBook);
    }

    @DisplayName("Should access to DELETE /books/{id} ")
    @Test
    void shouldAllowAccessToDelete() throws Exception {
        this.mockMvc
                .perform(delete("/books/{id}", testBookId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        verify(mockBookService, atLeastOnce()).deleteById(testBookId);
    }
}