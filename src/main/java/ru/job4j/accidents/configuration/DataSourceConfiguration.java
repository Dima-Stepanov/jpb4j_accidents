package ru.job4j.accidents.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * DataSourceConfiguration создание пула соединений к базе данных.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
@Configuration
@PropertySource("classpath:db.properties")
public class DataSourceConfiguration {
    @Bean
    public DataSource getDataSource(@Value("${jdbc.url}") String url,
                                    @Value("${jdbc.username}") String username,
                                    @Value("${jdbc.password}") String password,
                                    @Value("${jdbc.driver}") String driver) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }
}
