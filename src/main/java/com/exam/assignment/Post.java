package com.exam.assignment;

public class Post {
    private int id;
    private String title;
    private String description;
    private String comment;
    private String author;

    public Post(int id, String title, String description, String comment, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.comment = comment;
        this.author = author;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
    }
}
