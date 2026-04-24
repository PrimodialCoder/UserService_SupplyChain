package com.supply_chain.userservice_supplychain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserServiceSupplyChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceSupplyChainApplication.class, args);
    }

}
