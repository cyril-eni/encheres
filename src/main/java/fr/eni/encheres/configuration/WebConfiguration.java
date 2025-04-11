package fr.eni.encheres.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imagesArticles/**")
				.addResourceLocations("file:" + uploadDir)
				.setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
	}
}