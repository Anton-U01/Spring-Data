package com.example.services;

import com.example.data.entities.Author;
import com.example.data.entities.Book;
import com.example.data.entities.Category;
import com.example.data.entities.enums.AgeRestriction;
import com.example.data.entities.enums.EditionType;
import com.example.data.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final static String BOOK_PATH = "D:\\SOFTUNI\\Spring Data\\Exercise Spring Data Intro\\demo\\src\\main\\java\\com\\example\\files\\books.txt";
    private final AuthorServiceImpl authorService;

    private final CategoryServiceImpl categoryService;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorServiceImpl authorService, CategoryServiceImpl categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines((Path.of(BOOK_PATH)))
                .stream().filter(row -> !row.isEmpty())
                .forEach(row -> {
                    String[] data = row.split("\\s+");
                    EditionType editionType = EditionType.values()[Integer.parseInt((data[0]))];
                    LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt((data[4]))];
                    String title = Arrays.stream(data).skip(5).collect(Collectors.joining(" "));
                    Author author = authorService.getRandomAuthor();
                    Set<Category> categories = categoryService.getRandomCategories();
                    Book book = new Book(title,editionType,price,copies,releaseDate,ageRestriction,author,categories);
                    bookRepository.saveAndFlush(book);
                });
    }

    @Override
    public void getBooksAfter2000() {
        this.bookRepository.findAllByReleaseDateAfter(LocalDate.of(2000,12,31))
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void getAllBooksOfGeorgePowell() {
        this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George","Powell")
                .forEach(b -> {
                    System.out.println(b.getTitle() + " " + b.getReleaseDate() + " " + b.getCopies());
                });
    }
}
