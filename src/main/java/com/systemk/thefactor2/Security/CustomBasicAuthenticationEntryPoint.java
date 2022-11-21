package com.systemk.thefactor2.Security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

  //요청과 응답 그리고 권한 예외설정
  //public static final int SC_UNAUTHORIZED = 401;  
  //그동안 이것에서 401번이 떳던것 
	@Override
	public void commence(final HttpServletRequest request, 
	                             final HttpServletResponse response,
	                             final AuthenticationException authException
	                             ) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		//401을 출력해주는 부분
		PrintWriter writer = response.getWriter();
		writer.println(authException.getMessage());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("SYSTEMK_REALM");
		super.afterPropertiesSet();
	}
}
