package bg.softuni._xmlexercise.carDealer.services.impl;

import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.CarDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.SaleDiscountDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.SalesDiscountRootDto;
import bg.softuni._xmlexercise.carDealer.data.models.Car;
import bg.softuni._xmlexercise.carDealer.data.models.Customer;
import bg.softuni._xmlexercise.carDealer.data.models.Sale;
import bg.softuni._xmlexercise.carDealer.data.repositories.CarRepository;
import bg.softuni._xmlexercise.carDealer.data.repositories.CustomerRepository;
import bg.softuni._xmlexercise.carDealer.data.repositories.SaleRepository;
import bg.softuni._xmlexercise.carDealer.services.SaleService;
import bg.softuni._xmlexercise.carDealer.utils.XmlParser;
import bg.softuni._xmlexercise.constants.Paths;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final ModelMapper mapper;
    private final XmlParser xmlParser;

    private final List<Double> DISCOUNTS = List.of(0.0,0.05,0.10,0.15,0.20,0.30,0.40,0.50);


    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository, ModelMapper mapper, XmlParser xmlParser) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSales() {
        if(saleRepository.count() == 0){
            for(int i = 0; i < 50; i++){
                Sale sale = new Sale();
                sale.setCar(getRandomCar());
                sale.setCustomer(getRandomCustomer());
                sale.setDiscount(DISCOUNTS.get(ThreadLocalRandom.current().nextInt(1, DISCOUNTS.size())));
                saleRepository.saveAndFlush(sale);
            }
        }

    }

    private Customer getRandomCustomer() {
        return this.customerRepository.findById(
                ThreadLocalRandom.current().nextLong(1,customerRepository.count())
        ).get();
    }

    private Car getRandomCar() {
        return this.carRepository.findById(
                ThreadLocalRandom.current().nextLong(1,carRepository.count())
        ).get();
    }

    @Override
    public void getSales() throws JAXBException {
        List<SaleDiscountDto> dtos = this.saleRepository.findAll()
                .stream()
                .map(s -> {
                    SaleDiscountDto dto = mapper.map(s, SaleDiscountDto.class);
                    CarDto carDto = mapper.map(s.getCar(), CarDto.class);
                    dto.setCar(carDto);
                    dto.setCustomerName(s.getCustomer().getName());
                    dto.setDiscount(s.getDiscount());
                    dto.setPrice(BigDecimal.valueOf(s.getCar()
                            .getParts()
                            .stream()
                            .mapToDouble(p -> p.getPrice().doubleValue()).sum()));
                    double sumWithDiscount = s.getCar()
                            .getParts()
                            .stream()
                            .mapToDouble(p -> p.getPrice().doubleValue()).sum() * (1 - s.getDiscount());
                    dto.setPriceWithDiscount(BigDecimal.valueOf(sumWithDiscount));
                    return dto;
                })
                .toList();
        SalesDiscountRootDto salesDiscountRootDto = new SalesDiscountRootDto();
        salesDiscountRootDto.setSales(dtos);
        xmlParser.writeToFile(SalesDiscountRootDto.class,salesDiscountRootDto, Paths.SALES_DISCOUNTS_OUTPUT);
    }

}
