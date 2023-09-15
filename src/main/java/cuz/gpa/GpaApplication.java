package cuz.gpa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("cuz.gpa.mapper")
public class GpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(GpaApplication.class, args);
	}

}
