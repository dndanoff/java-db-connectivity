package com.github.dndanoff.db;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@ConditionalOnExpression("'${spring.jpa.hibernate.ddl-auto}' == 'none' &&  '${spring.sql.init.mode}' == 'never'")
public class DbPopulator implements CommandLineRunner {

    private final DataSource ds;

    public DbPopulator(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void run(String... args) {
        System.out.println("Initing db....");
        Resource initSchema = new ClassPathResource("scripts/schema.sql");
        Resource initData = new ClassPathResource("scripts/data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
        DatabasePopulatorUtils.execute(databasePopulator, ds);
        System.out.println("DB initialized!");
    }
}
