package br.gov.caixa.hackaton.config;

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
        basePackages = "br.gov.caixa.hackaton.repository.remote",
        entityManagerFactoryRef = "remoteEntityManager",
        transactionManagerRef = "remoteTransactionManager"
)
public class RemoteJPAConfiguration {

    @Bean
    LocalContainerEntityManagerFactoryBean remoteEntityManager(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("remoteDataSource") DataSource dataSource
    ){
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "none");
        props.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("br.gov.caixa.hackaton.entity.remote")
                .properties(props)
                .build();
    }

    @Bean
    PlatformTransactionManager remoteTransactionManager(
            @Qualifier("remoteEntityManager") LocalContainerEntityManagerFactoryBean emfb
    ){
        return new JpaTransactionManager(emfb.getObject());
    }
}
