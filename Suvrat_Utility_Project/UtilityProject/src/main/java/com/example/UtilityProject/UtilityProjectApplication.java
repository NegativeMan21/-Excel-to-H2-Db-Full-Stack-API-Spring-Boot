package com.example.UtilityProject;

import com.example.UtilityProject.Mail.MailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;

@EnableScheduling
@SpringBootApplication
@ComponentScan
public class UtilityProjectApplication {
	@Autowired
	private static MailService mailService;

	public static void main(String[] args) {
		createLogDirectory();
		LocalTime currentTime = LocalTime.now();
		LocalTime startTime = LocalTime.of(0, 0);
		LocalTime endTime = LocalTime.of(23, 59);
		if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
			SpringApplication.run(UtilityProjectApplication.class, args);
			System.out.println("Scheduled task is running.");
		} else {
			System.out.println("Scheduled task could not be run. Please run the task between Monday-Friday.");
		}

		}
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		executor.setThreadNamePrefix("TaskExecutor");
		executor.initialize();
		return executor;
	}
	private static void createLogDirectory() {
		File logsDir = new File("logs");
		if (!logsDir.exists()) {
			boolean created = logsDir.mkdir();
			if (!created) {
			}
		}
	}
}


