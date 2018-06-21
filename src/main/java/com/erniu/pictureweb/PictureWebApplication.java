package com.erniu.pictureweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.erniu.pictureweb.mapper")
public class PictureWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PictureWebApplication.class, args);
	}
}
