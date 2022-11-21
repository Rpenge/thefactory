package com.systemk.thefactor2.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  //WebSecurityConfigurerAdapter: security 를 custom하기 위해 추가하던것 5.1.0이후로는 사용불가 
  //SecurityFilterChain를 Bean으로 등록으로 대체하라고함 (나중에 트라이해볼것)configure을 사용할수 없다.
	@Autowired
	@Qualifier("customUserDetailService")
	private UserDetailsService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      //char를 인코딩 하기위한 필터
      CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        //http security 에서 권한설정
    	http
    	    //외부 frame에 페이지를 추가하기 위해서  url 에서  X-Frame-Options 헤더를 제거하는 부분
    		.headers().frameOptions().disable()
    		
    		//위에서 쓴 UTF-8과같은 만들어진 1. 필터를 적용
    	.and()
    		.addFilterBefore(filter,CsrfFilter.class)
    		//2. 권한 요청
    		.authorizeRequests()
    		//3. 허용 범위 (아래 속한 리소스 들을 모두 허용 한다)
    		.antMatchers("/resources/css/**", "/resources/js/**", "/resources/img/**", "/resources/**", "/member/login").permitAll()
    		
    		// realm name을 설정하고  Response 401이 떨어지면 해당 로직을 타서 commence메소드로 실행
    	.and()
    		.httpBasic().realmName("SYSTEMK_REALM").authenticationEntryPoint(getBasicAuthEntryPoint())
		.and()
			.csrf().disable();
    }
    //401 에러가 떳을때의 오류 처리법
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
    //custom 에러 메세지 
    @Override
    protected UserDetailsService userDetailsService() {
        return customUserDetailService;
    }
    //Security 인증 지원
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//		auth.userDetailsService(customUserDetailService);
	}
    //패스워드 인코딩
    @Bean
	public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
	}
    //헤더 webtoken사용
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}
