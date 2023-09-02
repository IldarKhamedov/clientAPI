package ru.khamedov.ildar.clientApi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.khamedov.ildar.clientApi.filter.TransactionFilter;

@Configuration
public class AppConfig {

    @Bean
    public TransactionFilter transactionFilter(){
        return new TransactionFilter();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
