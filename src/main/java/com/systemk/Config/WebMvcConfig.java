package com.systemk.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public  class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoggerInterceptor())
//                .addPathPatterns("/api/*")
//                .excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
//    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      System.out.println("registry");
        registry.addResourceHandler("/resources/APP/**")
                .addResourceLocations("file:///D:/theFactor2RFID/APP/");
    }


}
