package com.basicauth.config;

import com.basicauth.mapper.MemMapper;
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
public class MembershipConfig {


    private static Logger logger = LoggerFactory.getLogger(MembershipConfig.class);


    @Bean
    @ConfigurationProperties(prefix = "mims.jdbc")
    public DataSource dataSourceMem() {
        logger.info("dataSourceMem");
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryMem() throws Exception {

        logger.debug("> sqlSessionFactory");
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSourceMem());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        Resource mapperResource = new ClassPathResource("mapper/MemMapper.xml");
        sqlSessionFactory.setMapperLocations(new Resource[]{mapperResource});

        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManagerMem() {

        logger.debug("> transactionManager");

        return new DataSourceTransactionManager(dataSourceMem());
    }

    @Bean
    MemMapper memMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactoryMem());
        return sessionTemplate.getMapper(MemMapper.class);
    }


}
