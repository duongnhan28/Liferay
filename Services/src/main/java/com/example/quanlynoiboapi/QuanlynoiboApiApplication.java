package com.example.quanlynoiboapi;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.osgi.io.OsgiBundleResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class QuanlynoiboApiApplication implements BundleActivator {

    ConfigurableApplicationContext appContext;
    @Autowired
    private Environment env;

    @Override
    public void start(BundleContext bundleContext) {
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        OsgiBundleResourcePatternResolver resourceResolver = new OsgiBundleResourcePatternResolver(bundleContext.getBundle());
        appContext = new SpringApplication(resourceResolver, QuanlynoiboApiApplication.class).run();
    }

    @Override
    public void stop(BundleContext bundleContext) {
        SpringApplication.exit(appContext, () -> 0);
    }

    public static void main(String[] args) {
        SpringApplication.run(QuanlynoiboApiApplication.class);
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("qlnd.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("qlnd.datasource.url"));
        dataSource.setUsername(env.getProperty("qlnd.datasource.username"));
        dataSource.setPassword(env.getProperty("qlnd.datasource.password"));
        return dataSource;
    }
}
