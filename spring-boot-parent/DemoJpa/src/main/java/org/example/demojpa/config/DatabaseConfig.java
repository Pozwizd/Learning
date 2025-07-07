package org.example.demojpa.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfig {

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));
        populator.addScript(new ClassPathResource("data.sql"));
        initializer.setDatabasePopulator(populator);

        ResourceDatabasePopulator cleanupPopulator = new ResourceDatabasePopulator();
        cleanupPopulator.addScript(new ClassPathResource("cleanup.sql"));
        initializer.setDatabaseCleaner(cleanupPopulator);

        return initializer;
    }
}
