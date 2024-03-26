package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookImportDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.Genre;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final String BOOK_IMPORT = "src/main/resources/files/json/books.json";
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(BOOK_IMPORT)));
    }

    @Override
    public String importBooks() throws IOException {
        BookImportDto[] bookImportDtos = gson.fromJson(readBooksFromFile(), BookImportDto[].class);
        StringBuilder sb = new StringBuilder();
        for (BookImportDto dto : bookImportDtos) {
            Book book = mapper.map(dto, Book.class);
            Optional<Book> optional = bookRepository.findByTitle(book.getTitle());
            if(!validationUtil.isValid(book) || optional.isPresent()){
                sb.append("Invalid book\n");
                continue;
            }
            book.setGenre(Genre.valueOf(dto.getGenre()));
            bookRepository.saveAndFlush(book);
            sb.append(String.format("Successfully imported book %s - %s\n",book.getAuthor(),book.getTitle()));
        }
        return sb.toString();
    }
}
