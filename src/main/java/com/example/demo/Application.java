package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.bean.MyBean;
import com.example.demo.bean.MyBeanWithDependency;
import com.example.demo.bean.MyBeanWithProperties;
import com.example.demo.component.ComponentDependency;
import com.example.demo.pojo.UserPojo;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private final Log LOGGER = LogFactory.getLog(Application.class);
	
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	
	public Application(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) {
		
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println( myBeanWithProperties.function() );
		System.out.println(userPojo.getEmail() + "  " + userPojo.getPassword() + " edad " + userPojo.getAge());
		try {
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor es " + value);
		}catch(Exception e) {		
			LOGGER.error("Es un error de operacion " + e.getMessage());
		}
	}

}
