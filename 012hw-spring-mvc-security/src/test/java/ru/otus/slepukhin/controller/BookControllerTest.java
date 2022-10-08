package ru.otus.slepukhin.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.security.SecurityConfig;
import ru.otus.slepukhin.service.AuthenticationInfoService;
import ru.otus.slepukhin.service.AuthorService;
import ru.otus.slepukhin.service.BookService;
import ru.otus.slepukhin.service.GenreService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Class BookController")
@WebMvcTest(BookController.class)
@WithMockUser
class BookControllerTest {
    @MockBean
    private BookService mockBookService;

    @MockBean
    private GenreService mockGenreService;

    @MockBean
    private AuthorService mockAuthorService;

    @MockBean
    private AuthenticationInfoService authenticationInfoService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;


    @Before("Init mockMvc")
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("Should access to / ")
    @Test
    void shouldAllowAccessToRoot() throws Exception {
        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("books"));
    }

    @DisplayName("Should access to /login ")
    @Test
    @WithAnonymousUser
    void shouldAllowAccessToFormLogin() throws Exception {
        mockMvc
                .perform(get("/login"))
                .andExpect(status().isOk());

        mockMvc
                .perform(formLogin())
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Should access to /logout ")
    @Test
    void shouldAllowAccessToLogout() throws Exception {
        mockMvc
                .perform(get("/logout"))
                .andExpect(status().isOk());

        mockMvc
                .perform(logout())
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Should access to /edit?id={id} ")
    @Test
    void shouldAllowAccessToEditPage() throws Exception {
        when(mockBookService.getById(12)).thenReturn(Optional.of(new Book()));
        mockMvc
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
        mockMvc
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
        mockMvc
//                .perform(post("/edit", book))
                .perform(post("/edit", book).with(csrf()))
                .andExpect(status().is3xxRedirection());
        verify(mockBookService, atLeastOnce()).save(book);
    }

    @DisplayName("Should access to /delete ")
    @Test
    void shouldAllowAccessToDelete() throws Exception {
        mockMvc
                .perform(get("/delete?id={id}", 12))
                .andExpect(status().is3xxRedirection());
        verify(mockBookService, atLeastOnce()).deleteById(12);
    }
}