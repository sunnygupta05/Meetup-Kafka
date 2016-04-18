package com.meetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AccountConsumer1Application implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	AccountConsumer1 con;

	public static void main(String[] args) {
		SpringApplication.run(AccountConsumer1Application.class, args);
	}


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        con.consume();
    }
}
