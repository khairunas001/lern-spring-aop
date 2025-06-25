package bang_anas.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect // Menandai class ini sebagai Aspec
@Component // Agar Spring mendeteksi dan mendaftarkan bean ini
public class LogAspect {

    // Pointcut untuk semua method di class HelloService (berdasarkan target class)
    @Pointcut("target(bang_anas.aop.service.HelloService)")
    public void helloServideMethod() {

    }

    // Advice yang dieksekusi sebelum method HelloService dijalankan
    @Before("helloServideMethod()")
    public void beforeHelloMethodService(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Before " + className + "." + methodName + "()");
    }

    // Advice yang membungkus eksekusi method HelloService (sebelum, sesudah, dan jika error)
    @Around("helloServideMethod()")
    public Object aroundBeforeHelloMethodService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            log.info("Around Before " + className + "." + methodName + "()");
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable throwable) {
            log.error("Around Error " + className + "." + methodName + "()");
            throw throwable;
        } finally {
            log.info("Around Finally " + className + "." + methodName + "()");
        }
    }

    // Pointcut untuk method HelloService yang menerima parameter String
    @Pointcut("execution(* bang_anas.aop.service.HelloService.*(java.lang.String))")
    public void pointCutHelloServiceStringParam() {

    }

    @Pointcut("execution(* bang_anas.aop.service.HelloService.*(java.lang.String, String))")
    public void pointCutHelloServiceStringParam2() {

    }

    // Advice sebelum method HelloService dengan parameter String dijalankan
    @Before("pointCutHelloServiceStringParam() && args(name)")
    public void logStringParameter(String name) {
        log.info("Excecute method with parameter : " + name);
    }

    @Before("pointCutHelloServiceStringParam2() && args(firstName,lastName)")
    public void logStringParameter2(String firstName, String lastName) {
        log.info("Execute method with parameter: {} and {}", firstName, lastName);
    }
//    @Before("pointCutHelloServiceStringParam()")
//    public void logStringParameter(JoinPoint joinPoint) {
//        String value = (String) joinPoint.getArgs()[0];
//        log.info("Excecute method with parameter : " + value);
//    }


    // Pointcut untuk semua method dalam package service
    @Pointcut("execution(* bang_anas.aop.service.*.*(..))")
    public void poitCutServicePackage() {

    }

    // Pointcut untuk semua bean yang nama class-nya diakhiri dengan 'Service'
    @Pointcut("bean(*Service)")
    public void poitCutServiceBean() {

    }


    // Pointcut untuk semua method public
    @Pointcut("execution(public * *(..))")
    public void poitCutServiceMethod() {

    }

    // Pointcut gabungan: method public dalam package service dan bean *Service
    @Pointcut("poitCutServicePackage()  && poitCutServiceBean() && poitCutServiceMethod()")
    public void publicMethodForService() {

    }

    // Advice sebelum semua service method yang sesuai dengan gabungan pointcut di atas
    @Before("publicMethodForService()")
    public void logAllService() {
        log.info("Log for all service method");
    }
}
