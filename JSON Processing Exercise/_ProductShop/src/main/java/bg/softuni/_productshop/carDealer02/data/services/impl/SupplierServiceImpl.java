package bg.softuni._productshop.carDealer02.data.services.impl;

import bg.softuni._productshop.carDealer02.data.data.dtos.SupplierImportDto;
import bg.softuni._productshop.carDealer02.data.data.models.Supplier;
import bg.softuni._productshop.carDealer02.data.data.repositories.SupplierRepository;
import bg.softuni._productshop.carDealer02.data.services.SupplierService;
import bg.softuni._productshop.constants.Paths;
import com.google.gson.Gson;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final Gson gson;

    private final ModelMapper mapper;
    public SupplierServiceImpl(SupplierRepository supplierRepository, Gson gson, ModelMapper mapper) {
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(Paths.SUPPLIER_PATH.toFile());
        SupplierImportDto[] supplierImportDtos = gson.fromJson(fileReader, SupplierImportDto[].class);
        List<Supplier> supplierList = Arrays.stream(supplierImportDtos)
                .map(dto -> mapper.map(dto, Supplier.class))
                .toList();
        this.supplierRepository.saveAllAndFlush(supplierList);
    }
}
