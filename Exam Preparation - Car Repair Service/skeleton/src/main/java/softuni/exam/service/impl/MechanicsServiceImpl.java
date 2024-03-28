package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicSeedDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MechanicsServiceImpl implements MechanicsService {
    private final String MECHANIC_IMPORT = "src/main/resources/files/json/mechanics.json";
    private final MechanicsRepository mechanicRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public MechanicsServiceImpl(MechanicsRepository mechanicRepository, ModelMapper mapper, ValidationUtil validationUtil, Gson gson) {
        this.mechanicRepository = mechanicRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(MECHANIC_IMPORT)));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        MechanicSeedDto[] dtos = gson.fromJson(readMechanicsFromFile(), MechanicSeedDto[].class);
        for (MechanicSeedDto dto : dtos) {
            if(!validationUtil.isValid(dto) || mechanicRepository.findByEmail(dto.getEmail()).isPresent()){
                stringBuilder.append("Invalid mechanic\n");
                continue;
            }
            Mechanic mechanic = mapper.map(dto, Mechanic.class);
            mechanicRepository.saveAndFlush(mechanic);
            stringBuilder.append(String.format("Successfully imported mechanic %s %s\n",mechanic.getFirstName(),mechanic.getLastName()));
        }
        return stringBuilder.toString();
    }
}
