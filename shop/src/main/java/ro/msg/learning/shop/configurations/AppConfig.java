package ro.msg.learning.shop.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ro.msg.learning.shop.Repositories.interfaces.ILocationRepo;
import ro.msg.learning.shop.Repositories.interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.interfaces.IStockRepo;
import ro.msg.learning.shop.strategies.impl.MostAbundantStrategy;
import ro.msg.learning.shop.strategies.impl.SingleLocationStrategy;
import ro.msg.learning.shop.strategies.interfaces.ILocationStrategy;

@Configuration
@Profile("!test")
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FlywayMigrationStrategy cleanMigrateStrategy() {
        return flyway -> {
            flyway.repair();
            flyway.migrate();
        };
    }

    @Bean
    public ILocationStrategy singleStrategy(ILocationRepo locationRepo, IStockRepo stockRepo, IProductRepo productRepo) {
        return new SingleLocationStrategy(locationRepo, stockRepo, productRepo);
    }

    @Bean
    public ILocationStrategy abundantStrategy(IStockRepo stockRepo, IProductRepo productRepo) {
        return new MostAbundantStrategy(stockRepo, productRepo);
    }
}
