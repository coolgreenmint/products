package com.mint.tombs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TombsApplication {

	public static void main(String[] args) {
		System.out.println("-----------------------------");
		System.out.println("tombs start runing");
		SpringApplication.run(TombsApplication.class, args);
		System.out.println("tombs start up");
		System.out.println("-----------------------------");
	}

}
