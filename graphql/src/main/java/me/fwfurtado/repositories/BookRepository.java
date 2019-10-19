package me.fwfurtado.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import me.fwfurtado.domain.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    private static final Map<Long, Book> database = new HashMap<>();

    public void save(Book book) {
        database.put(book.getId(), book);
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(database.values());
    }
}
