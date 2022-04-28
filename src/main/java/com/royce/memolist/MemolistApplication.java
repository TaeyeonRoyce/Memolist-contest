package com.royce.memolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MemolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemolistApplication.class, args);
	}

}
