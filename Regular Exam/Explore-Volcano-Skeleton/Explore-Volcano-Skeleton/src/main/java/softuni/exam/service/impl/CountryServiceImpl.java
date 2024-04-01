package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountrySeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final String COUNTRY_PATH = "src/main/resources/files/json/countries.json";

    private final Gson gson;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ModelMapper mapper, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(COUNTRY_PATH)));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        CountrySeedDto[] dtos = gson.fromJson(readCountriesFromFile(), CountrySeedDto[].class);
        for (CountrySeedDto dto : dtos) {
            Optional<Country> optional = countryRepository.findByName(dto.getName());
            if(!validationUtil.isValid(dto) || optional.isPresent()){
                stringBuilder.append("Invalid country\n");
                continue;
            }
            Country country = mapper.map(dto, Country.class);
            countryRepository.saveAndFlush(country);
            stringBuilder.append(String.format("Successfully imported country %s - %s\n",country.getName(),country.getCapital()));
        }

        return stringBuilder.toString();
    }
}
