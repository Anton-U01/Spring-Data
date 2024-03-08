package bg.softuni._gamestore.services;

import bg.softuni._gamestore.data.entities.Game;

import java.util.Map;
import java.util.Optional;

public interface GameService {
    String addGame(GameDto gameDto);

    String editGame(long id, Map<String, String> map);

    String deleteGame(long id);

    String getAllGames();

    String getDetailsOfGame(String title);
}
