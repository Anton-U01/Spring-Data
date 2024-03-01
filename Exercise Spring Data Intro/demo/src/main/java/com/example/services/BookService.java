package com.example.services;

import com.example.data.entities.Book;

import java.io.IOException;
import java.util.Set;

public interface BookService {
    void seedBooks() throws IOException;
    void getBooksAfter2000();

    void getAllBooksOfGeorgePowell();
}
