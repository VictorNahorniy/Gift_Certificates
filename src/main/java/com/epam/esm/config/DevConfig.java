package com.epam.esm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;


/**
 * The type Dev config.
 * <p>
 *     This class is used to configure the application for development.
 *     It is used to configure the database connection.
 *     It is used to configure the JdbcTemplate.
 *     It is used to configure the component scan.
 *     It is used to configure the web mvc.
 *     It is used to configure the property source.
 * </p>
 * <p>
 *     This class is annotated as @Configuration.
 *     This class is annotated as @EnableWebMvc.
 *     This class is annotated as @ComponentScan.
 *     This class is annotated as @PropertySource.
 * </p>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.epam.esm")
@PropertySource("classpath:application.properties")
public class DevConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
