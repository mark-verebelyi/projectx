package zzz.projectx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zzz.projectx.core.cqrs.command.EnableCommandBus;
import zzz.projectx.core.cqrs.query.EnableQueryBus;
import zzz.projectx.core.eda.event.EnableEventBus;

@SpringBootApplication
@EnableCommandBus(packageToScan = "zzz.projectx.**.domain.**.command.handler.**")
@EnableQueryBus(packageToScan = "zzz.projectx.**.domain.**.query.handler.**")
@EnableEventBus(packageToScan = "zzz.projectx.**.domain.**.event.listener.**")
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
