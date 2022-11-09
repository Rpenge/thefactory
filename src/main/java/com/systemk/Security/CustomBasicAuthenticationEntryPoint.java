package com.systemk.Security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

		//WebSecurityConfig의 AuthenticationEntryPoint을 사용하여 commence를 실행하고 수정할 수 있다.
		//ServletException이부분을 제거하라고 자꾸 떠서 일딴 제거해본다.
		@Override
		public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		PrintWriter writer = response.getWriter();
		writer.println(authException.getMessage());
		}
		
		//java9이후부터 BeanFactory에 모든 Property가 설정되고 난 뒤 실행되는 메소드, 보통 custom초기화 로직이 필요하거나 주입받은 ProPerty 확인하는 용도로사용
		@Override
		public void afterPropertiesSet() {
			setRealmName("SYSTEMK_REALM");
			super.afterPropertiesSet();
		}
	
}