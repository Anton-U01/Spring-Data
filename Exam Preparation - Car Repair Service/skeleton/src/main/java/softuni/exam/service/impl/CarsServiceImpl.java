package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarImportDto;
import softuni.exam.models.dto.CarRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.CarType;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CarsServiceImpl implements CarsService {
    private static String CARS_FILE_PATH = "src/main/resources/files/xml/cars.xml";
    private final CarsRepository carRepository;
    private final ModelMapper mapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public CarsServiceImpl(CarsRepository carRepository, ModelMapper mapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(CARS_FILE_PATH)));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();
        CarRootDto rootDto = xmlParser.parse(CarRootDto.class, CARS_FILE_PATH);
        for (CarImportDto dto : rootDto.getCars()) {
            if(!validationUtil.isValid(dto) || carRepository.findByPlateNumber(dto.getPlateNumber()).isPresent()){
                stringBuilder.append("Invalid car\n");
                continue;
            }
            Car car = mapper.map(dto, Car.class);
            car.setCarType(CarType.valueOf(dto.getCarType()));
            carRepository.saveAndFlush(car);
            stringBuilder.append(String.format("Successfully imported car %s - %s\n",car.getCarMake(),car.getCarModel()));
        }

        return stringBuilder.toString();
    }
}
