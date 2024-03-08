package bg.softuni._gamestore.services.impl;

import bg.softuni._gamestore.data.entities.Game;
import bg.softuni._gamestore.data.entities.User;
import bg.softuni._gamestore.data.repositories.GameRepository;
import bg.softuni._gamestore.data.repositories.UserRepository;
import bg.softuni._gamestore.services.ShoppingCartService;
import bg.softuni._gamestore.services.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private Set<Game> gameList;


    public ShoppingCartServiceImpl(UserService userService, UserRepository userRepository, GameRepository gameRepository, Set<Game> gameList) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.gameList = gameList;
    }

    @Override
    public String addItem(String title) {
        Optional<Game> optionalGame = gameRepository.findByTitle(title);
        if(optionalGame.isEmpty()){
            return "No such game with this title exists.";
        }
        Game game = optionalGame.get();
        gameList.add(game);
        return String.format("%s added to cart.",game.getTitle());
    }
    public String removeItem(String title) {
        Optional<Game> optionalGame = gameRepository.findByTitle(title);
        if(optionalGame.isEmpty()){
            return "No such game with this title exists.";
        }
        Game game = optionalGame.get();
        gameList.remove(game);
        return String.format("%s removed from cart.",game.getTitle());
    }

    @Override
    public String buyItem() {
        User loggedInUser = userService.getLoginUser();
        if(loggedInUser == null){
            return "Not logged user.";
        }
        loggedInUser.getGames().addAll(gameList);
        userRepository.saveAndFlush(loggedInUser);

        String result =  "Successfully bought games:\n" +
                gameList.stream().map(g -> " -" + g.getTitle())
                        .collect(Collectors.joining("\n"));

        this.gameList = new HashSet<>();

        return result;
    }


}
