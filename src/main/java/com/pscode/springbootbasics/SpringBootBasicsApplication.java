// Code to explain Beans
package com.pscode.springbootbasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootBasicsApplication {


	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringBootBasicsApplication.class);
		String[] beans = applicationContext.getBeanDefinitionNames();

		for (String bean: beans) {
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println(bean);
			System.out.println("----------------------------------------------------------------------------------------");

		}

	}
}
