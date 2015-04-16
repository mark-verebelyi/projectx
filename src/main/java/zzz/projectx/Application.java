package zzz.projectx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zzz.projectx.core.cqrs.EnableCommandBus;

@SpringBootApplication
@EnableCommandBus(packageToScan = "zzz.projectx.**.domain.**.command.handler.**")
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
