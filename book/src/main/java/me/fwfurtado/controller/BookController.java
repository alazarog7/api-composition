package me.fwfurtado.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import me.fwfurtado.domain.Book;
import me.fwfurtado.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        return repository.findById(id).map(ok()::body).orElseGet(notFound()::build);
    }

    @GetMapping
    public List<Book> all() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookForm form) {

        Book book = repository.save(form.getTitle(), form.getAuthorId());

        return ok(book);
    }

    static class BookForm {
        private String title;
        private Long authorId;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }
    }
}
