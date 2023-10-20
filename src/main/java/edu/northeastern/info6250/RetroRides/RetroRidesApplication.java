package edu.northeastern.info6250.RetroRides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan({"edu.northeastern.info6250.RetroRides.controllers","edu.northeastern.info6250.RetroRides.dao","edu.northeastern.info6250.RetroRides.exception",
	"edu.northeastern.info6250.RetroRides.validators", "edu.northeastern.info6250.RetroRides.pojo", "edu.northeastern.info6250.RetroRides.configure"})
public class RetroRidesApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(RetroRidesApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
	}

}
