package com.basicauth.config;

import com.basicauth.mapper.mace.DiagProcMapper;
import com.basicauth.mapper.mace.DocHospMapper;
import com.basicauth.mapper.mace.MaceErMapper;
import com.basicauth.mapper.mace.MaceMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * Created by igan_long on 10/6/2016.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.basicauth.mapper.mace")
public class MaceConfig {


    private static Logger logger = LoggerFactory.getLogger(MaceConfig.class);


    @Primary
    @Bean
    @ConfigurationProperties(prefix = "mace.jdbc")
    public DataSource dataSource() {
        logger.info("dataSource");
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        logger.debug("> sqlSessionFactory");

        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        Resource mapperResource = new ClassPathResource("mapper/MaceMapper.xml");
        Resource mapperResource2 = new ClassPathResource("mapper/DiagProcMapper.xml");
        Resource mapperResource3 = new ClassPathResource("mapper/DocHospMapper.xml");
        Resource mapperResource4 = new ClassPathResource("mapper/MaceErMapper.xml");
        sqlSessionFactory.setMapperLocations(new Resource[]{mapperResource, mapperResource2, mapperResource3, mapperResource4});

        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        logger.debug("> transactionManager");
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public MaceMapper maceMapper() throws Exception {
        logger.info("maceMapper");
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sessionTemplate.getMapper(MaceMapper.class);
    }

    @Bean
    public MaceErMapper maceErMapper() throws Exception {
        logger.info("maceErMapper");
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sessionTemplate.getMapper(MaceErMapper.class);
    }

    @Bean
    public DiagProcMapper diagProcMapper() throws Exception {
        logger.info("diagProcMapper");
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sessionTemplate.getMapper(DiagProcMapper.class);
    }

    @Bean
    public DocHospMapper docHospMapper() throws Exception {
        logger.info("diagProcMapper");
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sessionTemplate.getMapper(DocHospMapper.class);
    }

    @Bean
    public MessageSource messageSource(){
        return new DatabaseMessageSource();
    }
}
