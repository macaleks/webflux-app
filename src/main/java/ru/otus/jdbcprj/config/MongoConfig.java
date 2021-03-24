package ru.otus.jdbcprj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "ru.otus.jdbcprj.repository")
@Configuration
public class MongoConfig {
}
