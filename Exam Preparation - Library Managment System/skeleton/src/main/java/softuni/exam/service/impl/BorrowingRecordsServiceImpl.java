package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookSeedDto;
import softuni.exam.models.dto.BorrowingRecordImportDto;
import softuni.exam.models.dto.BorrowingRecordRootDto;
import softuni.exam.models.dto.LibraryMembersImportDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final String RECORD_IMPORT = "src/main/resources/files/xml/borrowing-records.xml";

    private final XmlParser xmlParser;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, XmlParser xmlParser, ModelMapper mapper, ValidationUtil validationUtil1, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.validationUtil = validationUtil1;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(RECORD_IMPORT)));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        BorrowingRecordRootDto rootDto = xmlParser.parse(BorrowingRecordRootDto.class, RECORD_IMPORT);
        for (BorrowingRecordImportDto recordDto : rootDto.getBorrowingRecords()) {
            Optional<Book> optionalBook = bookRepository.findByTitle(recordDto.getBook().getTitle());
            Optional<LibraryMember> optionalLibraryMember = libraryMemberRepository.findById(recordDto.getMember().getId());
            if(!validationUtil.isValid(recordDto) || optionalBook.isEmpty() || optionalLibraryMember.isEmpty()){
                sb.append("Invalid borrowing record\n");
                continue;
            }
            BorrowingRecord borrowingRecord = mapper.map(recordDto, BorrowingRecord.class);
            Book book = optionalBook.get();
            borrowingRecord.setBook(book);
            LibraryMember libraryMember = optionalLibraryMember.get();
            borrowingRecord.setLibraryMember(libraryMember);
            borrowingRecordRepository.saveAndFlush(borrowingRecord);
            sb.append(String.format("Successfully imported borrowing record %s - %s\n",borrowingRecord.getBook().getTitle(),borrowingRecord.getBorrowDate().toString()));
        }

        return sb.toString();
    }

    @Override
    public String exportBorrowingRecords() {
        List<BorrowingRecord> records = borrowingRecordRepository.findAllByGenreOrderByBorrowDate();
        return records.stream()
                .map(record -> String.format("Book title: %s\n" +
                        "*Book author: %s\n" +
                        "**Date borrowed: %s\n" +
                        "***Borrowed by: %s %s\n",record.getBook().getTitle(),record.getBook().getAuthor(),
                        record.getBorrowDate().toString(),record.getLibraryMember().getFirstName(),record.getLibraryMember().getLastName()))
                .collect(Collectors.joining());
    }
}
