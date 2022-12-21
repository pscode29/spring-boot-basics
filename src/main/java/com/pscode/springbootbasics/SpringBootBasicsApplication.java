/**
 * DI: Dependency Injection
 * Following code shows how spring injects the `clock` dependency in your `TimeMachine` class at runtime.
 * There are 3 ways of DIs. Constructor, Setter and Field injection. This is an example of Field injection which
 * is the least recommended way of DI.
 *
 * Benefits: same as IoC as DI is a specific type IoC:
 *
 * How is spring boot doing DI?
 * - Using @Autowire annotation
 *
 */
package com.pscode.springbootbasics;

import org.springframework.beans.factory.annotation.Autowired;
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

		TimeMachine timeMachine = applicationContext.getBean("timeMachine", TimeMachine.class);
		timeMachine.whatTimeIsIt();

	}
}

@Component
class Clock {
	private LocalDateTime localDateTime = LocalDateTime.now();

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
}

@Component
class TimeMachine {

	// Dependency injection using Field Injection. DON'T DO THIS. Prefer Constructor Injection instead.
	@Autowired
	private Clock clock;

	public void whatTimeIsIt() {
		System.out.println("-----------------------------------------------------------------------------------------j");
		System.out.println("Welcome to the future 💡 .... Current time is... ");
		System.out.println("-----------------------------------------------------------------------------------------j");
		System.out.println(clock.getLocalDateTime().plusYears(1000));
	}
}