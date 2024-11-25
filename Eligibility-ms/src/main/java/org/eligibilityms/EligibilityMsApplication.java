package org.eligibilityms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("org.eligibilityms")
public class EligibilityMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EligibilityMsApplication.class, args);
    }

}
