package me.fwfurtado.domain;

import java.util.StringJoiner;

public class Book {

    private Long id;
    private String title;
    private Long authorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("title='" + title + "'")
            .add("authorId=" + authorId)
            .toString();
    }
}
