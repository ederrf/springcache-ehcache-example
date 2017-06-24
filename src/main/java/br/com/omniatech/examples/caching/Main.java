package br.com.omniatech.examples.caching;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.omniatech.examples.caching.business.ExampleBusinessLogic;
import br.com.omniatech.examples.caching.configuration.SpringConfiguration;

public class Main {
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {

			ExampleBusinessLogic exampleBusinessLogic = context.getBean(ExampleBusinessLogic.class);

			for (int i = 0; i < 10; i++) {
				String now = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());
				String ret = exampleBusinessLogic.expensiveStringFunction("TheUniqueKey");
				System.out.println(String.format("Function called at %1$s and returned '%2$s'", now, ret));

				//Sleeping to test the TTL of the cache 
				try {
					Thread.sleep(500);
				} catch (InterruptedException ie) {}
			}
		} 
	}
}
