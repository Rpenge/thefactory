//package com.systemk.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
////WebSecurityConfigurerAdapter을 상속받아서 사용(인터페이스 아님)
////WebSecurityConfigurerAdapter스프링 시큐리티의 웹 보안 기능 초기화 및 설정 ->HttpSecurty로 인증API와 인가 API를 제공
////세부적인 설정이 가능함 ->getHttp()메서드가 실행할때, HTTPSecurity 클래스를 생성하게 된다 .
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//	@Autowired
//	@Qualifier("customUserDetailService")
//	private UserDetailsService customUserDetailService;
//    
//	
//	//header설정과 경로에 대한 접근설정을 할 수 있다.
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	CharacterEncodingFilter filter = new CharacterEncodingFilter();
//    	//setEncoding은 요청데이터 setForceEncoding는 응답데이터 true하면 요청데이터 인코딩으로 강제 설정 
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//    	http
//    		.headers().frameOptions().disable() //console화면을 사용하기 위해 옵션을 disable
//    	.and()
//    		.addFilterBefore(filter,CsrfFilter.class) //filter중 CsrfFilter는 post나 put과 같이 리소스를 변경하는 요청의 경우 내가 내보냈던 리소스에서 올라온 요청인지 확인한다.
//    		.authorizeRequests()						//URL별 권한 접근제어 관리 옵션 시작점
//    		.antMatchers("/resources/css/**", "/resources/js/**", "/resources/img/**","/resources/**", "/member/**").permitAll() 
//    				//권한 관리 대상 지정 permitAll() 모든 권한 공개
////    	.and()
////    		.httpBasic().realmName("SYSTEMK_REALM").authenticationEntryPoint(getBasicAuthEntryPoint())
////    			.httpBasic()메서드 호출로 http인증 활성화 된다. 원하는대로 범위를 명시하기 위해서 realmName()을 호출하는 것
////    		auth는 인증절차를 거쳐 통과하게 되는데 실패하거나 인증헤더를 안보낼 경우filterChain에 걸려  Response이 401을 띄운다. 
////    		그때 처리하는게 .authenticationEntryPoint이다. 에러  401일때 해당 로직은 commence라는 메소드를 실행
////    		commence보내면 entrypoint로 로그인 성공시 Redirect to login, 실패시 setHeader+sendError넣어서 출력
//    	.and()
//			.csrf().disable();
//    	System.out.println("config 요청");
//    }
//    
//    //com.systemk.security에서 불러온다. getBasicAuthEntryPoint사용하기 위하여 Bean 으로 정의함.
////    @Bean
////    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
////        return new CustomBasicAuthenticationEntryPoint();
////    }
//    
//    //UserDetail이란 사용자 정보를 담는 인터페이스 이다. 
////    @Override
////    protected UserDetailsService userDetailsService() {
////        return customUserDetailService;
////    }
//    
//    //configure는 권한 관리 빌드를 정의하고 유저에게 권한을 주기 위한 인터페이스다.
////    @Override
////   	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////   		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
////   	}
////    
////    //패스워드를 암호화 해주는 인터페이스 이다.
////    @Bean
////   	public PasswordEncoder passwordEncoder() {
////       	return new BCryptPasswordEncoder();
////   	}
////
////    //보안을 위한 CSRF Token정보를 Header정보에 포함하여 서버요청을 시도하는것이 보안상 안전하다.
////   	private CsrfTokenRepository csrfTokenRepository() {
////   		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
////   		repository.setHeaderName("X-XSRF-TOKEN");
////   		return repository;
////   	}
//}