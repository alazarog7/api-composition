package me.fwfurtado.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import me.fwfurtado.domain.Author;
import me.fwfurtado.repositories.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return repository.findById(id).map(ok()::body).orElseGet(notFound()::build);
    }

    @GetMapping
    List<Author> all() {
        return repository.findAll();
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody AuthorForm form) {
        Author author = repository.save(form.name);

        return ok(author);
    }

    static public class AuthorForm {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
