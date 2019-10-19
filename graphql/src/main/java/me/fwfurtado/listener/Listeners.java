package me.fwfurtado.listener;

import me.fwfurtado.domain.Author;
import me.fwfurtado.domain.Book;
import me.fwfurtado.repositories.AuthorRepository;
import me.fwfurtado.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listeners {

    private static final Logger LOG = LoggerFactory.getLogger(Listeners.class);

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Listeners(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @KafkaListener(topics = "${topics.author}")
    public void handle(Author author) {
        LOG.info("receive a new author {}", author);

        authorRepository.save(author);
    }


    @KafkaListener(topics = "${topics.book}" )
    public void handle(Book event) {
        LOG.info("receive a new book {}", event);

        Author author = authorRepository.findById(event.getAuthorId()).orElseThrow(() -> new IllegalArgumentException("Cannot find author with this id " + event.getAuthorId()));


        bookRepository.save(event);

    }
}
