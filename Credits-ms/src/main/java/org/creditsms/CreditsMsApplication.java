package org.creditsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CreditsMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditsMsApplication.class, args);
    }

}
