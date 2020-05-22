package com.example.Epam_Lab5;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EpamLab5Application {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(EpamLab5Application.class, args);
	}

}
