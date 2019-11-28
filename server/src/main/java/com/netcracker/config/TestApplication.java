package com.netcracker.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.apache.commons.codec.language.Metaphone;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.netcracker.repository")
@ComponentScan(basePackages = "com.netcracker")
@Configuration
@EntityScan("com.netcracker.entity")
@EnableJpaAuditing
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    @Bean
    public Metaphone metaphone() {
        Metaphone metaphone = new Metaphone();
        metaphone.setMaxCodeLen(40);
        return metaphone;
    }

}
