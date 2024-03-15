package bg.softuni._xmlexercise.carDealer.services.impl;

import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.CustomerByBirthDateDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.CustomerByBirthDateExportDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.CustomerImportDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.CustomerSeedDto;
import bg.softuni._xmlexercise.carDealer.data.models.Customer;
import bg.softuni._xmlexercise.carDealer.data.repositories.CustomerRepository;
import bg.softuni._xmlexercise.carDealer.services.CustomerService;
import bg.softuni._xmlexercise.constants.Paths;
import bg.softuni._xmlexercise.carDealer.utils.ValidationUtil;
import bg.softuni._xmlexercise.carDealer.utils.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
        List<CustomerByBirthDateExportDto> dtos = this.customerRepository.getAllOrderByBirthDateAndYoungDriver()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(c -> mapper.map(c, CustomerByBirthDateExportDto.class))
                .toList();
        CustomerByBirthDateDto customer = new CustomerByBirthDateDto(dtos);
        xmlParser.writeToFile(CustomerByBirthDateDto.class,customer,Paths.OUTPUT_ORDERED_CUSTOMERS);
    }
}
