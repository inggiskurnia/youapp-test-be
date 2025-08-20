package com.youapp.YouApp;

import com.youapp.YouApp.infrastructure.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RsaKeyConfigProperties.class})
public class YouAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouAppApplication.class, args);
	}

}
