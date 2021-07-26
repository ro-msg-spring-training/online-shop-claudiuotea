package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.msg.learning.shop.DTOs.OrderDTO;
import ro.msg.learning.shop.Models.Location;
import ro.msg.learning.shop.Models.Product;
import ro.msg.learning.shop.Models.Stock;
import ro.msg.learning.shop.Repositories.Interfaces.ILocationRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IStockRepo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrderServiceIntegration {
    private static boolean initialized;
    @Autowired
    private MockMvc mvc;
    @Autowired private ObjectMapper mapper;

    @Autowired
    private ILocationRepo locationRepo;
    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private IStockRepo stockRepo;

    @BeforeClass
    public static void init() {
        initialized = false;
    }

    @Before
    public void setUp() throws Exception {
        //TODO: mock LocationStrategy.findLocation and AddressRepo here
        if(!initialized) {
            Product prod1 = productRepo.save(Product.builder()
                    .productCategory(null)
                    .description("First product")
                    .imageUrl("first")
                    .name("First product")
                    .price(BigDecimal.valueOf(3.14))
                    .supplier(null)
                    .weight(5.2)
                    .build());
            Product prod2 = productRepo.save(Product.builder()
                    .productCategory(null)
                    .description("Second product")
                    .imageUrl("Second")
                    .name("Second product")
                    .price(BigDecimal.valueOf(6.14))
                    .supplier(null)
                    .weight(54.2)
                    .build());
            Location location1 = locationRepo.save(Location.builder()
                    .address(null)
                    .name("First location")
                    .stocks(new ArrayList<>())
                    .build());
            Location location2 = locationRepo.save(Location.builder()
                    .address(null)
                    .name("Second location")
                    .stocks(new ArrayList<>())
                    .build());
            stockRepo.save(Stock.builder()
                    .product(prod1)
                    .location(location1)
                    .quantity(10)
                    .build());
            stockRepo.save(Stock.builder()
                    .product(prod1)
                    .location(location2)
                    .quantity(100)
                    .build());
            stockRepo.save(Stock.builder()
                    .product(prod2)
                    .location(location1)
                    .quantity(20)
                    .build());
            stockRepo.save(Stock.builder()
                    .product(prod2)
                    .location(location2)
                    .quantity(10)
                    .build());
            initialized = true;
        }
    }


    @Test
    public void createOrderSuccesfully() throws Exception {
        HashMap<Integer,Integer> prods = new HashMap<>();
        prods.put(1,5);
        prods.put(2,3);

        OrderDTO orderDTO = OrderDTO.builder()
                .city("Bistrita")
                .country("Romania")
                .county("BN")
                .streetAddress("Parcalabului")
                .time(Timestamp.valueOf(LocalDateTime.now()))
                .products(prods)
                .build();

        String json = mapper.writeValueAsString(orderDTO);
        mvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.country").value("Romania"));
    }

    @Test
    public void createOrderFailedDueToMissingStock() throws Exception {
        HashMap<Integer,Integer> prods = new HashMap<>();
        prods.put(1,101);
        prods.put(2,3);

        OrderDTO orderDTO = OrderDTO.builder()
                .city("Bistrita")
                .country("Romania")
                .county("BN")
                .streetAddress("Parcalabului")
                .time(Timestamp.valueOf(LocalDateTime.now()))
                .products(prods)
                .build();

        String json = mapper.writeValueAsString(orderDTO);
        mvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict());
    }
}

