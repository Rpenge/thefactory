package com.systemk.thefactor2.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public  class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoggerInterceptor())
//                .addPathPatterns("/api/*")
//                .excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
//    }

  
  //PDA 앱 불러오는 역할 :리소스 경로값을 넣어줘서 읽어온다.api는 PDA앱 관련 으로 추측
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/APP/**")
                .addResourceLocations("file:///D:/theFactor2RFID/APP/");
    }


}
