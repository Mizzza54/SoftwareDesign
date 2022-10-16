package ru.ifmo.gerasimov.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.ifmo.gerasimov.configuration.VkNewsfeedConfiguration;

/**
 * @author Michael Gerasimov
 */
@SpringBootApplication
@Import(VkNewsfeedConfiguration.class)
public class VkNewsfeedApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(VkNewsfeedApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
