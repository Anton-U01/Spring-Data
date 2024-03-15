package bg.softuni._xmlexercise.carDealer.services.impl;

import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.PartImportDto;
import bg.softuni._xmlexercise.carDealer.data.dtos.importDto.PartSeedDto;
import bg.softuni._xmlexercise.carDealer.data.models.Part;
import bg.softuni._xmlexercise.carDealer.data.models.Supplier;
import bg.softuni._xmlexercise.carDealer.data.repositories.PartRepository;
import bg.softuni._xmlexercise.carDealer.data.repositories.SupplierRepository;
import bg.softuni._xmlexercise.carDealer.services.PartService;
import bg.softuni._xmlexercise.constants.Paths;
import bg.softuni._xmlexercise.carDealer.utils.ValidationUtil;
import bg.softuni._xmlexercise.carDealer.utils.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper mapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    private Part setSupplier(Part part) {
        Optional<Supplier> optional = this.supplierRepository.getRandomSupplier();
        if(optional.isPresent()){
           part.setSupplier(optional.get());
        }
        return part;
    }

    @Override
    public void seedParts() throws JAXBException {
        if(partRepository.count() == 0){
            PartImportDto partImportDto = xmlParser.parse(PartImportDto.class, Paths.PARTS_PATH);
            for (PartSeedDto dto : partImportDto.getParts()) {
                if(!validationUtil.isValid(dto)){
                    System.out.println("Entity not valid!");
                    continue;
                }
                Part part = mapper.map(dto, Part.class);
                setSupplier(part);
                partRepository.saveAndFlush(part);
            }
        }
    }
}
