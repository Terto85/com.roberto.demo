package com.roberto.demo.application;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = { "com.roberto.demo.infra.db.models" })
@ComponentScan(basePackages = { "com.roberto.demo.ports.rest", "com.roberto.demo.domain", "com.roberto.demo.infra" } )
@EnableJpaRepositories("com.roberto.demo.infra.db.repositories")
public class DemoApplicationConfig {
}
