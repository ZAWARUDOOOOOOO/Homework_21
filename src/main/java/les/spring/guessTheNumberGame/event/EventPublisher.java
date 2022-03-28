package les.spring.guessTheNumberGame.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class EventPublisher implements ApplicationContextAware {

    private ApplicationContext context;

    public void publishEvent(int number, Locale locale) {
        context.publishEvent(new Event(number, locale));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
