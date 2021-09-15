package com.cos.security1;

import com.cos.security1.config.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableConfigurationProperties(
        {FileUploadProperties.class}
)

@SpringBootApplication
public class Security1Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Security1Application.class, args);
		System.out.print("test1 \n"+ org.springframework.core.SpringVersion.getVersion());

	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{ return builder.sources(Security1Application.class);
	}
}
