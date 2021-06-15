package com.epam.esm.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class DatabaseConfiguration {
    private static final String PROPERTIES_PATH = "/database.properties";
    private static final String DRIVER_CLASSNAME = "db.driver";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USER = "db.user";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final String DATABASE_POOL_SIZE = "db.pool_size";

    @Bean
    public DataSource dataSource() throws IOException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(DatabaseConfiguration.class.getResourceAsStream(PROPERTIES_PATH));
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(databaseProperties.getProperty(DRIVER_CLASSNAME));
        dataSource.setJdbcUrl(databaseProperties.getProperty(DATABASE_URL));
        dataSource.setUsername(databaseProperties.getProperty(DATABASE_USER));
        dataSource.setPassword(databaseProperties.getProperty(DATABASE_PASSWORD));
        dataSource.setMaximumPoolSize(Integer.parseInt(databaseProperties.getProperty(DATABASE_POOL_SIZE)));
        return dataSource;
    }
}
