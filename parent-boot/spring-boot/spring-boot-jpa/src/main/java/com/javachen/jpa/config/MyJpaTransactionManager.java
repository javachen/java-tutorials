package com.javachen.jpa.config;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * @author june
 * @createTime 2019-07-12 17:13
 * @see
 * @since
 */
public class MyJpaTransactionManager extends JpaTransactionManager {
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        if(definition.isReadOnly()){
            DataSourceContextHolder.setDataSource(DataSourceContextHolder.SLAVE_DATASOURCE_KEY);
        }else{
            DataSourceContextHolder.setDataSource(DataSourceContextHolder.MASTER_DATASOURCE_KEY);
        }
        super.doBegin(transaction, definition);
    }
    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        super.doCommit(status);
    }
}