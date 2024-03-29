package bg.softuni._xmlexercise.carDealer.services.impl;


import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.*;
import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.CarImportDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.CarSeedDto;
import bg.softuni._xmlexercise.carDealer.data.models.Car;
import bg.softuni._xmlexercise.carDealer.data.models.Part;
import bg.softuni._xmlexercise.carDealer.data.repositories.CarRepository;
import bg.softuni._xmlexercise.carDealer.data.repositories.PartRepository;
import bg.softuni._xmlexercise.carDealer.services.CarService;
import bg.softuni._xmlexercise.constants.Paths;
import bg.softuni._xmlexercise.carDealer.utils.ValidationUtil;
import bg.softuni._xmlexercise.carDealer.utils.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper mapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCars() throws FileNotFoundException, JAXBException {
        if(carRepository.count() == 0){
            CarImportDto carImportDto = xmlParser.parse(CarImportDto.class, Paths.CARS_PATH);
            for (CarSeedDto dto : carImportDto.getCars()) {
                if(!validationUtil.isValid(dto)){
                    System.out.println("Entity not valid!");
                    continue;
                }
                Car car = mapper.map(dto, Car.class);
                setParts(car);
                carRepository.saveAndFlush(car);
            }
        }
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
            }
        }
        car.setParts(partList);
        return car;
    }

    @Override
    public void getAllToyotaCars() throws JAXBException {
        List<CarToyotaExportDto> dtos = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota")
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(c -> mapper.map(c, CarToyotaExportDto.class))
                .toList();
        CarsToyotaDto carsToyotaDto = new CarsToyotaDto();
        carsToyotaDto.setCars(dtos);

        xmlParser.writeToFile(CarsToyotaDto.class,carsToyotaDto,Paths.OUTPUT_TOYOTA_CARS);
    }

    @Override
    public void getAllCarsWithTheirParts() throws JAXBException {
        List<AllCarExportDto> dtos = this.carRepository.getAllBy()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> {
                    List<Part> parts = car.getParts();
                    List<PartExportDto> partDtos = parts.stream().map(p -> mapper.map(p, PartExportDto.class)).toList();
                    PartDto partDto = new PartDto();
                    partDto.setParts(partDtos);
                    AllCarExportDto carExportDto = mapper.map(car, AllCarExportDto.class);
                    carExportDto.setParts(partDto);
                    return carExportDto;
                })
                .toList();
        AllCarDto allCarDto = new AllCarDto();
        allCarDto.setCars(dtos);
        xmlParser.writeToFile(AllCarDto.class,allCarDto,Paths.CARS_AND_PARTS_OUTPUT);

    }


}
