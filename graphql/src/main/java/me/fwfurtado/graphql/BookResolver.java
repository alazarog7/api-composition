package me.fwfurtado.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import java.util.Optional;
import me.fwfurtado.domain.Author;
import me.fwfurtado.domain.Book;
import me.fwfurtado.repositories.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    private static final Logger LOG = LoggerFactory.getLogger(BookResolver.class);


    private final AuthorRepository repository;

    public BookResolver(AuthorRepository repository) {
        this.repository = repository;
    }

    public Optional<Author> getAuthor(Book book) {

        LOG.info("Call authors service");

        return repository.findById(book.getId());
    }

}
