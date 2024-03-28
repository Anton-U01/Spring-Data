package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
    @Bean
    public Gson gson(){
        return new GsonBuilder().setPrettyPrinting().create();
    }
    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();}
}
