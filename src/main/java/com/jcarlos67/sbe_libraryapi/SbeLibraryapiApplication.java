package com.jcarlos67.sbe_libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SbeLibraryapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbeLibraryapiApplication.class, args);
	}

}
