package ru.ifmo.gerasimov.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.ifmo.gerasimov.service.ValidationInputService;
import ru.ifmo.gerasimov.service.VkNewsfeedService;

/**
 * @author Michael Gerasimov
 */
@Component
public class Runner implements CommandLineRunner{
    private final ValidationInputService validationInputService;
    private final VkNewsfeedService vkNewsfeedService;

    public Runner(ValidationInputService validationInputService, VkNewsfeedService vkNewsfeedService) {
        this.validationInputService = validationInputService;
        this.vkNewsfeedService = vkNewsfeedService;
    }

    @Override
    public void run(String... args) {
        if (!validationInputService.validationInput(args)) {
            System.out.println("Invalid input data!");
            return;
        }

        String query = args[0];
        int hourNumbers = Integer.parseInt(args[1]);
        System.out.println(vkNewsfeedService.findFrequencyHashtag(query, hourNumbers));
    }
}
