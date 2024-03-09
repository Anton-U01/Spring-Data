package bg.softuni._productshop;

import bg.softuni._productshop.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final SeedService seedService;

    public CommandLineRunnerImpl(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedUsers();
    }
}
