package les.spring.guessTheNumberGame;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspects {


    private static final Logger log = LoggerFactory.getLogger(GuessTheNumberGameApplication.class);

    @AfterReturning(pointcut = "@annotation(les.spring.guessTheNumberGame.logAnnotations.LogMethod)", returning = "result")
    public void logMethod(JoinPoint joinPoint, Object result) {
        log.info("LogMethod! MethodName: {}", joinPoint.getSignature().getName());
        log.info("Return: {}", result);
        log.info("Args:", joinPoint.getSignature().getName(), result);

        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            log.info("this function has no args");
        } else {
            for (Object arg : args) {
                log.info((String) arg);
            }
        }
    }

    @Around("@annotation(les.spring.guessTheNumberGame.logAnnotations.WorkTime)")
    public void workTime(ProceedingJoinPoint pjp) throws Throwable {
        long m = System.currentTimeMillis();
        Object result = pjp.proceed();
        double worktime = (double) (System.currentTimeMillis() - m);
        log.info("WorkTime! MethodName: {}; worktime: {}", pjp.getSignature().getName(), worktime);
    }
}
