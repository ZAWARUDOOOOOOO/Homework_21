package les.spring.guessTheNumberGame;

import les.spring.guessTheNumberGame.event.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class GuessTheNumberGameApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GuessTheNumberGameApplication.class, args);
        ResourceBundleMessageSource resource = context.getBean(ResourceBundleMessageSource.class);

        Locale locale = Locale.US;
        log.info(resource.getMessage("hello.message", null, locale));

        EventPublisher publisher = context.getBean(EventPublisher.class);
        while (true) {
            log.info(resource.getMessage("try.message", null, locale));

            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();

            publisher.publishEvent(number, locale);
        }
    }
}
