package br.gov.caixa.hackaton.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class LocalDatasourceConfiguration {

    @ConfigurationProperties("spring.datasource.local")
    @Bean
    public DataSourceProperties localDataSourceProperties(){
        DataSourceProperties dp = new DataSourceProperties();
        return dp;
    }

    @Bean
    public DataSource localDataSource(){
        DataSourceProperties dp = localDataSourceProperties();
        return dp.initializeDataSourceBuilder().build();
    }
}
