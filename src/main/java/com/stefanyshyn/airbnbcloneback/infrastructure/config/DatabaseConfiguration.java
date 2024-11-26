package com.stefanyshyn.airbnbcloneback.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"com.stefanyshyn.airbnbcloneback.user.repository",
        "com.stefanyshyn.airbnbcloneback.listing.repository",
        "com.stefanyshyn.airbnbcloneback.booking.repository"})
@EnableTransactionManagement
@EnableJpaAuditing
public class DatabaseConfiguration {
}
