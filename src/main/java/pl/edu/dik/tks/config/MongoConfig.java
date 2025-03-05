package pl.edu.dik.tks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public String mongoConnetionString() {

        // TODO: Implement loading form application.properties
        return "someString";
    }

}
