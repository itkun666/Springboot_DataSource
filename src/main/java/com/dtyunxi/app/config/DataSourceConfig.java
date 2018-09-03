package com.dtyunxi.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
    private String url;
    private String username;
    private String password;
    
    @Bean
    public SqlSessionFactory getSessionFactory(DataSource ds) throws Exception{
    	   PageHelper pageHelper = new PageHelper();
           Properties p = new Properties();
           p.setProperty("offsetAsPageNum", "true");
           p.setProperty("rowBoundsWithCount", "true");
           p.setProperty("reasonable", "false");
           p.setProperty("returnPageInfo", "check");
           p.setProperty("params", "count=countSql");
           pageHelper.setProperties(p);
           SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
           sfb.setPlugins(new Interceptor[]{pageHelper});
           sfb.setDataSource(ds);
           // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
           sfb.setTypeAliasesPackage("com.dtyunxi.app.pojo");
           sfb.setMapperLocations(new PathMatchingResourcePatternResolver()
                   .getResources("classpath:mappers/mysql/*Mapper.xml"));
           return sfb.getObject();
    	
    }

    @Bean
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);// 用户名
        dataSource.setPassword(password);// 密码
        return dataSource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
