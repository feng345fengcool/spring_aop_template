package com.indi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({JdbcConfig.class})
@ComponentScan("com.indi")
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy
public class SpringConfig {
}
