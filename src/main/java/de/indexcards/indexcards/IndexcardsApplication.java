package de.indexcards.indexcards;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@SpringBootApplication
public class IndexcardsApplication {

	public static void main(String[] args) {

		SpringApplication.run(IndexcardsApplication.class, args);
	}

	@Configuration
	class H2Configuration {
		@Bean(initMethod = "start", destroyMethod = "stop")
		public Server inMemoryH2DatabaseaServer() throws SQLException {
			return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
		}
	}
}
