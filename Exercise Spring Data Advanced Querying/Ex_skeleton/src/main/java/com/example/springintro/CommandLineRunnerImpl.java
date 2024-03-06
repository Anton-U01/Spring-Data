package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.repository.BookInfo;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //printAllBooksAfterYear(2000);
        //printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //printAllAuthorsAndNumberOfTheirBooks();
        //printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
        //bookService.findAllBooksTitleWithAgeRestriction("teEN").forEach(System.out::println);
        //bookService.findAllGoldBooksWithLessThan5000Copies().forEach(System.out::println);
        //bookService.findAllBooksWithPriceLessThan5OrPriceHigherThan40().forEach(System.out::println);
        //bookService.findAllBooksNotReleasedIn(1998).forEach(System.out::println);
        //bookService.findAllBooksWithReleasedDateBefore("30-12-1989").forEach(System.out::println);
        //authorService.getAllAuthorsWithNameEndingWith("dy").forEach(System.out::println);
        //bookService.findAllBooksThatContainStringInTitle("WOR").forEach(System.out::println);
        //bookService.findAllBooksWrittenByAuthorsWithLastNameStartingWith("gr").forEach(System.out::println);
        //System.out.println(bookService.getCountOfBooksWithTitleLongerThanCount(40));
        //System.out.println(bookService.getCountOfCopiesByAuthorsNames("Roger","Porter"));
       //getParametersByTitle("Things Fall Apart");
    }

    private void getParametersByTitle(String title) {
        BookInfo bookInfo = bookService.getBooksParametersByTitle(title);
        System.out.println(bookInfo.getTitle() + " " + bookInfo.getEditionType() + " " + bookInfo.getAgeRestriction() + " " + bookInfo.getPrice());
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
