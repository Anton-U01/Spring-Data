package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerRootDto;
import softuni.exam.models.dto.AstronomerSeedDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {
    private final String ASTRONOMER_IMPORT = "src/main/resources/files/xml/astronomers.xml";
    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
    private final XmlParser xmlParser;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, ValidationUtil validationUtil, ModelMapper mapper, XmlParser xmlParser) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(ASTRONOMER_IMPORT)));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        AstronomerRootDto astronomerRootDto = xmlParser.parse(AstronomerRootDto.class, ASTRONOMER_IMPORT);
        for (AstronomerSeedDto dto : astronomerRootDto.getAstronomers()) {

            Optional<Astronomer> optionalAstronomer = astronomerRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
            Optional<Star> optionalStar = starRepository.findById(dto.getObservingStarId());

            if(!validationUtil.isValid(dto) || optionalAstronomer.isPresent() || optionalStar.isEmpty()){
                sb.append("Invalid astronomer\n");
                continue;
            }
            Astronomer astronomer = mapper.map(dto, Astronomer.class);
            astronomer.setObservingStar(optionalStar.get());
            astronomerRepository.saveAndFlush(astronomer);
            sb.append(String.format("Successfully imported astronomer %s %s - %.2f\n",astronomer.getFirstName(),astronomer.getLastName(),astronomer.getAverageObservationHours()));
        }
        return sb.toString();
    }
}
