package me.fwfurtado.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import me.fwfurtado.domain.Author;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final RestTemplate rest;
    private final String authorURL;

    public Mutation(RestTemplate rest, @Value("${url.author.save}") String authorURL) {
        this.rest = rest;
        this.authorURL = authorURL;
    }

    public Author createAuthor(AuthorForm form) {
        return rest.postForObject(authorURL, form, Author.class);
    }

    static class AuthorForm {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
