package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);
    List<String> findAllBooksTitleWithAgeRestriction(String ageRestriction);
    List<String> findAllGoldBooksWithLessThan5000Copies();
    List<String> findAllBooksWithPriceLessThan5OrPriceHigherThan40();
    List<String> findAllBooksNotReleasedIn(int year);
    List<String> findAllBooksWithReleasedDateBefore(String date);
    List<String> findAllBooksThatContainStringInTitle(String string);
    List<String> findAllBooksWrittenByAuthorsWithLastNameStartingWith(String string);
    int getCountOfBooksWithTitleLongerThanCount(int count);
    int getCountOfCopiesByAuthorsNames(String firstName,String lastName);
    BookInfo getBooksParametersByTitle(String title);
}
