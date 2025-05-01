package ru.mirea.deliveryservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"ru.mirea.deliveryservice.repos"})
public class DBConfig {
}
