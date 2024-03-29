package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartSeedDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

@Service
public class PartsServiceImpl implements PartsService {
    private final String PART_IMPORT = "src/main/resources/files/json/parts.json";
    private final PartsRepository partRepository;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;


    public PartsServiceImpl(PartsRepository partRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.partRepository = partRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PART_IMPORT)));
    }

    @Override
    public String importParts() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setMinimumFractionDigits(1);
        decimalFormat.setMaximumFractionDigits(2);
        PartSeedDto[] dtos = gson.fromJson(readPartsFileContent(), PartSeedDto[].class);
        for (PartSeedDto dto : dtos) {
            if (!validationUtil.isValid(dto) || partRepository.findByPartName(dto.getPartName()).isPresent()) {
                stringBuilder.append("Invalid part\n");
                continue;
            }
            Part part = mapper.map(dto, Part.class);
            partRepository.saveAndFlush(part);
            stringBuilder.append(String.format("Successfully imported part %s - %s\n", part.getPartName(),decimalFormat.format(part.getPrice())));
        }

        return stringBuilder.toString();
    }




}
