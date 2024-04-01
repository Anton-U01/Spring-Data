package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanoSeedDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolcanoServiceImpl implements VolcanoService {
    private final VolcanoRepository volcanoRepository;
    private final CountryRepository countryRepository;
    private final String VOLCANO_IMPORT = "src/main/resources/files/json/volcanoes.json";

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.volcanoRepository = volcanoRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(VOLCANO_IMPORT)));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        VolcanoSeedDto[] dtos = gson.fromJson(readVolcanoesFileContent(), VolcanoSeedDto[].class);
        for (VolcanoSeedDto dto : dtos) {
            Optional<Volcano> optional = volcanoRepository.findByName(dto.getName());
            if(!validationUtil.isValid(dto) || optional.isPresent()){
                stringBuilder.append("Invalid volcano\n");
                continue;
            }
            Volcano volcano = mapper.map(dto, Volcano.class);
            volcano.setCountry(countryRepository.getById(dto.getCountry()));
            volcanoRepository.saveAndFlush(volcano);
            stringBuilder.append(String.format("Successfully imported volcano %s of type %s\n",volcano.getName(), volcano.getVolcanoType()));
        }

        return stringBuilder.toString();
    }

    @Override
    public String exportVolcanoes() {
        return volcanoRepository.findAllActiveVolcanosWithElevationAbove3000()
                .stream()
                .map(v ->String.format("Volcano: %s\n" +
                        "   *Located in: %s\n" +
                        "   **Elevation: %d\n" +
                        "   ***Last eruption on: %s\n",v.getName(),
                        v.getCountry().getName(),
                        v.getElevation(),
                        v.getLastEruption().toString()))
                        .collect(Collectors.joining());
    }
}