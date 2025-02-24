package com.buildingblocks.industries.infra.mainservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EntityScan(basePackages = "com.buildingblocks.industries.infra.mongo.entities")
@EnableReactiveMongoRepositories(basePackages = "com.buildingblocks.industries.infra.mongo.repositories")
public class MongoConfig {
}
