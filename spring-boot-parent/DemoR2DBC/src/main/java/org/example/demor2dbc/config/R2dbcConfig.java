package org.example.demor2dbc.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.r2dbc.OptionsCapableConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

@Configuration
public class R2dbcConfig {
//
//    @Bean
//    @Primary
//    public ConnectionFactory connectionFactory() {
//        PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder()
//                .host("localhost")
//                .port(5432)
//                .database("r2dbctest")
//                .username("postgres")
//                .password("0000")
//                .build();
//
//        ConnectionFactory connectionFactory = new PostgresqlConnectionFactory(config);
//
//        ConnectionPoolConfiguration poolConfig = ConnectionPoolConfiguration
//                .builder(connectionFactory)
//                .initialSize(20)
//                .maxSize(1000)
//                .maxIdleTime(Duration.ofMinutes(30))
//                .build();
//
//        ConnectionPool pool = new ConnectionPool(poolConfig);
//
//        // Оборачиваем в OptionsCapableConnectionFactory для совместимости с Spring Boot
//        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
//                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
//                .option(ConnectionFactoryOptions.HOST, "localhost")
//                .option(ConnectionFactoryOptions.PORT, 5432)
//                .option(ConnectionFactoryOptions.DATABASE, "r2dbctest")
//                .option(ConnectionFactoryOptions.USER, "postgres")
//                .option(ConnectionFactoryOptions.PASSWORD, "0000")
//                .build();
//
//        return new OptionsCapableConnectionFactory(options, pool);
//    }
}
