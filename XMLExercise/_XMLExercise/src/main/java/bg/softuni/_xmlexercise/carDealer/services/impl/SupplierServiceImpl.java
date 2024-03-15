package bg.softuni._xmlexercise.carDealer.services.impl;


import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.SupplierImportDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.SupplierSeedDto;
import bg.softuni._xmlexercise.carDealer.data.models.Supplier;
import bg.softuni._xmlexercise.carDealer.data.repositories.SupplierRepository;
import bg.softuni._xmlexercise.carDealer.services.SupplierService;
import bg.softuni._xmlexercise.constants.Paths;
import bg.softuni._xmlexercise.carDealer.utils.ValidationUtil;
import bg.softuni._xmlexercise.carDealer.utils.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final XmlParser xmlParser;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    public SupplierServiceImpl(SupplierRepository supplierRepository, XmlParser xmlParser, ModelMapper mapper, ValidationUtil validationUtil) {
        this.supplierRepository = supplierRepository;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException, JAXBException {
        if(supplierRepository.count() == 0){
            SupplierImportDto supplierImportDto = xmlParser.parse(SupplierImportDto.class, Paths.SUPPLIER_PATH);
            for (SupplierSeedDto supplierSeedDto : supplierImportDto.getSuppliers()) {
                if(!validationUtil.isValid(supplierSeedDto)){
                    this.validationUtil.getViolation(supplierSeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                Supplier supplier = mapper.map(supplierSeedDto,Supplier.class);
                this.supplierRepository.saveAndFlush(supplier);
            }

        }
    }
}
