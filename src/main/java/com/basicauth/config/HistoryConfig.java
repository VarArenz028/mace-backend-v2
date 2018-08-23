package com.basicauth.config;

import com.basicauth.mapper.HistoryMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * Created by igan_long on 10/6/2016.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="com.basicauth.mapper")
public class HistoryConfig {

    private static Logger logger = LoggerFactory.getLogger(HistoryConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "history.jdbc")
    public DataSource dataSourceHistory() {
        logger.info("dataSourceHistory");
//        BasicDataSource ds = new BasicDataSource();
//
//        ds.setDriverClassName("net.sf.log4jdbc.DriverSpy");
//        ds.setUsername("root");
//        ds.setPassword("1234");
//        ds.setUrl("jdbc:log4jdbc:mysql://testdb01:3306/test?characterEncoding=UTF-8&amp;useServerPrepStmts=true&amp;cachePrepStmts=true&amp;connectTimeout=3000&amp;socketTimeout=7000");
//        ds.setMaxActive(3);
//        ds.setValidationQuery("/* ping */ select 1");
//        ds.setTestOnBorrow(false);
//        ds.setTestOnReturn(false);
//        ds.setTestWhileIdle(true);
//        ds.setTimeBetweenEvictionRunsMillis(60000);
//        ds.setNumTestsPerEvictionRun(5);
//        ds.setMinEvictableIdleTimeMillis(-1);
//
//        return ds;

        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryHistory() throws Exception {
        logger.debug("> sqlSessionFactory AAAAAAAAAAAAAA");

//		return new SqlSessionFactoryBuilder().build(
//				this.getClass().getResourceAsStream("classpath:mybatis-config.xml"));

        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSourceHistory());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        Resource mapperResource00 = new ClassPathResource("mapper/HistoryMapper.xml");
        Resource[] mapperLocations = new Resource[]{mapperResource00};
        sqlSessionFactory.setMapperLocations(mapperLocations);
//        sqlSessionFactory.setTypeHandlersPackage("org.horiga.study.mybatis.typehandler");

        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManagerHistory() {
        logger.debug("> transactionManager");
        return new DataSourceTransactionManager(dataSourceHistory());
    }

    @Bean
    HistoryMapper historyMapper() throws Exception {
        logger.info("historyMapper");
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactoryHistory());
        return sessionTemplate.getMapper(HistoryMapper.class);
    }


}
