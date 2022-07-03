package ru.otus.slepukhin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Input;
import org.springframework.shell.Shell;
import ru.otus.slepukhin.dao.BookDao;
import ru.otus.slepukhin.domain.Book;

import static org.mockito.Mockito.verify;

@DisplayName("Class ShellService")
@SpringBootTest
class ShellServiceTest {
    @MockBean
    private BookDao mockBookDao;

    @Autowired
    private Shell shell;


    @DisplayName("Should invoke count method")
    @Test
    void shouldInvokeCount() {
        shell.evaluate(() -> "getCount");
        verify(mockBookDao).count();
    }

    @DisplayName("Should invoke getById method")
    @Test
    void shouldInvokeGetById() {
        shell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "getById 1";
            }

        });
        verify(mockBookDao).getById(1);
    }

    @DisplayName("Should invoke getAll method")
    @Test
    void shouldInvokeGetAll() {
        shell.evaluate(() -> "getAll");
        verify(mockBookDao).getAll();
    }

    @DisplayName("Should invoke insert method")
    @Test
    void shouldInvokeInsert() {
        shell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "insert bookTitle 1 1";
            }

        });

        verify(mockBookDao).insert(new Book("bookTitle", 1L, 1L));
    }

    @DisplayName("Should invoke update method")
    @Test
    void shouldInvokeUpdate() {
        shell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "update 1 bookTitle 1 1";
            }

        });

        verify(mockBookDao).update(new Book(1L, "bookTitle", 1L, 1L));
    }
}
