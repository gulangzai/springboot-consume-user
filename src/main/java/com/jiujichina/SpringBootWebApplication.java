package com.jiujichina;

import java.nio.charset.Charset;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.jolbox.bonecp.BoneCPDataSource;

//MyBatis 支持
@MapperScan("com.jiujichina.mybatis.dao")
@EnableDiscoveryClient
@EnableAutoConfiguration
@Configuration
@SpringBootApplication
@ComponentScan(basePackages="com.jiujichina")
@PropertySource(value={"classpath:jdbc.properties","classpath:redis.properties"})
//@ImportResource({"classpath:some-context.xml"})
public class SpringBootWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }
    
 
    
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
    	StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("ISO-8859-1"));
    	return converter;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
    
    
    @Value("${jdbc.url}")
    private String jdbcUrl ;
    
    @Bean(destroyMethod="close")
    public DataSource dataSource(){
    	BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
    	return boneCPDataSource;
    }
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
    	return new RestTemplate();
    }

}