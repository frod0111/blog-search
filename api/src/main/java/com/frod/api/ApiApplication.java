package com.frod.api;

import com.frod.api.configuration.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(
        scanBasePackages = {"com.frod.api", "com.frod.core"}
)
@EntityScan(basePackageClasses = {Jsr310Converters.class},
        basePackages = {"com.frod.core"}
)
@EnableJpaRepositories(basePackages = {"com.frod.core"})
@ImportAutoConfiguration(value = {
        SwaggerConfiguration.class
})
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}