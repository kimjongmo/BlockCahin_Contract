package com.contract.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.contract.common.repository"})
@EntityScan(basePackages = {"com.contract.common.model"})
public class JpaConfig {
}
