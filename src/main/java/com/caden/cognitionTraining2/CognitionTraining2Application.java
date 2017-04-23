package com.caden.cognitionTraining2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.caden.cognitionTraining2.dip.DIPServiceProperties;
import com.caden.cognitionTraining2.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ StorageProperties.class, DIPServiceProperties.class })
public class CognitionTraining2Application {

	public static void main(String[] args) {
		SpringApplication.run(CognitionTraining2Application.class, args);
	}
}
