package com.cos.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

//servlet으로

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


	private final String signImagesPath;
	private String uploadPath;

	@Autowired
	public WebMvcConfig(PathYamlRead pathYamlRead){
		this.signImagesPath = pathYamlRead.getSign_path();
		this.uploadPath = pathYamlRead.getUpload_path();
	}


	@Override
	public void configureViewResolvers(ViewResolverRegistry registry)
	{
		MustacheViewResolver resolver = new MustacheViewResolver();
		resolver.setCharset("UTF-8");
		resolver.setContentType("text/html; charset=UTF-8");//던지는 데이터는 html파일 UTF-8
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");//.html파일 만들어도 mustache가 인식

		registry.viewResolver(resolver);//mustache 재설정
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry
				.addResourceHandler(uploadPath)
				.addResourceLocations("file:///" + signImagesPath);
	}

}
