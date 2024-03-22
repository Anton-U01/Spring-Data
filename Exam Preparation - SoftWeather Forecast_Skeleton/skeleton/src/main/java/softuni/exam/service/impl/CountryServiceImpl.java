package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountrySeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final String COUNTRY_IMPORT = "src/main/resources/files/json/countries.json";

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(COUNTRY_IMPORT)));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        CountrySeedDto[] dtos = gson.fromJson(readCountriesFromFile(), CountrySeedDto[].class);
        for (CountrySeedDto dto : dtos) {
            Optional<Country> optionalCountry = countryRepository.findByCountryName(dto.getCountryName());
            if(!validationUtil.isValid(dto) || optionalCountry.isPresent()){
                stringBuilder.append("Invalid country");
                continue;
            }
            Country country = mapper.map(dto, Country.class);
            countryRepository.saveAndFlush(country);
            stringBuilder.append(String.format("Successfully imported country %s - %s",country.getCountryName(),country.getCurrency()));
        }
        return stringBuilder.toString();
    }
}
