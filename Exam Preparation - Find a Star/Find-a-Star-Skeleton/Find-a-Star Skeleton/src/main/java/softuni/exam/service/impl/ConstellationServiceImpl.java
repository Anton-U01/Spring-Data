package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final String CONSTELLATION_IMPORT = "src/main/resources/files/json/constellations.json";

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, ModelMapper mapper, ValidationUtil validationUtil) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(CONSTELLATION_IMPORT)));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();
        ConstellationSeedDto[] constellationSeedDtos = gson.fromJson(readConstellationsFromFile(), ConstellationSeedDto[].class);
        for (ConstellationSeedDto dto : constellationSeedDtos) {
            Optional<Constellation> optional = constellationRepository.findByName(dto.getName());
            if(!validationUtil.isValid(dto) || optional.isPresent()){
                sb.append("Invalid constellation\n");
                continue;
            }
            Constellation constellation = mapper.map(dto,Constellation.class);
            constellationRepository.saveAndFlush(constellation);
            sb.append(String.format("Successfully imported constellation %s - %s\n",constellation.getName(),constellation.getDescription()));
        }
        return sb.toString();
    }
}
