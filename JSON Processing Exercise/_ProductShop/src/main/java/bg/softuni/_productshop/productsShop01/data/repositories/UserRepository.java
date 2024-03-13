package bg.softuni._productshop.productsShop01.data.repositories;

import bg.softuni._productshop.productsShop01.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("FROM User ORDER BY RAND() LIMIT 1")
    Optional<User> getRandomUser();

    @Query("SELECT DISTINCT p.seller FROM Product p WHERE p.buyer IS NOT NULL ORDER BY p.seller.lastName,p.seller.firstName")
    Optional<List<User>> findUsersWithSoldProducts();

    @Query("FROM User u WHERE SIZE(u.sold) > 0 ORDER BY SIZE(u.sold) DESC,u.lastName")
    Optional<List<User>> findAllUsersWithAtLeast1ItemSold();

}
