/**
 * IoC: Inversion of Control
 * Following code shows how you let spring manage your objects as a bean using @Component annotation. This
 * way you have inverted the control of your object creation and management etc. to spring.
 *
 * Benefits:
 * - Allows decoupling - Domain Driven Design stuff
 * - DRY: You don't have to keep instantiating your object every time it is needed
 * - Share a resource throughout the app and don't worry about lifecycle management of objects
 * - Helps in testing by letting you mock objects easily, we will see that later.
 *
 * NOTE - Dependency Injection is a way of achieving IoC.
 */
package com.pscode.springbootbasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringBootBasicsApplication {


	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringBootBasicsApplication.class);
		String[] beans = applicationContext.getBeanDefinitionNames();

		for (String bean: beans) {
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println(bean);
			System.out.println("-------------------------------------------------------------------------------------");

		}

//		Clock clock = new Clock();
//		LocalDateTime localDateTime = clock.getLocalDateTime();
//		System.out.println("localDateTime = " + localDateTime);

		Clock clock = applicationContext.getBean("clock", Clock.class);
		LocalDateTime localDateTime = clock.getLocalDateTime();
		System.out.println("localDateTime = " + localDateTime);

	}
}

@Component
class Clock {
	private LocalDateTime localDateTime = LocalDateTime.now();

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
}