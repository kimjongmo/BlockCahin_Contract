package com.boot.contract.config;

import com.boot.contract.util.ChainCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ChainCode getChainCode(){
        return new ChainCode();
    }

}
