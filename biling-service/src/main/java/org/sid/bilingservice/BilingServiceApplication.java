package org.sid.bilingservice;

import org.sid.bilingservice.entities.Bill;
import org.sid.bilingservice.entities.ProductItem;
import org.sid.bilingservice.model.Customer;
import org.sid.bilingservice.model.Product;
import org.sid.bilingservice.repository.BillRepository;
import org.sid.bilingservice.repository.ProductItemRepository;
import org.sid.bilingservice.service.CustomerRestClient;
import org.sid.bilingservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BilingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return args -> {

            Collection<Product> products = productRestClient.allProduct().getContent();
            Long customerId = 1L;
            Customer customer = customerRestClient.findCustomerById(customerId);
            if (customer ==null) throw new RuntimeException("Customer not found");
            Bill bill=new Bill();
            bill.setBillDate(new Date());
            bill.setCustomerId(customerId);
            Bill savedBill = billRepository.save(bill);
            products.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setBill(savedBill);
                productItem.setProductId(product.getId());
                productItem.setQuantity(1+new Random().nextInt(10));
                productItem.setPrice(product.getPrice());
                productItem.setDiscount(Math.random());
                productItemRepository.save(productItem);
            });
        };
    }
}
