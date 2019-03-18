package com.ashwin.csvfileoperations.configuration;

import com.ashwin.csvfileoperations.util.ValidationHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public ValidationHelper loadHelper(){
        return new ValidationHelper();
    }
}
