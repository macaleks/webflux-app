package ru.otus.jdbcprj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.repository.BookRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Bean
    public RouterFunction<ServerResponse> routes(BookRepository repository) {

        RouterFunction<ServerResponse> route = route()
                .GET("/func/books", accept(APPLICATION_JSON), serverRequest -> ok().contentType(APPLICATION_JSON).body(repository.findAll(), Book.class))

                .GET("/func/book", accept(APPLICATION_JSON),
                        serverRequest -> ok().contentType(APPLICATION_JSON)
                                .body(repository.getBookById(serverRequest.queryParam("id").get()), Book.class))

                .POST("/func/delete/{id}", accept(APPLICATION_JSON), serverRequest -> ok().contentType(APPLICATION_JSON)
                        .body(repository.deleteBookById(serverRequest.pathVariable("id")), Book.class))

                .POST("/func/save", accept(APPLICATION_JSON), serverRequest -> {
                    Mono<Book> bookMono = serverRequest.bodyToMono(Book.class);
                    bookMono.subscribe(repository::save);
                    return ok().contentType(APPLICATION_JSON).body(bookMono, Book.class);
                })
                .build();
        return route;
    }
}