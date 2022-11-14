package com.systemk;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.systemk.Config.LogFilter;
import com.systemk.Mapper.TfLogMapper;

@ServletComponentScan
@SpringBootApplication
public class SystemkAmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemkAmsApplication.class, args);
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Autowired
    private TfLogMapper tfLogMapper;

    @Bean
    public FilterRegistrationBean myFilterBean() {
        final FilterRegistrationBean filterBean = new FilterRegistrationBean(new LogFilter(tfLogMapper));
        filterBean.addUrlPatterns("/api/*");
        return filterBean;
    }

}
