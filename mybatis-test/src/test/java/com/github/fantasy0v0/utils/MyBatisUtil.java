package com.github.fantasy0v0.utils;

import com.github.fantasy0v0.handler.MapToStringHandler;
import com.github.fantasy0v0.mappers.TestMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.h2.jdbcx.JdbcDataSource;

import java.util.Map;

public class MyBatisUtil {

  public static SqlSession openSession() {
    JdbcDataSource dataSource = new JdbcDataSource();
    dataSource.setURL("jdbc:h2:mem:;INIT=runscript from 'classpath:create.sql'\\;runscript from 'classpath:init.sql'");
    dataSource.setUser("sa");

    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);

    TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    MapToStringHandler handler = new MapToStringHandler();
    typeHandlerRegistry.register(Map.class, JdbcType.VARCHAR, handler);

    configuration.addMapper(TestMapper.class);
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    return sessionFactory.openSession();
  }

}

