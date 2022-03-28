package les.spring.guessTheNumberGame.event;


import les.spring.guessTheNumberGame.logAnnotations.WorkTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Slf4j
@Component
public class EventListener implements ApplicationListener<Event> {

    private final int lowerBound;
    private final int upperBound;
    private final ResourceBundleMessageSource resource;
    private int randomNumber;

    @Autowired
    public EventListener(@Value("${lower.bound}") int lowerBound, @Value("${upper.bound}") int upperBound,
                         ResourceBundleMessageSource resource) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.resource = resource;

        randomNumber = new Random().nextInt(upperBound) + lowerBound;
    }

    @WorkTime
    @Override
    public void onApplicationEvent(Event event) {
        Locale locale = event.getLocale();
        if (event.getNumber() < randomNumber) {
            log.info(resource.getMessage("bigger.message", null, locale));
        } else if (event.getNumber() > randomNumber) {
            log.info(resource.getMessage("less.message", null, locale));
        } else {
            log.info(resource.getMessage("win.message", null, locale) + " " + randomNumber);
            randomNumber = new Random().nextInt(upperBound) + lowerBound;
            log.info(resource.getMessage("hello.message", null, locale));
        }
    }
}
