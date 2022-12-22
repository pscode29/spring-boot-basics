/**
 * This simple application is intended to learn about following topics -
 * - Spring ApplicationContext
 * - Beans - switch to branch what-the-beans to know more
 * - Inversion of Control - switch to branch invert-the-control to know more
 * - Dependency Injection - switch to branches dependency-injection and better-injections to know more
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
class Color {
	private String color = "Blue";

	public String getColor() {
		return color;
	}
}

@Component
class TimeMachine {

//	DI using Field Injection - NOT a preferred way of DI
//	It is achieved through @Autowire annotation
//	@Autowired
	private final Clock clock; // A mandatory field

	private String color = "Black"; // An optional field

//	DI using Setter injection - preferred for optional fields injection and is more configurable
//	Setter Injection is achieved through @Autowire annotation
//	@Autowired
//	Not a good use of Setter Injection for mandatory field `clock`
//	public void setClock(Clock clock ) {
//		this.clock = clock;
//	}

//	A better use of Setter Injection for an optional field `color`
	@Autowired
	public void setColor(Color color) {
		this.color = color.getColor();
	}

//	DI using constructor injection - preferred for mandatory fields
//	Supports immutability means now you can make `clock` a `final` variable now.. thread safe etc..
//  @Autowired annotation is not required for Constructor Injection provided only one constructor is defined in this class
	public TimeMachine(Clock clock, Color color) {
		this.clock = clock;
	}

	public void whatTimeIsIt() {
		System.out.println("-----------------------------------------------------------------------------------------j");
		System.out.println("Welcome to the future 💡 .... Current time is... ");
		System.out.println("-----------------------------------------------------------------------------------------j");
		System.out.println(clock.getLocalDateTime().plusYears(1000));
		System.out.println("My color is " + color);
	}
}