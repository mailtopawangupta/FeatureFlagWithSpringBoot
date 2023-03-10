package com.amazingbooks.bookservice.config;
import org.ff4j.FF4j;
import org.ff4j.audit.repository.InMemoryEventRepository;
import org.ff4j.property.store.InMemoryPropertyStore;
import org.ff4j.store.InMemoryFeatureStore;
import org.ff4j.store.JdbcFeatureStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FF4jConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public FF4j getFF4j()
    {
        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new JdbcFeatureStore(dataSource));
        //ff4j.setFeatureStore(new InMemoryFeatureStore());
        ff4j.setPropertiesStore(new InMemoryPropertyStore());
        ff4j.setEventRepository(new InMemoryEventRepository());

        //Enable audit and monitoring, default value is false.
        ff4j.audit(true);

        //When evaluating not exiting features, ff4j will create then but disabled.
        ff4j.autoCreate(true);

        //To define RBAC access, the application must have a logged user
        //ff4j.setAuthManager();

        //To define a cache layer to relax the db, multiple implementations
        //ff4j.cache(a cache manager);

        return ff4j;
    }
}
