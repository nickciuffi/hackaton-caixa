package br.gov.caixa.hackaton.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "br.gov.caixa.hackaton.repository.local",
        entityManagerFactoryRef = "localEntityManager",
        transactionManagerRef = "localTransactionManager"
)
public class LocalJPAConfiguration {

    @Bean
    LocalContainerEntityManagerFactoryBean localEntityManager(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("localDataSource") DataSource dataSource
            ){
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("br.gov.caixa.hackaton.entity.local")
                .properties(props)
                .build();
    }

    @Bean
    PlatformTransactionManager localTransactionManager(
            @Qualifier("localEntityManager") LocalContainerEntityManagerFactoryBean emfb
    ){
        assert emfb.getObject() != null;
        return new JpaTransactionManager(emfb.getObject());
    }
}
