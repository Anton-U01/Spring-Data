package bg.softuni._productshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import java.security.PublicKey;

@org.springframework.context.annotation.Configuration
public class Configuration {

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
         }

}
