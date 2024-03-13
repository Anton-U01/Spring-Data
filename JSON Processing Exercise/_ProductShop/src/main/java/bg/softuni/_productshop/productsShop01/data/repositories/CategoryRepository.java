package bg.softuni._productshop.productsShop01.data.repositories;

import bg.softuni._productshop.productsShop01.data.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("FROM Category c ORDER BY RAND() LIMIT 1")
    Optional<Category> getRandomCategory();

    @Query("FROM Category c ORDER BY SIZE(c.products) DESC")
    List<Category> findAllByOrderByProductsCount();
}
