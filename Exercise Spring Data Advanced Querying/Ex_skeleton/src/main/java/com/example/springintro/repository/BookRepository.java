package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);


    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice,BigDecimal higherPrice);
    @Query("FROM Book b WHERE YEAR(b.releaseDate) <> :year")
    List<Book> findAllByReleaseDateNotInYear(int year);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByTitleContainingIgnoreCase(String string);
    List<Book> findAllByAuthorLastNameStartingWith(String string);
    @Query("FROM Book b WHERE LENGTH(b.title) > :count ")
    List<Book> findAllByTitleSymbolsLongerThan(int count);

    @Query("SELECT SUM(b.copies) FROM Book b JOIN Author a ON a.id = b.author.id " +
            "WHERE a.firstName = :firstName AND a.lastName = :lastName " +
            "GROUP BY a.id ORDER BY SUM(b.copies) DESC")
    int getTotalNumberOfCopiesByAuthor(String firstName,String lastName);


    BookInfo findByTitle(String title);

}
