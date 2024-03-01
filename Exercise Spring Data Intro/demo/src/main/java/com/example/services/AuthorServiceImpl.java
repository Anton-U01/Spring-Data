package com.example.services;

import com.example.data.entities.Author;
import com.example.data.entities.Book;
import com.example.data.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    private final static String AUTHORS_PATH = "D:\\SOFTUNI\\Spring Data\\Exercise Spring Data Intro\\demo\\src\\main\\java\\com\\example\\files\\authors.txt";

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_PATH))
                .stream().filter(row->!row.isEmpty())
                .forEach(row -> {
                    String[] data = row.split("\\s+");
                    Author author = new Author(data[0],data[1]);
                    authorRepository.saveAndFlush(author);
                });
    }

    public Author getRandomAuthor() {
        int id = ThreadLocalRandom.current().nextInt(1, (int) authorRepository.count() + 1);
        return authorRepository.findById(id).get();
    }

    @Override
    public void getAuthorsWithAtLeast1BookReleasedBefore1990() {
        this.authorRepository.findAllByBooksReleaseDateBefore(LocalDate.of(1990,1,1))
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    @Override
    public void getAuthorsOrderedByBooksCount() {
        this.authorRepository.findAllOrderByBooksSizeDesc()
                .forEach(a -> {
                    System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size());
                });
    }

}
