package com.thai27.trangtintuc_v4_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class TrangtintucV4BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrangtintucV4BeApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder passencode() {;
		return new BCryptPasswordEncoder();
	}

}
