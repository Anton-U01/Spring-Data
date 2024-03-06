package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookInfo;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksTitleWithAgeRestriction(String ageRestriction) {
        ageRestriction = ageRestriction.toUpperCase();
        return this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllGoldBooksWithLessThan5000Copies() {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.valueOf("GOLD"),5000)
                .stream().map(Book::getTitle)
                .toList();
    }

    @Override
    public List<String> findAllBooksWithPriceLessThan5OrPriceHigherThan40() {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5),BigDecimal.valueOf(40))
                .stream().map(b -> String.format("%s - $%.2f",b.getTitle(),b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksNotReleasedIn(int year) {
        return this.bookRepository.findAllByReleaseDateNotInYear(year)
                .stream().map(Book::getTitle)
                .toList();
    }

    @Override
    public List<String> findAllBooksWithReleasedDateBefore(String date) {
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
        return this.bookRepository.findAllByReleaseDateBefore(parsedDate)
                .stream()
                .map(b -> String.format("%s %s %.2f",b.getTitle(),b.getEditionType(),b.getPrice()))
                .toList();
    }

    @Override
    public List<String> findAllBooksThatContainStringInTitle(String string) {
        return this.bookRepository.findAllByTitleContainingIgnoreCase(string)
                .stream().map(Book::getTitle)
                .toList();
    }

    @Override
    public List<String> findAllBooksWrittenByAuthorsWithLastNameStartingWith(String string) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(string)
                .stream().map(b -> String.format("%s (%s)",b.getTitle(),b.getAuthor().getLastName())).toList();
    }

    @Override
    public int getCountOfBooksWithTitleLongerThanCount(int count) {
        return this.bookRepository.findAllByTitleSymbolsLongerThan(count)
                .size();
    }

    @Override
    public int getCountOfCopiesByAuthorsNames(String firstName, String lastName) {
        return this.bookRepository.getTotalNumberOfCopiesByAuthor(firstName,lastName);
    }

    @Override
    public BookInfo getBooksParametersByTitle(String title) {
        return this.bookRepository.findByTitle(title);
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
