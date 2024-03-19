package bg.softuni._xmlexercise.carDealer.services.impl;

import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.CustomerByBirthDateDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.CustomerByBirthDateExportDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.CustomerSaleExportDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.CustomersSalesDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.CustomerImportDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.CustomerSeedDto;
import bg.softuni._xmlexercise.carDealer.data.models.Customer;
import bg.softuni._xmlexercise.carDealer.data.models.Part;
import bg.softuni._xmlexercise.carDealer.data.repositories.CustomerRepository;
import bg.softuni._xmlexercise.carDealer.services.CustomerService;
import bg.softuni._xmlexercise.constants.Paths;
import bg.softuni._xmlexercise.carDealer.utils.ValidationUtil;
import bg.softuni._xmlexercise.carDealer.utils.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, XmlParser xmlParser, ModelMapper mapper, ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCustomers() throws JAXBException {
        if(customerRepository.count() == 0){
            CustomerImportDto customerImportDto = xmlParser.parse(CustomerImportDto.class, Paths.CUSTOMER_PATH);
            for (CustomerSeedDto dto : customerImportDto.getCustomers()) {
                if(!validationUtil.isValid(dto)){
                    System.out.println("Entity is not valid!");
                    continue;
                }
                Customer customer = mapper.map(dto, Customer.class);
                customerRepository.saveAndFlush(customer);
            }
        }
    }

    @Override
    public void getAllCustomersOrderedByBirthDate() throws JAXBException {
        List<CustomerByBirthDateExportDto> dtos = this.customerRepository.findAllByOrderByBirthDateAscIsYoungDriverAsc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(c -> mapper.map(c, CustomerByBirthDateExportDto.class))
                .toList();
        CustomerByBirthDateDto customer = new CustomerByBirthDateDto(dtos);
        xmlParser.writeToFile(CustomerByBirthDateDto.class,customer,Paths.OUTPUT_ORDERED_CUSTOMERS);
    }

    @Override
    public void getAllCustomersWithBoughtCars() throws JAXBException {
        List<CustomerSaleExportDto> dtos = this.customerRepository.getAllCustomersWithBoughtCars()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> {
                    CustomerSaleExportDto dto = mapper.map(customer, CustomerSaleExportDto.class);
                    dto.setFullName(customer.getName());
                    dto.setBoughtCars(customer.getSales().size());
                    Double totalSum = customer.getSales().stream()
                            .mapToDouble(s -> {
                                double currentSum = s.getCar().getParts().stream().mapToDouble(p -> p.getPrice().doubleValue()).sum() * (1 - s.getDiscount());
                                return currentSum;
                            }).sum();
                    dto.setSpentMoney(totalSum);
                    return dto;
                })
                .sorted(Comparator.comparing(CustomerSaleExportDto::getSpentMoney).reversed()
                        .thenComparing(Comparator.comparing(CustomerSaleExportDto::getBoughtCars).reversed()))
                .toList();
        CustomersSalesDto customersSalesDto = new CustomersSalesDto(dtos);
        xmlParser.writeToFile(CustomersSalesDto.class,customersSalesDto,Paths.CUSTOMERS_TOTAL_SALES);

    }
}
