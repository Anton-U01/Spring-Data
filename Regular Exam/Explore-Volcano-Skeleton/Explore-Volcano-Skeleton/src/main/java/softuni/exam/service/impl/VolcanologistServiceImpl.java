package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanologistRootDto;
import softuni.exam.models.dto.VolcanologistSeedDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class VolcanologistServiceImpl  implements VolcanologistService {
    private final VolcanologistRepository volcanologistRepository;
    private final VolcanoRepository volcanoRepository;
    private final String VOLCANOLOGIST_IMPORT = "src/main/resources/files/xml/volcanologists.xml";

    private final ModelMapper mapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, VolcanoRepository volcanoRepository, ModelMapper mapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoRepository = volcanoRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(VOLCANOLOGIST_IMPORT)));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();
        VolcanologistRootDto volcanologistRootDto = xmlParser.fromFile(VOLCANOLOGIST_IMPORT, VolcanologistRootDto.class);
        for (VolcanologistSeedDto dto : volcanologistRootDto.getVolcanologists()) {
            Optional<Volcanologist> optionalVolcanologist = volcanologistRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
            Optional<Volcano> optionalVolcano = volcanoRepository.findById(dto.getVolcano());
            if(!validationUtil.isValid(dto) || optionalVolcano.isEmpty() || optionalVolcanologist.isPresent()){
                stringBuilder.append("Invalid volcanologist\n");
                continue;
            }
            Volcanologist volcanologist = mapper.map(dto, Volcanologist.class);
            volcanologist.setVolcano(optionalVolcano.get());
            volcanologistRepository.saveAndFlush(volcanologist);
            stringBuilder.append(String.format("Successfully imported volcanologist %s %s\n",volcanologist.getFirstName(),volcanologist.getLastName()));
        }

        return stringBuilder.toString();
    }
}