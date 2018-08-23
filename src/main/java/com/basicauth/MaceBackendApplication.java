package com.basicauth;

import com.basicauth.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.*;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({ JHipsterProperties.class})
public class MaceBackendApplication {

    private static final Logger logger = LoggerFactory.getLogger(MaceBackendApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(MaceBackendApplication.class);
        Environment env = app.run(args).getEnvironment();
        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                        env.getProperty("spring.application.name"),
                        env.getProperty("server.port"),
                        InetAddress.getLocalHost().getHostAddress(),
                        env.getProperty("server.port"));

//        String configServerStatus = env.getProperty("configserver.status");
//        logger.info("\n----------------------------------------------------------\n\t" +
//                        "Config Server: \t{}\n----------------------------------------------------------",
//                configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
    }

    {

//        logger.info("Starting MaceBackendApplication......");
//        SpringApplication.run(MaceBackendApplication.class, args);
    }


}