package br.gov.caixa.hackaton.config.db;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class RemoteDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.remote")
    public DataSourceProperties remoteDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource remoteDataSource(){
        return remoteDataSourceProperties().initializeDataSourceBuilder().build();
    }

}
