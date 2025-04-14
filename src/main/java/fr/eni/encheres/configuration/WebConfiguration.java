package fr.eni.encheres.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	/**
	 * file.upload-dir
	 * on va prendre la configuration file.upload-dir de notre fichier applications.properties
	 * pour le dossier supplémentaire de ressources
	 * (à changer : valeur initiale : file.uploadDir=C:/Users/cmace/Documents/imagesArticles/)
	 */
	@Value("${file.upload-dir}")
	private String uploadDir;

	/**
	 * On rajoute un dossier qui va être scanné par l'aaplication pour aller chercher des fichiers statics
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imagesArticles/**")
				.addResourceLocations("file:" + uploadDir)
				.setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
	}
}