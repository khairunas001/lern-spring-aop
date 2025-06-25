package bang_anas.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloService {

    public String hello(String name) {
        log.info("Call HelloService.hello()");
        return "Hello " + name;
    }

    public String helloFirstLast(String firstName, String lastName) {
        log.info("Call HelloService.helloFirstLast()");
        return "Hello " + firstName + " " + lastName;
    }

    public String bye(String name) {
        log.info("Call HelloService.bye()");
        return "Bye " + name;
    }

    public String error(String name) {
        log.info("Call HelloService.error()");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is Required");
        }
        return "Error " + name;
    }

    public void test() {
        log.info("Call HelloServie.test()");
    }


}
