package bg.softuni._gamestore.services.impl;

import bg.softuni._gamestore.data.entities.Game;
import bg.softuni._gamestore.data.repositories.GameRepository;
import bg.softuni._gamestore.services.DetailGameDto;
import bg.softuni._gamestore.services.GameDto;
import bg.softuni._gamestore.services.GameService;
import bg.softuni._gamestore.utils.Validator;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, Validator validator) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public String addGame(GameDto gameDto) {
        if(!validator.isValid(gameDto)){
            return validator.validate(gameDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }

        Game game = modelMapper.map(gameDto,Game.class);
        gameRepository.saveAndFlush(game);

        return String.format("Added %s",game.getTitle());
    }

    @Override
    public String editGame(long id, Map<String, String> map) {
        Optional<Game> optional = gameRepository.findById(id);
        if(optional.isEmpty()){
            return "No such game with given id exists.";
        }
        Game game = optional.get();
        map.forEach((key, value) -> {
            switch (key){
                case "title":
                    game.setTitle(value);
                    break;
                case "price":
                    game.setPrice(Double.parseDouble(value));
                    break;
                case "size":
                    game.setSize(Double.parseDouble(value));
                    break;
                case "trailer":
                    game.setTrailer(value);
                    break;
                case "thumbnail":
                    game.setThumbnail(value);
                    break;
                case "description":
                    game.setDescription(value);
                    break;
                case "releaseDate":
                    game.setReleaseDate(LocalDate.parse(value,
                            DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    break;
            }
        });
        this.gameRepository.saveAndFlush(game);
        return String.format("Edited %s",game.getTitle());
    }

    @Override
    public String deleteGame(long id) {
        Optional<Game> optional = gameRepository.findById(id);
        if(optional.isEmpty()){
            return "No such game with given id exists.";
        }
        Game gameForDelete = optional.get();
        String result = String.format("Deleted %s",gameForDelete.getTitle());
        gameRepository.delete(gameForDelete);
        return result;
    }

    @Override
    public String getAllGames() {
        return gameRepository.findAll().stream()
                .map(g -> String.format("%s %s",g.getTitle(),g.getPrice()))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getDetailsOfGame(String title) {
        Optional<Game> optionalGame = gameRepository.findByTitle(title);
        if(optionalGame.isEmpty()){
            return "No such game with the given title exists";
        }
        DetailGameDto detailGameDto = modelMapper.map(optionalGame.get(), DetailGameDto.class);
        return detailGameDto.toString();
    }


}
