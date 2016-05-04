package com.kmutt.stcp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceConfig.class, PropertiesConfig.class, MailConfig.class})
public class SpringRootConfig {
}