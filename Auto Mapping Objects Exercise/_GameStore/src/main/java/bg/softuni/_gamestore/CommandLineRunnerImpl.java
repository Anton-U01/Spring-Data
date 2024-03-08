package bg.softuni._gamestore;

import bg.softuni._gamestore.services.*;
import jakarta.persistence.Column;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private final ShoppingCartService shoppingCartService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.gameService = gameService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        while (!input.equals("END")){
            String result = "";
            String[] tokens = input.split("\\|");
            switch (tokens[0]){
                case "RegisterUser":
                    result = userService.registerUser(new UserDto(tokens[1],tokens[2],tokens[3],tokens[4]));
                    break;
                case "LoginUser":
                    result = userService.loginUser(tokens[1],tokens[2]);
                    break;
                case "Logout":
                    result = userService.logoutUser();
                    break;
                case "AddGame":
                    result = gameService.addGame(new GameDto(tokens[1],Double.parseDouble(tokens[2]),
                            Double.parseDouble(tokens[3]),tokens[4],tokens[5],tokens[6],
                            LocalDate.parse(tokens[7],
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                    break;
                case "EditGame":
                    Map<String, String> map = Arrays.stream(tokens).skip(2)
                            .map(p -> p.split("="))
                            .collect(Collectors.toMap(p -> p[0], p -> p[1]));
                    result = gameService.editGame(Long.parseLong(tokens[1]),map);
                    break;
                case "DeleteGame":
                    result = gameService.deleteGame(Long.parseLong(tokens[1]));
                    break;
                case "AllGames":
                    result = gameService.getAllGames();
                    break;
                case "DetailGame":
                    result = gameService.getDetailsOfGame(tokens[1]);
                    break;
                case "AddItem":
                    result = shoppingCartService.addItem(tokens[1]);
                    break;
                case "RemoveItem":
                    result = shoppingCartService.removeItem(tokens[1]);
                    break;
                case "BuyItem":
                    result = shoppingCartService.buyItem();
                    break;
            }
            System.out.println(result);

            input = reader.readLine();
        }
    }
}
