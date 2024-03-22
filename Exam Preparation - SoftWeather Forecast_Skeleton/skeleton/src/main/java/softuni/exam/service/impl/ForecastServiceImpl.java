package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastRootDto;
import softuni.exam.models.dto.ForecastSeedDto;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.dayOfWeek;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements softuni.exam.service.ForecastService {
    private final ForecastRepository forecastRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final CityRepository cityRepository;
    private final String FORECAST_IMPORT = "src/main/resources/files/xml/forecasts.xml";

    public ForecastServiceImpl(ForecastRepository forecastRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil, XmlParser xmlParser, CityRepository cityRepository) {
        this.forecastRepository = forecastRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FORECAST_IMPORT)));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();
        ForecastRootDto rootDto = xmlParser.parse(ForecastRootDto.class, FORECAST_IMPORT);
        for (ForecastSeedDto dto : rootDto.getForecasts()) {
            try {
                dayOfWeek dayOfWeek = softuni.exam.models.entity.dayOfWeek.valueOf(dto.getDayOfWeek());
            } catch (Exception e){
                stringBuilder.append("Invalid forecast");
                continue;
            }
            List<Forecast> optional = forecastRepository.findAllByCityAndDayOfWeek(cityRepository.getById(dto.getCity()), dayOfWeek.valueOf(dto.getDayOfWeek()));
            if(!validationUtil.isValid(dto) || !optional.isEmpty()){
                stringBuilder.append("Invalid forecast");
                continue;
            }
            Forecast forecast = mapper.map(dto, Forecast.class);
            forecast.setCity(cityRepository.getById(dto.getCity()));
            forecastRepository.saveAndFlush(forecast);
            stringBuilder.append(String.format("Successfully import forecast %s - %.2f",forecast.getDayOfWeek(),forecast.getMaxTemperature()));
        }

        return stringBuilder.toString();
    }

    @Override
    public String exportForecasts() {
        return forecastRepository
                .findAllByDayOfWeekAndPopulation()
                .stream()
                .map(forecast -> String.format("City: %s:\n" +
                        "-min temperature: %.2f\n" +
                        "--max temperature: %.2f\n" +
                        "---sunrise: %s\n",forecast.getCity().getCityName(),forecast.getMinTemperature(),forecast.getMaxTemperature(),forecast.getSunset()))
                .collect(Collectors.joining());
    }
}
