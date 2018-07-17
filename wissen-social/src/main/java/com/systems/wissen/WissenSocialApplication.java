package com.systems.wissen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WissenSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(WissenSocialApplication.class, args);
	}
}