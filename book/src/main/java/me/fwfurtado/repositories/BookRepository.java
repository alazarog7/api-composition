package me.fwfurtado.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import me.fwfurtado.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    private static final AtomicLong identity = new AtomicLong();
    private static final Map<Long, Book> database = new HashMap<>();

    private final KafkaTemplate<Long, Book> kafka;
    private final String bookTopic;

    public BookRepository(KafkaTemplate<Long, Book> kafka,@Value("${topic.name}") String bookTopic) {
        this.kafka = kafka;
        this.bookTopic = bookTopic;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(database.values());
    }

    public Book save(String title, Long authorId) {
        Book book = database.computeIfAbsent(identity.incrementAndGet(), id -> new Book(id, title, authorId));

        kafka.send(bookTopic, book.getId(), book);

        return book;
    }
}
