package br.gov.caixa.hackaton.config.db;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LocalDatasourceConfiguration {

    @ConfigurationProperties("spring.datasource.local")
    @Bean
    public DataSourceProperties localDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource localDataSource(){
        DataSourceProperties dp = localDataSourceProperties();
        return dp.initializeDataSourceBuilder().build();
    }
}
