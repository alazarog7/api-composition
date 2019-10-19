package me.fwfurtado.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import me.fwfurtado.domain.Author;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {

    private static final AtomicLong identity = new AtomicLong();
    private static final Map<Long, Author> database = new HashMap<>();

    private final KafkaTemplate<Long, Author> kafka;
    private final String authorsTopic;

    public AuthorRepository(KafkaTemplate<Long, Author> kafka, @Value("${topic.name}") String authorsTopic) {
        this.kafka = kafka;
        this.authorsTopic = authorsTopic;
    }

    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public Author save(String name) {
        Author author = database.computeIfAbsent(identity.incrementAndGet(), id -> new Author(id, name));


        kafka.send(authorsTopic, author.getId(), author);

        return author;
    }

    public List<Author> findAll() {
        return new ArrayList<>(database.values());
    }
}
