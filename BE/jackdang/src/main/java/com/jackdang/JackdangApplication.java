package com.jackdang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class JackdangApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(JackdangApplication.class, args);
	}

}