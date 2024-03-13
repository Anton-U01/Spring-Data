package bg.softuni._productshop.carDealer02.data.services.impl;

import bg.softuni._productshop.carDealer02.data.data.dtos.PartImportDto;
import bg.softuni._productshop.carDealer02.data.data.models.Part;
import bg.softuni._productshop.carDealer02.data.data.models.Supplier;
import bg.softuni._productshop.carDealer02.data.data.repositories.PartRepository;
import bg.softuni._productshop.carDealer02.data.data.repositories.SupplierRepository;
import bg.softuni._productshop.carDealer02.data.services.PartService;
import bg.softuni._productshop.constants.Paths;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private final ModelMapper mapper;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, Gson gson, ModelMapper mapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    private Part setSupplier(Part part) {
        Optional<Supplier> optional = this.supplierRepository.getRandomSupplier();
        if(optional.isPresent()){
           part.setSupplier(optional.get());
        }
        return part;
    }

    @Override
    public void seedParts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(Paths.PARTS_PATH.toFile());
        PartImportDto[] partImportDtos = gson.fromJson(fileReader, PartImportDto[].class);
        List<Part> partList = Arrays.stream(partImportDtos)
                .map(dto -> mapper.map(dto, Part.class))
                .map(this::setSupplier)
                .toList();
        this.partRepository.saveAllAndFlush(partList);
    }
}
