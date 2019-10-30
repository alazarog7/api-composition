package me.fwfurtado.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import me.fwfurtado.domain.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {

    private static final Map<Long, Author> database = new HashMap<>();

    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public void save(Author author) {
        database.put(author.getId(), author);
    }

    public List<Author> findAll() {
        return new ArrayList<>(database.values());
    }

    public List<Author> findAllByIds(List<Long> authorsId) {
        return authorsId.stream().map(database::get).collect(Collectors.toList());
    }
}
