package ru.otus.jdbcprj.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public Flux<Book> main() {
        return bookRepository.findAll();
    }

    @GetMapping("/book")
    public Mono<Book> getBook(@RequestParam("id") String id) {
        return bookRepository.getBookById(id);
    }

    @PostMapping("/delete/{id}")
    public Mono<Book> deleteBook(@PathVariable("id") String id) {
        return bookRepository.deleteBookById(id);
    }

    @PostMapping("/save")
    public Mono<Book> createBook(@RequestBody Book book) {
        bookRepository.save(book);
        return Mono.just(book);
    }
}
