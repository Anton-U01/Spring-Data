package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {
    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.genre = 'SCIENCE_FICTION' AND br.borrowDate < '2021-09-10' ORDER BY br.borrowDate DESC")
    List<BorrowingRecord> findAllByGenreOrderByBorrowDate();
}
