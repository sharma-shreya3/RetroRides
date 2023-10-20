package edu.northeastern.info6250.RetroRides.configure;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class StaticResourceConfiguration implements WebMvcConfigurer  {
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/external-images/**")
                .addResourceLocations("file:///C:/Shreya Sharma/NEU coursework/Sem-2/web tools/Retro Rides/RetroRides/RetroRides/src/main/webapp/images/");
    }

}
