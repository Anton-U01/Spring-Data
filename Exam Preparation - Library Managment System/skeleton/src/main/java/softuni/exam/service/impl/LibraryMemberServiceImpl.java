package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMembersImportDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private final LibraryMemberRepository libraryMemberRepository;
    private final String MEMBERS_IMPORT = "src/main/resources/files/json/library-members.json";

    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(MEMBERS_IMPORT)));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (LibraryMembersImportDto dto : gson.fromJson(readLibraryMembersFileContent(), LibraryMembersImportDto[].class)) {
            Optional<LibraryMember> optional = libraryMemberRepository.findByPhoneNumber(dto.getPhoneNumber());
            if(!validationUtil.isValid(dto) || optional.isPresent()){
                sb.append("Invalid library member\n");
                continue;
            }
            LibraryMember libraryMember = mapper.map(dto, LibraryMember.class);
            libraryMemberRepository.saveAndFlush(libraryMember);
            sb.append(String.format("Successfully imported library member %s - %s\n",libraryMember.getFirstName(),libraryMember.getLastName()));
        }

        return sb.toString();
    }
}
