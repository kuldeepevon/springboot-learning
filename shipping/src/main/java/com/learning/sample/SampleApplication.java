package com.learning.sample;

import com.learning.sample.util.StorageProperties;
import com.learning.sample.util.StorageService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

//	@Bean
//	public NewTopic topic() {
//		return TopicBuilder.name("topic1")
//				.partitions(10)
//				.replicas(1)
//				.build();
//	}
//
//	@Bean
//	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
//		return args -> {
//			template.send("topic1", "test");
//		};
//	}
//	@KafkaListener(topics = "test-topic")
//	public void listen(String in) {
//		System.out.println(in);
//	}
}
