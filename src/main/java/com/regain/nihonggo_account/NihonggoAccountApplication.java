package com.regain.nihonggo_account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class NihonggoAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(NihonggoAccountApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder() { //bean mã hóa pass
		return new BCryptPasswordEncoder();
	}


}
