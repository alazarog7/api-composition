package me.fwfurtado.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Optional;
import me.fwfurtado.domain.Book;
import me.fwfurtado.repositories.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class BookQuery implements GraphQLQueryResolver {


    private final BookRepository repository;

    public BookQuery(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> books() {
        return repository.findAll();
    }

    public Optional<Book> searchBookBy(Long id) {
        return repository.findById(id);
    }

}
