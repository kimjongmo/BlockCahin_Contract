package com.contract.front.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.contract.common","com.contract.front"})
public class AppConfig {

}
