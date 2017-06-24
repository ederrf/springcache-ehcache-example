package br.com.omniatech.examples.caching.business;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ExampleBusinessLogic {

	@Cacheable("ExampleCache")
	public String expensiveStringFunction(String string) {
		//Sleeping to simulate the expensiveness of the function
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie) {}
		
		String now = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());
		System.out.println(String.format(">>> CACHE MISS for key '%1$s' at %2$s", string, now));
		
		return String.format("%1$s was cached at %2$s", string , now);
	}
}
