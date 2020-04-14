package com.javachen.jpa.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author june
 * @createTime 2019-07-12 17:11
 * @see
 * @since
 */
@Configuration
@EnableJpaRepositories
@EnableConfigurationProperties(JpaProperties.class)
public class DataSourceConfiguration {
    @Autowired
    private JpaProperties jpaProperties;

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Value("${spring.datasource.druid.master.url}")
    private String masterUrl;

    @Value("${spring.datasource.druid.slave.url}")
    private String slaveUrl;

    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        DynamicDataSourceRouter proxy = new DynamicDataSourceRouter();
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceContextHolder.MASTER_DATASOURCE_KEY, masterDataSource());
        targetDataSources.put(DataSourceContextHolder.SLAVE_DATASOURCE_KEY, slaveDataSource());

        proxy.setDefaultTargetDataSource(masterDataSource());
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    @Primary
    @Bean
    public DataSource masterDataSource() {
        DruidDataSource druidDataSource= (DruidDataSource)DataSourceBuilder.create().type(dataSourceType).build();
        druidDataSource.setUrl(masterUrl);
        return druidDataSource;
    }

    @Bean
    public DataSource slaveDataSource() {
        DruidDataSource druidDataSource= (DruidDataSource)DataSourceBuilder.create().type(dataSourceType).build();
        druidDataSource.setUrl(slaveUrl);
        return druidDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        Map<String, String> properties = jpaProperties.getProperties();
        return builder.dataSource(routingDataSource())//关键：注入routingDataSource
                .properties(properties)
                .build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.entityManagerFactoryBean(builder).getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        MyJpaTransactionManager transactionManager = new MyJpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(builder));
        return transactionManager;
    }
}
