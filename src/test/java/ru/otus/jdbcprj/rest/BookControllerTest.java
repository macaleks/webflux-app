package ru.otus.jdbcprj.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.repository.BookRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@WebFluxTest(controllers = BookController.class)
//@ContextConfiguration
public class BookControllerTest {

    @MockBean
    private BookRepository repository;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testMain() {
        Book book = new Book("111", "new book", null, null);

        Mockito.when(repository.findAll()).thenReturn(Flux.just(book));

        webClient.get()
                .uri("/api/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class);

        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        String bookId = "111";
        Book book = new Book(bookId, "new book", null, null);

        Mockito.when(repository.getBookById(any())).thenReturn(Mono.just(book));

        webClient.get()
                .uri("/api/book?id={id}", bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class);

        Mockito.verify(repository, times(1)).getBookById(any());
    }

    @Test
    public void testDeleteById() {
        String bookId = "111";
        Book book = new Book(bookId, "new book", null, null);

        Mockito.when(repository.deleteBookById(any())).thenReturn(Mono.just(book));

        webClient.post()
                .uri("/api/delete/{id}", bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class);

        Mockito.verify(repository, times(1)).deleteBookById(any());
    }

    @Test
    public void testSave() {
        String bookId = "111";
        Book book = new Book(bookId, "new book", null, null);

        Mockito.when(repository.save(any())).thenReturn(Mono.just(book));

        webClient.post()
                .uri("/api/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(book))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class);

        Mockito.verify(repository, times(1)).save(any());
    }
}
