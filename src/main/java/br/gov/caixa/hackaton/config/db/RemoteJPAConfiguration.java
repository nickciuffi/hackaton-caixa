package br.gov.caixa.hackaton.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*
    Configuração de entity manager e transaction manager para o banco de dados remoto sql server
 */

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
            @Qualifier("remoteDataSource") DataSource dataSource,
            Environment env
    ){
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", env.getProperty("spring.datasource.remote.ddl-auto", "none"));
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
        assert emfb.getObject() != null;
        return new JpaTransactionManager(emfb.getObject());
    }
}
