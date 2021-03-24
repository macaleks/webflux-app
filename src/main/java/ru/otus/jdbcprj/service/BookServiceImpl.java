package ru.otus.jdbcprj.service;

import org.springframework.stereotype.Service;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    BookRepository repo;

    public BookServiceImpl(BookRepository bookDao) {
        this.repo = bookDao;
    }

    @Override
    public List<Book> getAll() {
        return repo.findAll();
    }

    @Override
    public Book save(Book book) {
        return repo.save(book);
    }

    @Override
    public void deleteById(String id) {
        repo.deleteById(id);
    }

    @Override
    public void updateNameById(Book book) {
        repo.save(book);
    }

    @Override
    public Book getById(String id) {
        return repo.findById(id).get();
    }


}
