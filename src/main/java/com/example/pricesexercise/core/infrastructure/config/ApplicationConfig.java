package com.example.pricesexercise.core.infrastructure.config;

import com.example.pricesexercise.core.infrastructure.repository.InH2Prices;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;


@Configuration
@Lazy
public class ApplicationConfig {
    @Bean
    @Lazy
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Lazy
    public PricesRepository pricesRepository(){
        return new InH2Prices(dataSource(dataSourceProperties()));
    }
}
