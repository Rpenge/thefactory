package com.systemk.ams;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.systemk.ams.SystemkAmsApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SystemkAmsApplication.class})
public class SystemkNoblandApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	public void contextLoads() throws Exception {
		
		System.out.println(bCryptPasswordEncoder.encode("admin"));
	}

}
