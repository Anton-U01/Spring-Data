package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CitySeedDto;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final String CITY_IMPORT = "src/main/resources/files/json/cities.json";

    public CityServiceImpl(CityRepository cityRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.cityRepository = cityRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(CITY_IMPORT)));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        CitySeedDto[] dtos = gson.fromJson(readCitiesFileContent(), CitySeedDto[].class);
        for (CitySeedDto dto : dtos) {
            Optional<City> optional = cityRepository.findByCityName(dto.getCityName());
            if(!validationUtil.isValid(dto) || optional.isPresent()){
                stringBuilder.append("Invalid city");
                continue;
            }
            City city = mapper.map(dto, City.class);
            cityRepository.saveAndFlush(city);
            stringBuilder.append(String.format("Successfully imported city %s - %d",city.getCityName(),city.getPopulation()));
        }
        return stringBuilder.toString();
    }
}
