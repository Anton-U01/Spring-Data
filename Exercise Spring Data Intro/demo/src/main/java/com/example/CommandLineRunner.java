package com.example;

import com.example.data.entities.Book;
import com.example.services.AuthorServiceImpl;
import com.example.services.BookServiceImpl;
import com.example.services.CategoryServiceImpl;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final CategoryServiceImpl categoryService;

    private final AuthorServiceImpl authorService;
    private final BookServiceImpl bookService;

    public CommandLineRunner(CategoryServiceImpl categoryService, AuthorServiceImpl authorService, BookServiceImpl bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
        bookService.getBooksAfter2000();
        authorService.getAuthorsWithAtLeast1BookReleasedBefore1990();
        authorService.getAuthorsOrderedByBooksCount();
        bookService.getAllBooksOfGeorgePowell();
    }

    private void seedDatabase() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
