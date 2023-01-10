package school21.spring.service.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "school21.spring.service")
public class ApplicationConfig {

    @Value("${db.driverName}")
    private String dbDriver;
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(dbDriver);
        return dataSource;
    }
    @Bean
    public UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplateImpl(){
        return new UsersRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public HikariDataSource dataSourceHikari(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(dbDriver);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public UsersRepositoryJdbcImpl userRepositoryJdbc(){
        return new UsersRepositoryJdbcImpl(dataSourceHikari());
    }
}
