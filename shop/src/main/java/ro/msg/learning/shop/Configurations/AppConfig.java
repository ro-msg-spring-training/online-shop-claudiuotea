package ro.msg.learning.shop.Configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ro.msg.learning.shop.Repositories.Interfaces.ILocationRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IStockRepo;
import ro.msg.learning.shop.Strategies.Impl.MostAbundantStrategy;
import ro.msg.learning.shop.Strategies.Impl.SingleLocationStrategy;
import ro.msg.learning.shop.Strategies.Interfaces.ILocationStrategy;

@Configuration
@Profile("!test")
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
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
