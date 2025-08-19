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
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("br.gov.caixa.hackaton.entity.remote")
                .build();
    }

    @Bean
    PlatformTransactionManager remoteTransactionManager(
            @Qualifier("remoteEntityManager") LocalContainerEntityManagerFactoryBean emfb
    ){
        return new JpaTransactionManager(emfb.getObject());
    }
}
