package ro.msg.learning.shop.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ro.msg.learning.shop.Repositories.interfaces.ILocationRepo;
import ro.msg.learning.shop.Repositories.interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.interfaces.IStockRepo;
import ro.msg.learning.shop.strategies.impl.MostAbundantStrategy;
import ro.msg.learning.shop.strategies.impl.SingleLocationStrategy;
import ro.msg.learning.shop.strategies.interfaces.ILocationStrategy;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-test.properties")
@EnableTransactionManagement
@Profile("test")
public class TestConfig {
    @Autowired
    private Environment env;

    @Bean
    public ILocationStrategy singleStrategy(ILocationRepo locationRepo, IStockRepo stockRepo, IProductRepo productRepo){
        return new SingleLocationStrategy(locationRepo,stockRepo,productRepo);
    }

    @Bean
    public ILocationStrategy abundantStrategy(IStockRepo stockRepo, IProductRepo productRepo) {
        return new MostAbundantStrategy(stockRepo, productRepo);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        System.out.println(env.getProperty("spring.datasource.url"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        return dataSource;
    }
}
