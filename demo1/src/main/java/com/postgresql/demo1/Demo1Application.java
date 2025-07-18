package com.postgresql.demo1;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Demo1Application {
//	this is used to print something without using main method
//	public final class PrintMessageWithoutMainMethod {
//    static {
//        System.out.println("Hello World!!");
//        System.exit(0);
//    }
//}

	private static final Logger logger = LogManager.getLogger(Demo1Application.class);

	@Value("${spring.profiles.active:default}")
	private String activeProfile;


	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		logger.info("Application started");
		logger.info(" bean created ");
		System.out.println("check for changes");
		logger.info("its a feature branch to test");
		logger.info("abcdef");

	}

	@PostConstruct
	public void logActiveProfile() {
		logger.info(" Application is running in '{}' envirogfgnment", activeProfile.toUpperCase());
	}
}




