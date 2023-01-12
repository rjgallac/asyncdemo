package uk.co.sheffieldwebprogrammer.asyncdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication
public class AsyncdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncdemoApplication.class, args);
	}


	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(12);
//		executor.setMaxPoolSize(30);
//		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("async-demo-");
		executor.initialize();
		return executor;
	}
}
