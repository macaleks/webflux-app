package ru.otus.jdbcprj;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.repository.BookRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RouterFunctionTest {

    @Autowired
    private RouterFunction routes;

    @MockBean
    private BookRepository repository;

    private WebTestClient webClient;

    @BeforeEach
    public void init() {
        webClient = WebTestClient
                .bindToRouterFunction(routes)
                .build();
    }

    @Test
    public void testMain() {
        Book book = new Book("111", "new book", null, null);

        Mockito.when(repository.findAll()).thenReturn(Flux.just(book));

        webClient.get()
                .uri("/func/books")
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
                .uri("/func/book?id={id}", bookId)
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
                .uri("/func/delete/{id}", bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class);

        Mockito.verify(repository, times(1)).deleteBookById(any());
    }

    @Ignore
    @Test
    public void testSave() {
        String bookId = "111";
        Book book = new Book(bookId, "new book", null, null);

        Mockito.when(repository.save(any())).thenReturn(Mono.just(book));

        webClient.post()
                .uri("/func/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(book))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class);

        Mockito.verify(repository, times(1)).save(any());
    }
}
