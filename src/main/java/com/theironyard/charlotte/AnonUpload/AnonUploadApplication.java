package com.theironyard.charlotte.AnonUpload;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class AnonUploadApplication {

	public static void main(String[] args) throws SQLException {
//		Server.createWebServer().start();
//		new comment
		SpringApplication.run(AnonUploadApplication.class, args);
	}
}
