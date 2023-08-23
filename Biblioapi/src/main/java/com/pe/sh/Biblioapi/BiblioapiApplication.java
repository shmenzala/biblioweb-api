package com.pe.sh.Biblioapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BiblioapiApplication {

        @Bean
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }
    
	public static void main(String[] args) {
		SpringApplication.run(BiblioapiApplication.class, args);
	}

}
