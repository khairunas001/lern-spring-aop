package bang_anas.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("target(bang_anas.aop.service.HelloService)")
    public void helloServideMethod() {

    }

    @Before("helloServideMethod()")
    public void beforeHelloMethodServide(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Before " + className + "." + methodName + "()");
    }

    @Before("helloServideMethod()")
    public void beforeHelloMethodServide2() {
        log.info("Before Hello Service Method Agains");
    }

}
