package com.reactivox.springrxjava;

import com.reactivox.springrxjava.service.PerroServiceImpl;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringrxjavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrxjavaApplication.class, args);
	}


}
