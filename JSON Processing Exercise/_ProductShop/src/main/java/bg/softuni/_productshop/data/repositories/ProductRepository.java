package bg.softuni._productshop.data.repositories;

import bg.softuni._productshop.data.models.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<List<Product>> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(@NotNull BigDecimal price1, @NotNull BigDecimal price2);
}
