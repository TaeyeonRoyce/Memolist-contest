package com.royce.memolist;

import javax.persistence.EntityListeners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@SpringBootApplication
public class MemolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemolistApplication.class, args);
	}

}
