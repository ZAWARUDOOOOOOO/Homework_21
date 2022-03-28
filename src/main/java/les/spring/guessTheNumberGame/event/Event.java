package les.spring.guessTheNumberGame.event;

import les.spring.guessTheNumberGame.logAnnotations.LogMethod;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class Event extends ApplicationEvent {

    private int number;
    private Locale locale;

    public Event(int number, Locale locale) {
        super(number);
        this.number = number;
        this.locale = locale;
    }

    public int getNumber() {
        return number;
    }

    public Locale getLocale() {
        return locale;
    }
}
