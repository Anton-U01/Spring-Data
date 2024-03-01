package com.example.services;

import com.example.data.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    Author getRandomAuthor();
    void getAuthorsWithAtLeast1BookReleasedBefore1990();

    void getAuthorsOrderedByBooksCount();

}
