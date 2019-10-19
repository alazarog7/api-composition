package me.fwfurtado.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.List;
import me.fwfurtado.domain.Author;
import me.fwfurtado.repositories.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorQuery implements GraphQLQueryResolver {


    private final AuthorRepository repository;

    public AuthorQuery(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> authors() {
        return repository.findAll();
    }
}
