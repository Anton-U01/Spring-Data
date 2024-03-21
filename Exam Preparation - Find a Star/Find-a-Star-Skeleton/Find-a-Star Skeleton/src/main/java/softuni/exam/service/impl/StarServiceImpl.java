package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarSeedDto;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {
    private final String STAR_IMPORT = "src/main/resources/files/json/stars.json";
    private final StarRepository starRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final ConstellationRepository constellationRepository;


    public StarServiceImpl(StarRepository starRepository, Gson gson, ModelMapper mapper, ValidationUtil validationUtil, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.constellationRepository = constellationRepository;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(STAR_IMPORT)));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();
        StarSeedDto[] starSeedDtos = gson.fromJson(readStarsFileContent(), StarSeedDto[].class);

        for (StarSeedDto dto : starSeedDtos) {
            Optional<Star> optional = starRepository.findByName(dto.getName());
            if(!validationUtil.isValid(dto) || optional.isPresent()){
                 sb.append("Invalid star\n");
                 continue;
            }
            Star star = mapper.map(dto, Star.class);
            star.setStarType(StarType.valueOf(dto.getStarType()));
            star.setConstellation(constellationRepository.getById(dto.getConstellation()));
            starRepository.saveAndFlush(star);
            sb.append(String.format("Successfully imported star %s - %.2f light years\n",star.getName(),star.getLightYears()));
        }

        return sb.toString();
    }

    @Override
    public String exportStars() {
        StringBuilder sb = new StringBuilder();
        this.starRepository
                .findAllByStarTypeAndNoObservers()
                .stream()
                .map(star -> String.format("Star: %s\n" +
                        "   *Distance: %.2f light years\n" +
                        "   **Description: %s\n" +
                        "   ***Constellation: %s\n",star.getName(),star.getLightYears(),star.getDescription(),star.getConstellation().getName()))
                .forEach(sb::append);
        return sb.toString();
    }
}
