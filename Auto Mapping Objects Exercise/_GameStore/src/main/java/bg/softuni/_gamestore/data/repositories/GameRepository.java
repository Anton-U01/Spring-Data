package bg.softuni._gamestore.data.repositories;

import bg.softuni._gamestore.data.entities.Game;
import bg.softuni._gamestore.services.GameTitlePriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    GameTitlePriceDto findAllBy();

    Optional<Game> findByTitle(String title);
}
