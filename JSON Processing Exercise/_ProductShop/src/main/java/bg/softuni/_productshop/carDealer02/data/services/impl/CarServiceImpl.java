package bg.softuni._productshop.carDealer02.data.services.impl;

import bg.softuni._productshop.carDealer02.data.data.dtos.CarImportDto;
import bg.softuni._productshop.carDealer02.data.data.models.Car;
import bg.softuni._productshop.carDealer02.data.data.models.Part;
import bg.softuni._productshop.carDealer02.data.data.repositories.CarRepository;
import bg.softuni._productshop.carDealer02.data.data.repositories.PartRepository;
import bg.softuni._productshop.carDealer02.data.services.CarService;
import bg.softuni._productshop.constants.Paths;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private final ModelMapper mapper;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, Gson gson, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public void seedCars() throws FileNotFoundException {
        FileReader fileReader = new FileReader(Paths.CARS_PATH.toFile());
        CarImportDto[] carImportDtos = gson.fromJson(fileReader, CarImportDto[].class);
        List<Car> carsList = Arrays.stream(carImportDtos)
                .map(dto -> mapper.map(dto, Car.class))
                .map(this::setParts)
                .toList();
        this.carRepository.saveAllAndFlush(carsList);
    }

    @Override
    public void getAllCarsParts() {
        this.carRepository.findAll()
                .forEach(car -> car.getParts().forEach(part -> System.out.println(part.getName())));
    }

    private Car setParts(Car car) {
        Random random = new Random();
        int partsCount = random.nextInt(6 - 3) + 3;
        List<Part> partList = new ArrayList<>();
        while (partList.size() != partsCount){
            Optional<Part> randomPart = partRepository.getRandomPart();
            if(randomPart.isPresent()){
                Part part = randomPart.get();
                if(partList.contains(part)){
                    continue;
                }
                partList.add(part);
                part.getCars().add(car);

            }
        }
        car.setParts(partList);
        return car;
    }
}
