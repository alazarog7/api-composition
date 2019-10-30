package me.fwfurtado.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import java.util.concurrent.CompletableFuture;
import me.fwfurtado.domain.Author;
import me.fwfurtado.domain.Book;
import me.fwfurtado.repositories.AuthorRepository;
import org.dataloader.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    private static final Logger LOG = LoggerFactory.getLogger(BookResolver.class);


    private final AuthorRepository repository;

    public BookResolver(AuthorRepository repository) {
        this.repository = repository;
    }

    public CompletableFuture<Author> getAuthor(Book book, DataFetchingEnvironment environment) {

        LOG.info("Call authors service");

        return authorCompletableFuture().load(book.getId());
    }

    @Bean
    DataLoader<Long, Author> authorCompletableFuture() {
        return new DataLoader<>(authorsId -> CompletableFuture.supplyAsync(() -> repository.findAllByIds(authorsId)));
    }


}
