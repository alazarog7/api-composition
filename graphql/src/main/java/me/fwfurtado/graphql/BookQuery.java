package me.fwfurtado.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
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

    public Connection<Book> books(int first, String after, DataFetchingEnvironment environment) {
        return new SimpleListConnection<>(repository.findAll()).get(environment);
    }

    public Optional<Book> searchBookBy(Long id) {
        return repository.findById(id);
    }

}
